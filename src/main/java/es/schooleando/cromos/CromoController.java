package es.schooleando.cromos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CromoController {
	
	Logger logger = LoggerFactory.getLogger(CromoController.class);
	
	@GetMapping("/")
	public String form(Model model) {
		
		model.addAttribute("cromo", new CromoForm());
		return "form.html";
	}
	
	@PostMapping("/")
	public String form(@Valid @ModelAttribute("cromo") CromoForm cromo, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws IOException {
		
		if (cromo.getImagen().getSize() == 0) {
			FieldError error = new FieldError("cromo", "imagen","Debes añadir una imagen!");
			bindingResult.addError(error);
		}
		
		if (bindingResult.hasErrors()) {
			logger.info("Errores");
			return "form";
        }

		// create temp file
		File convFile = File.createTempFile("player",".jpg");
        FileOutputStream fos = new FileOutputStream(convFile); 
        fos.write(cromo.getImagen().getBytes());
        fos.close(); 
        
		// initialize imagen_url
        cromo.setImagen_url("upload/" + convFile.getName());
        //logger.info("imagen_url:" + cromo.getImagen_url());
        redirectAttributes.addFlashAttribute("cromo", cromo);
        return "redirect:/show";
		
	}
	
	@GetMapping("show")
	public String show(Model model) {
		// TODO: check if FlashAttribute is present or redirect to "/"
		if (model.containsAttribute("cromo")) {
			return "show.html";
		}
		else {
			return "redirect:/";
		}
	}
}
