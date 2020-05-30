package info.pablogiraldo.sbblog.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.util.Map;
//import java.util.UUID;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import info.pablogiraldo.sbblog.entity.Article;
import info.pablogiraldo.sbblog.repository.IArticleRepository;
import info.pablogiraldo.sbblog.service.IArticleService;
import info.pablogiraldo.sbblog.utils.RenderizadorPaginas;

@Controller
@RequestMapping("/")
public class ArticleController {

	@Autowired
	private IArticleService articleService;

	@Autowired
	ServletContext context;

	@GetMapping("")
	public String listArticles(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable articlePageable = PageRequest.of(page, 2);

		Page<Article> articles = articleService.listArticles(articlePageable);
		RenderizadorPaginas<Article> renderizadorPaginas = new RenderizadorPaginas<Article>("", articles);

		model.addAttribute("renpag", renderizadorPaginas);
		model.addAttribute("articles", articles);

		return "inicio";
	}

	@GetMapping("/trueknic")
	public String trueknic() {
		return "trueknic";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}

	@GetMapping("/admin/articles/adminarticles")
	public String adminArticles(Model model) {
		model.addAttribute("articles", articleService.listArticles());
		return "adminArticles";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

//	@GetMapping("/admin/articles/formarticle")
//	public String formArticle(Model model) {
//		model.addAttribute("article", new Article());
//		return "formArticle";
//	}

	@GetMapping("/admin/articles/formarticle")
	public String userForm(Model model, @RequestParam(name = "id", required = true) long id) {

		Article article = new Article();

		Optional<Article> artOp = articleService.findArticleById(id);

		if (artOp.isPresent()) {

			article = artOp.get();
		}

		model.addAttribute("article", article);
		return "formArticle";
	}

	// , RedirectAttributes flash
	
	@PostMapping("/admin/articles/addarticle")
	public String addArticle(@Valid Article article, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("article", new Article());
			return "formArticle";
		}

		articleService.addArticle(article);
//		flash.addFlashAttribute("success", "Artículo guardado con éxito.");

//		return "redirect:/admin/articles/formarticle";

		return "redirect:/admin/articles/adminarticles";
	}

	@GetMapping("/admin/articles/delete/{id}")
	public String borrar(@PathVariable("id") long id, Model model) {
		articleService.deleteArticle(id);
		model.addAttribute("articles", articleService.listArticles());
		return "adminArticles";
	}
}
