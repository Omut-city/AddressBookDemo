package study.interview.codeExample.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.interview.codeExample.api.CategoryService;

@Slf4j
@RequestMapping(value = "/api", produces = "application/json;charset=UTF-8")
@RestController
public class RestCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/exists/category/name/{name}")
    public Boolean checkCategoryNameExistence(@PathVariable("name") String name) {
        log.info("Begin REST GET: /api/exists/category/name/{}", name);
        return name == null? false : categoryService.existsByName(name);
    }

}
