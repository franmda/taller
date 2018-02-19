package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HolaController {
	@RequestMapping("irHola")
	public ModelAndView redireccion() {
		ModelAndView MV = new ModelAndView();
		MV.setViewName("irHola");
		return MV;
		
	}

}
