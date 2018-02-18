package controllers;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;








import javax.sql.rowset.serial.SerialException;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.File;







import java.nio.file.Files;

import modelo.*;
import service.*;

@Transactional
@RestController
@EnableWebMvc
@RequestMapping("/media")
public class MediaController {
	@Autowired
	MediaService mediaService;
	private static final String IMAGENES = "Imagenes";
    private static final String TOMCAT_HOME_PROPERTY = "catalina.home";
    private static final String TOMCAT_HOME_PATH = System.getProperty(TOMCAT_HOME_PROPERTY);
    private static final String IMAGENES_PATH = TOMCAT_HOME_PATH + File.separator + IMAGENES;
    private static final File IMAGENES_DIR = new File(IMAGENES_PATH);
    private static final String IMAGENES_DIR_ABSOLUTE_PATH = IMAGENES_DIR.getAbsolutePath() + File.separator;
    
    private void createImagesDirIfNeeded() {
        if (!IMAGENES_DIR.exists()) {
        	IMAGENES_DIR.mkdirs();
        }
    }
    


	@RequestMapping(value = "/continueFileUpload", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public @ResponseBody ResponseEntity<Void> continueFileUpload(@RequestPart(value = "store") Publicacion pub, @RequestPart("file") MultipartFile file) throws IOException, SerialException, SQLException{
            createImagesDirIfNeeded();
            String fileName= file.getOriginalFilename();
            File image = new File(IMAGENES_DIR_ABSOLUTE_PATH + fileName);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(image));
            stream.write(file.getBytes());
            stream.close();
            
            MediaContents media=new MediaContents(fileName,pub);
            mediaService.guardarMedia(media);
        	return new ResponseEntity<Void>( HttpStatus.CREATED);

    }


	@RequestMapping(value = "/media/{id}", method = RequestMethod.GET)
	public byte[] getMedia(@PathVariable("id") Integer id) throws IOException {
		System.out.println("Obteniendo media content con id " + id);
		MediaContents media = mediaService.encontrarMedia(id);
		if (media == null) {
			System.out.println("Media Content con id " + id + " no encontrado");
			return null;
		}
		System.out.println(IMAGENES_DIR_ABSOLUTE_PATH + media.getFileName());
		File serverFile = new File(IMAGENES_DIR_ABSOLUTE_PATH + media.getFileName());
		return Files.readAllBytes(serverFile.toPath());
		
	}


	@RequestMapping(value = "/media/{id}", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public @ResponseBody ResponseEntity<Void> updateMedia(@RequestPart(value = "store") MediaContents media, @RequestPart("file") MultipartFile file) throws IOException, SerialException, SQLException {
		System.out.println("**************************************************");
		System.out.println("Actualizando media " + media.getMediaContents_id());
		System.out.println("**************************************************");
		MediaContents currentMedia = mediaService.encontrarMedia(media.getMediaContents_id());
		if (currentMedia == null) {
			System.out.println("Media con id " + media.getMediaContents_id() + " no encontrada");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		};
		String fileName= file.getOriginalFilename();
		File image = new File(IMAGENES_DIR_ABSOLUTE_PATH + fileName);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(image));
        stream.write(file.getBytes());
        stream.close();
        
        currentMedia.setFileName(fileName);
        mediaService.actualizarMedia(currentMedia);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}


	@RequestMapping(value = "/media/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<MediaContents> deleteMedia(@PathVariable("id") Integer id) {
		System.out.println("Obteniendo y eliminando el media content con id " + id);
		MediaContents media = mediaService.encontrarMedia(id);
		if (media == null) {
			System.out.println("No es posible eliminar, no se encuentra el media content con id " + id);
			return new ResponseEntity<MediaContents>(HttpStatus.NOT_FOUND);
		}
		mediaService.borrarMedia(id);;
		return new ResponseEntity<MediaContents>(HttpStatus.OK);
	}

}