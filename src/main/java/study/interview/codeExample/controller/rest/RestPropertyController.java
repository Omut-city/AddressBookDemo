package study.interview.codeExample.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.interview.codeExample.api.PropertyService;

@Slf4j
@RequestMapping(value = "/api", produces = "application/json;charset=UTF-8")
@RestController
public class RestPropertyController {

    @Autowired
    private PropertyService propertyService;

        @GetMapping("/exists/property/{categoryId}/{name}")
        public Boolean checkPropertyNameExistence(@PathVariable("categoryId") Integer categoryId, @PathVariable("name") String name) {
            log.info("Begin REST GET: /api/exists/property/{},{}", categoryId, name);
            return categoryId == null || name == null ? false : propertyService.existsByCategoryIdAndName(categoryId, name);
    }

}
