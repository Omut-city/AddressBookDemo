package study.interview.codeExample.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.interview.codeExample.api.ContactService;

@Slf4j
@RequestMapping(value = "/api", produces = "application/json;charset=UTF-8")
@RestController
public class RestContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/exists/contact/email/{email}")
    public Boolean checkContactEmailExistence(@PathVariable("email") String email) {
        log.info("Begin REST GET: /api/exists/contact/email/{}", email);
        return email == null? false : contactService.existsByEmail(email);
    }

}
