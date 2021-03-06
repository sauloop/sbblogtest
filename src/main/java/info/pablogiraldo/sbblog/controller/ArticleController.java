package info.pablogiraldo.sbblog.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import info.pablogiraldo.sbblog.entity.Article;
import info.pablogiraldo.sbblog.entity.Category;
import info.pablogiraldo.sbblog.repository.ICategoryRepository;
import info.pablogiraldo.sbblog.service.IArticleService;
import info.pablogiraldo.sbblog.service.ICategoryService;
import info.pablogiraldo.sbblog.utils.RenderizadorPaginas;

@Controller
@RequestMapping("/")
public class ArticleController {

	@Autowired
	private IArticleService articleService;

	@Autowired
	ServletContext context;

	@Autowired
	private ICategoryService categoryService;

	@GetMapping("")
	public String listArticles(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable articlePageable = PageRequest.of(page, 4);

		Page<Article> articles = articleService.listArticles(articlePageable);
//		local
//		RenderizadorPaginas<Article> renderizadorPaginas = new RenderizadorPaginas<Article>("", articles);

		RenderizadorPaginas<Article> renderizadorPaginas = new RenderizadorPaginas<Article>("", articles);

		model.addAttribute("renpag", renderizadorPaginas);
		model.addAttribute("articles", articles);

		return "inicio";
	}

	@GetMapping("/formsearch")
	public String formSearch(Model model) {
		model.addAttribute("category", new Category());
		model.addAttribute("categories", categoryService.listCategories());

		return "formSearch";
	}

	@GetMapping("/search")
	public String searchByCategory(@RequestParam String name, Model model,
			@ModelAttribute("category") Category category) {

//		List<Article> articles = (List<Article>) articleService.listArticles();
//		Collections.sort(articles);

//		List<Article> articles = new ArrayList<Article>();

		if (name == "") {

			return "redirect:/formsearch";

		} else {
			Category cat = categoryService.findCategoryByName(name);
			List<Article> articles = cat.getArticles();
			Collections.sort(articles);

			model.addAttribute("categories", categoryService.listCategories());
			model.addAttribute("articles", articles);

			return "formSearch";
		}
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
	public String adminArticles(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable articlePageable = PageRequest.of(page, 10);

		Page<Article> articles = articleService.listArticles(articlePageable);

		RenderizadorPaginas<Article> renderizadorPaginas = new RenderizadorPaginas<Article>("", articles);

		model.addAttribute("renpag", renderizadorPaginas);
		model.addAttribute("articles", articles);

		return "adminArticles";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/admin/articles/formarticle")
	public String formArticle(Model model, @RequestParam(name = "id", required = true) long id) {

		Article article = new Article();

		Optional<Article> artOp = articleService.findArticleById(id);

		if (artOp.isPresent()) {

			article = artOp.get();

//			local
//			Calendar c = Calendar.getInstance();
//			c.setTime(article.getDay());
//			c.add(Calendar.DAY_OF_MONTH, 1);
//			article.setDay(c.getTime());
		}

		else {

			Date date = new Date();

			article.setDay(date);
		}

		model.addAttribute("article", article);
		model.addAttribute("categories", categoryService.listCategories());
		return "formArticle";
	}

	@PostMapping("/admin/articles/addarticle")
	public String addArticle(@Valid Article article, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("article", article);
			return "formArticle";
		}

		articleService.addArticle(article);

		return "redirect:/admin/articles/adminarticles";
	}

//	@GetMapping("/admin/articles/formarticle")
//	public String formArticle(Model model) {
//		model.addAttribute("article", new Article());
//		return "formArticle";
//	}
//
//	@PostMapping("/admin/articles/formarticle")
//	public String addArticle(@RequestParam(name = "image", required = false) MultipartFile foto, Article article,
//			BindingResult result, Model model, RedirectAttributes flash) {
//
//		if (result.hasErrors()) {
//			model.addAttribute("article", article);
//			return "formArticle";
//		}
//
//		if (!foto.isEmpty()) {
//
//			 local
//			String ruta = "/";
//
//			String relativeWebPath = "/";
//			String ruta = context.getRealPath(relativeWebPath);
//
//			String nombreUnico = UUID.randomUUID().toString() + "-" + foto.getOriginalFilename();
//
//			String nombreUnico = foto.getOriginalFilename();
//
//			try {
//				byte[] bytes = foto.getBytes();
//
//				 local
//				Path rutaAbsoluta = Paths.get(ruta + "//" + nombreUnico);
//
//				Path rutaAbsoluta = Paths.get(ruta + nombreUnico);
//				Files.write(rutaAbsoluta, bytes);
//				article.setImage(nombreUnico);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//
//		}
//
//	articleService.addArticle(article);flash.addFlashAttribute("success","Artículo guardado con éxito.");
//
//	return"redirect:/admin/articles/formarticle";
//
//	}

	@GetMapping("/admin/articles/delete/{id}")
	public String deleteArticle(@PathVariable("id") long id) {
		articleService.deleteArticle(id);

		return "redirect:/admin/articles/adminarticles";
	}
}
