package study.interview.codeExample.controller.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import study.interview.codeExample.api.ModelService;

@Slf4j
@RequiredArgsConstructor
@Controller
public class DefaultController {

    private final ModelService modelService;

    @GetMapping("/")
    public String index(Model model) {
        log.info("Begin API GET: /");
        modelService.fillFooter(model);
        log.info("Finish API GET: /");
        return "index";
    }

}
