package es.schooleando.cromos;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CromoController {

	@GetMapping("form")
	public String form(Model model) {
		model.addAttribute("cromo", new CromoForm());
		return "form.html";
	}
	
	@PostMapping("save")
	public String save(HttpServletRequest request, @ModelAttribute CromoForm cromo, Model model) {
		model.addAttribute("cromo", cromo);
		return "show.html";
	}
}
