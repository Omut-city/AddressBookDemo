package study.interview.codeExample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import study.interview.codeExample.api.CategoryService;
import study.interview.codeExample.api.ContactService;
import study.interview.codeExample.api.DateService;
import study.interview.codeExample.api.ModelService;
import study.interview.codeExample.api.PropertyService;
import study.interview.codeExample.model.dto.CategoryDto;
import study.interview.codeExample.model.dto.ContactDto;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ContactService contactService;
    private final CategoryService categoryService;
    private final PropertyService propertyService;
    private final DateService dateService;

    @Value("${contacts.page.size}")
    private int pageSize;

    @Override
    public void fillModelAddContactForm(Model model) {
        model.addAttribute("javaVersion", System.getProperty("java.version"));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("properties", propertyService.findAll());
        model.addAttribute("years", dateService.getYears());
        model.addAttribute("months", dateService.getMonths());
        model.addAttribute("days", dateService.getDays());
    }

    @Override
    public void fillModelViewContacts(Model model, int pageNumber) {
        pageNumber = (pageNumber < 0) ? 0 : pageNumber;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        model.addAttribute("javaVersion", System.getProperty("java.version"));
        model.addAttribute("contacts", contactService.findAll(pageable));
        model.addAttribute("amount", contactService.count());
        model.addAttribute("page", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("last", contactService.count() / pageSize);
    }

    @Override
    public void fillModelEditContactForm(Model model, ContactDto contact) {
        model.addAttribute("javaVersion", System.getProperty("java.version"));
        model.addAttribute("contact", contact);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("properties", propertyService.findAll());
        model.addAttribute("years", dateService.getYears());
        model.addAttribute("months", dateService.getMonths());
        model.addAttribute("days", dateService.getDays());
    }

    @Override
    public void fillModelSearchContacts(Model model, List<ContactDto> contacts) {
        model.addAttribute("javaVersion", System.getProperty("java.version"));
        model.addAttribute("contacts", contacts);
        model.addAttribute("amount", contacts.size());
        model.addAttribute("page", 0);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("last", contacts.size() / pageSize);
    }

    @Override
    public void fillModelEditCategoryForm(Model model, CategoryDto category) {
        model.addAttribute("javaVersion", System.getProperty("java.version"));
        model.addAttribute("category", category);
    }

    @Override
    public void fillModelAddPropertyForm(Model model, int categoryId) {
        model.addAttribute("category", categoryService.findCategory(categoryId));
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("javaVersion", System.getProperty("java.version"));
    }

    @Override
    public void fillFooter(Model model) {
        model.addAttribute("javaVersion", System.getProperty("java.version"));
    }
}
