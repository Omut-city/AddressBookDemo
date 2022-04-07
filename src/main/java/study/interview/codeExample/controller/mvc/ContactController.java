package study.interview.codeExample.controller.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.interview.codeExample.api.ContactService;
import study.interview.codeExample.api.ModelService;
import study.interview.codeExample.model.dto.ContactDto;
import study.interview.codeExample.model.dto.SearchPatternDto;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/contacts")
public class ContactController {

    private final ContactService contactService;
    private final ModelService modelService;

    @GetMapping("/addContactForm")
    public String addContactForm(Model model) {
        log.info("Begin API GET: /contacts/addContactForm");
        modelService.fillModelAddContactForm(model);
        log.info("Finish API GET: /contacts/addContactForm");
        return "addContactForm";
    }

    @PostMapping("/addContact")
    public String addContact(Model model, @Valid ContactDto contactDto) {
        log.info("Begin API POST: /contacts/addContact. body: {}", contactDto);
        contactService.addContact(contactDto);
        modelService.fillFooter(model);
        log.info("Finish API POST: /contacts/addContact. body: {}", contactDto);
        return "redirect:/contacts/view";
    }

    @GetMapping("/view")
    public String viewContacts(Model model, @RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
        log.info("Begin API GET: /contacts/viewContacts");
        modelService.fillModelViewContacts(model, pageNumber);
        log.info("Finish API GET: /contacts/viewContacts");
        return "contacts";
    }

    @GetMapping("/editContactForm/{contactId}")
    public String editContactForm(Model model, @PathVariable("contactId") Integer contactId) {
        log.info("Begin API GET: /contacts/editContact/{}", contactId);
        ContactDto contact = contactService.findContact(contactId);
        modelService.fillModelEditContactForm(model, contact);
        log.info("Finish API GET: /contacts/editContact{}", contact);
        return "editContactForm";
    }

    @PostMapping("/editContact")
    public String editContact(Model model, @Valid ContactDto contactDto) {
        log.info("Begin API POST: /contacts/editContact. body: {}", contactDto);
        contactService.updateContact(contactDto);
        modelService.fillFooter(model);
        log.info("Finish API POST: /contacts/editContact. body: {}", contactDto);
        return "redirect:/contacts/view";
    }

    @PostMapping("/search")
    public String searchContacts(Model model, @Valid SearchPatternDto searchPatternDto) {
        log.info("Begin API GET: /contacts/search/{}", searchPatternDto.getPattern());
        List<ContactDto> contacts = contactService.searchContacts(searchPatternDto.getPattern());
        modelService.fillModelSearchContacts(model, contacts);
        log.info("Finish API GET: /contacts/search{}", searchPatternDto.getPattern());
        return "contacts";
    }

    @GetMapping("/deleteContact/{contactId}")
    public String deleteContact(Model model, @PathVariable("contactId") Integer contactId) {
        log.info("Begin API GET: /contacts/deleteContact/{}", contactId);
        boolean contactWasDeleted = contactService.deleteContact(contactId);
        modelService.fillFooter(model);
        log.info("Finish API GET: /contacts/deleteContact{}, contact was deleted: {}", contactId, contactWasDeleted);
        return "redirect:/contacts/view";
    }

}
