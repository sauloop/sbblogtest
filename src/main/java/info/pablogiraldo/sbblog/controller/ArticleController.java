package info.pablogiraldo.sbblog.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import info.pablogiraldo.sbblog.entity.Article;
import info.pablogiraldo.sbblog.service.IArticleService;

@Controller
@RequestMapping("/")
public class ArticleController {

	@Autowired
	private IArticleService articleService;

	@Autowired
	ServletContext context;

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
	public String addArticle(@RequestParam(name = "image", required = false) MultipartFile foto, Article article,
			BindingResult result, Model model, RedirectAttributes flash) {

//		if (result.hasErrors()) {
//			model.addAttribute("article", article);
//			return "formArticle";
//		}

		if (!foto.isEmpty()) {

			// local
//			String ruta = "C://pruebas//img";

//			String relativeWebPath = "";
			String relativeWebPath = "src/main/resources/";
			String ruta = context.getRealPath(relativeWebPath);

//			String nombreUnico = UUID.randomUUID().toString() + "-" + foto.getOriginalFilename();

			String nombreUnico = foto.getOriginalFilename();

			try {
				byte[] bytes = foto.getBytes();

				// local
//				Path rutaAbsoluta = Paths.get(ruta + "//" + nombreUnico);

				Path rutaAbsoluta = Paths.get(ruta + nombreUnico);
				Files.write(rutaAbsoluta, bytes);
				article.setImage(nombreUnico);
			} catch (Exception e) {
				// TODO: handle exception
			}

			articleService.addArticle(article);
			flash.addFlashAttribute("success", "Artículo guardado con éxito.");

		}

		return "redirect:/admin/articles/formarticle";

	}
}
