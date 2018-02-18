package controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.UriComponentsBuilder;

import modelo.*;
import service.*;

@Transactional
@RestController
@EnableWebMvc
@RequestMapping("/template")
public class TemplateController {
	@Autowired
	TemplateService templateService;

	@RequestMapping(value = "/template", method = RequestMethod.GET)
	public ResponseEntity<List<Template>> listAllTemplate() {
		List<Template> template = templateService.findAll();
		if (template.isEmpty()) {
			return new ResponseEntity<List<Template>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Template>>(template, HttpStatus.OK);
	}

	@RequestMapping(value = "/template/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Template> getTemplate(@PathVariable("id") Integer id) {
		System.out.println("Obteniendo template con id " + id);
		Template template = templateService.encontrarTemplate(id);
		if (template == null) {
			System.out.println("Template con id " + id + " no encontrado");
			return new ResponseEntity<Template>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Template>(template, HttpStatus.OK);
	}

	@RequestMapping(value = "/template", method = RequestMethod.POST)
	public ResponseEntity<Void> createTemplate(@RequestBody Template template, UriComponentsBuilder ucBuilder) {
		System.out.println("Creando el Template ");
		templateService.guardarTemplate(template);;
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/template/{id}").buildAndExpand(template.getTemplate_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}


	@RequestMapping(value = "/template/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Template> updateTemplate(@PathVariable("id") Integer id, @RequestBody Template template) {
		System.out.println("Actualizando template " + id);
		Template currentTemplate = templateService.encontrarTemplate(id);
		if (currentTemplate == null) {
			System.out.println("template con id " + id + " no encontrado");
			return new ResponseEntity<Template>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Template>(currentTemplate, HttpStatus.OK);
	}


	@RequestMapping(value = "/media/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Template> deleteTemplate(@PathVariable("id") Integer id) {
		System.out.println("Obteniendo y eliminando el Template con id " + id);
		Template template = templateService.encontrarTemplate(id);
		if (template == null) {
			System.out.println("No es posible eliminar, no se encuentra el template con id " + id);
			return new ResponseEntity<Template>(HttpStatus.NOT_FOUND);
		}
		templateService.borrarTemplate(id);
		return new ResponseEntity<Template>(HttpStatus.OK);
	}

}
