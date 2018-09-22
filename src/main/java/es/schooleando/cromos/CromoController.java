package es.schooleando.cromos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CromoController {
	
	Logger logger = LoggerFactory.getLogger(CromoController.class);

	@Autowired
	private ServletContext context;
	
	@GetMapping("form")
	public String form(Model model) {
		model.addAttribute("cromo", new CromoForm());
		return "form.html";
	}
	
	@PostMapping("save")
	public String save(@ModelAttribute CromoForm cromo, Model model) throws IOException {
		model.addAttribute("cromo", cromo);
		
		// create temp file
		File convFile = File.createTempFile("player",".jpg");
        FileOutputStream fos = new FileOutputStream(convFile); 
        fos.write(cromo.getImagen().getBytes());
        fos.close(); 
        
		// initialize imagen_url
        cromo.setImagen_url("upload/" + convFile.getName());
        logger.info("imagen_url:" + cromo.getImagen_url());
		return "show.html";
	}
}
