package info.pablogiraldo.sbblog.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import info.pablogiraldo.sbblog.entity.Article;
import info.pablogiraldo.sbblog.service.IArticleService;

@Controller
@RequestMapping("/")
public class ArticleController {

	@Autowired
	private IArticleService articleService;

	@GetMapping("")
	public String listArticles(Model model) {

		model.addAttribute("articles", articleService.listArticles());
		return "inicio";
	}

	@GetMapping("/trueknic")
	public String trueknic() {
		return "trueknic";
	}

	@GetMapping("/admin")
	public String pagina2() {
		return "admin";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/admin/articles/formarticle")
	public String formArticle(Model model) {
//		model.addAttribute("titulo", "Nuevo artículo");
		model.addAttribute("article", new Article());
		return "formArticle";
	}

	@PostMapping("/admin/articles/formarticle")
	public String addArticle(@Valid Article article, BindingResult result, Model model, RedirectAttributes flash) {
		if (result.hasErrors()) {
//			model.addAttribute("titulo", "Nuevo artículo");
			model.addAttribute("article", new Article());
			return "formArticle";
		}

		articleService.addArticle(article);
		flash.addFlashAttribute("success", "Artículo guardado con éxito.");

		return "redirect:/admin/articles/formarticle";
	}

//	@PostMapping("/formestudiante")
//	public String addEstudiante(@Valid Estudiante estudiante, BindingResult result, Map<String, Object> model,
//			RedirectAttributes flash) {
//
//		if (result.hasErrors()) {
//			model.put("estudiante", estudiante);
//			model.put("titulo", "Nuevo estudiante");
//			return "formEstudiante";
//		}
//
//		estudianteService.addEstudiante(estudiante);
//		flash.addFlashAttribute("success", "Estudiante guardado con éxito.");
//
//		return "redirect:/estudiantes/formestudiante";
//	}

}
