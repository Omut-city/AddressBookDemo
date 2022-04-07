package study.interview.codeExample.controller.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.interview.codeExample.api.ModelService;
import study.interview.codeExample.api.PropertyService;
import study.interview.codeExample.model.dto.PropertyDto;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/properties")
public class PropertyController {

    private final PropertyService propertyService;
    private final ModelService modelService;

    @GetMapping("/addPropertyForm/{categoryId}")
    public String addPropertyForm(Model model, @PathVariable("categoryId") int categoryId) {
        log.info("Begin API GET: /properties/addPropertyForm/{}", categoryId);
        modelService.fillModelAddPropertyForm(model, categoryId);
        log.info("Finish API GET: /properties/addPropertyForm/{}", categoryId);
        return "addPropertyForm";
    }

    @PostMapping("/addProperty")
    public String addProperty(Model model, @Valid PropertyDto propertyDto) {
        log.info("Begin API POST: /properties/addProperty. body: {}", propertyDto);
        propertyService.addProperty(propertyDto);
        modelService.fillModelAddPropertyForm(model, propertyDto.getCategoryId());
        log.info("Finish API POST: /properties/addProperty. body: {}", propertyDto);
        return "redirect:/categories/view";
    }

}
