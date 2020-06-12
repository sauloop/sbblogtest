package info.pablogiraldo.sbblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import info.pablogiraldo.sbblog.entity.Article;
import info.pablogiraldo.sbblog.entity.Category;
import info.pablogiraldo.sbblog.service.ICategoryService;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;

	@GetMapping("/formcategory")
	public String formCategory(Model model) {
		model.addAttribute("category", new Category());
		return "formCategory";
	}

//	@PostMapping("/addcategory")
//	public String addCategory(@Valid Category category, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			model.addAttribute("category", category);
//			return "formCategory";
//		}
//
//		categoryService.addCategory(category);
//
//		return "formCategory";
//	}

	@PostMapping("/addcategory")
	public String addCategory(@Valid Category category, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("category", category);
			return "formCategory";
		} else {
			categoryService.addCategory(category);

			return "redirect:/admin/categories/formcategory";
		}
	}
}
