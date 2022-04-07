package study.interview.codeExample.controller.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.interview.codeExample.api.CategoryService;
import study.interview.codeExample.api.ModelService;
import study.interview.codeExample.model.dto.CategoryDto;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final ModelService modelService;

    @Value("${contacts.page.size}")
    private int pageSize;

    @GetMapping("/view")
    public String viewCategories(Model model, @RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber
    ) {
        log.info("Begin API GET: /categories/view, pageNumber: {}", pageNumber);
        pageNumber = (pageNumber < 0) ? 0 : pageNumber;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<CategoryDto> categories = categoryService.findAll(pageable);
        model.addAttribute("javaVersion", System.getProperty("java.version"));
        model.addAttribute("categories", categories);
        model.addAttribute("amount", categoryService.count());
        model.addAttribute("page", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("last", categoryService.count() / pageSize);
        log.info("Finish API GET: /categories/view, pageNumber: {}", pageNumber);
        return "categories";

    }

    @GetMapping("/addCategoryForm")
    public String addCategoryForm(Model model) {
        log.info("Begin API GET: /categories/addCategoryForm");
        modelService.fillFooter(model);
        log.info("Finish API GET: /categories/addCategoryForm");
        return "addCategoryForm";
    }

    @PostMapping("/addCategory")
    public String addCategory(Model model, @Valid CategoryDto categoryDto) {
        log.info("Begin API POST: /categories/addCategory. body: {}", categoryDto);
        categoryService.addCategory(categoryDto);
        modelService.fillFooter(model);
        log.info("Finish API POST: /myContacts/addContact. body: {}", categoryDto);
        return "redirect:/categories/view";
    }

    @GetMapping("/editCategoryForm/{categoryId}")
    public String editCategoryForm(Model model, @PathVariable("categoryId") int categoryId) {
        log.info("Begin API GET: /categories/editCategoryForm/{}", categoryId);
        CategoryDto category = categoryService.findCategory(categoryId);
        modelService.fillModelEditCategoryForm(model, category);
        log.info("Finish API GET: /categories/editCategoryForm/{}", categoryId);
        return "editCategoryForm";
    }

    @PostMapping("/editCategory")
    public String editCategory(Model model, @Valid CategoryDto categoryDto) {
        log.info("Begin API POST: /categories/editCategory. body: {}", categoryDto);
        categoryService.updateCategory(categoryDto);
        modelService.fillFooter(model);
        log.info("Finish API POST: /categories/editCategory. body: {}", categoryDto);
        return "redirect:/categories/view";
    }

    @GetMapping("/deleteCategory/{categoryId}")
    public String deleteCategory(Model model, @PathVariable("categoryId") int categoryId) {
        log.info("Begin API GET: /categories/deleteCategory/{}", categoryId);
        boolean contactWasDeleted = categoryService.deleteCategory(categoryId);
        modelService.fillFooter(model);
        log.info("Finish API GET: /categories/deleteCategory{}, contact was deleted: {}", categoryId, contactWasDeleted);
        return "redirect:/categories/view";
    }

}
