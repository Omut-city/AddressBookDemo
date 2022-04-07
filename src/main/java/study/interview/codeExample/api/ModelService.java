package study.interview.codeExample.api;

import org.springframework.ui.Model;
import study.interview.codeExample.model.dto.CategoryDto;
import study.interview.codeExample.model.dto.ContactDto;

import java.util.List;

public interface ModelService {

    void fillModelAddContactForm(Model model);

    void fillModelViewContacts(Model model,  int pageNumber);

    void fillModelEditContactForm(Model model, ContactDto contact);

    void fillModelSearchContacts(Model model,  List<ContactDto> contacts);

    void fillModelEditCategoryForm(Model model, CategoryDto category);

    void fillModelAddPropertyForm(Model model, int categoryId);

    void fillFooter(Model model);

}
