package study.interview.codeExample.api;

import org.springframework.data.domain.Pageable;
import study.interview.codeExample.model.dto.ContactDto;

import java.util.List;

public interface ContactService {

    ContactDto addContact(ContactDto contactDto);

    ContactDto updateContact(ContactDto contactDto);

    long count();

    List<ContactDto> findAll(Pageable pageable);

    ContactDto findContact(Integer contactId);

    List<ContactDto> searchContacts(String pattern);

    boolean deleteContact(Integer contactId);

    boolean existsByEmail(String email);
}
