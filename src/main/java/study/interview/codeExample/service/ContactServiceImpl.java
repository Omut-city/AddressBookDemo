package study.interview.codeExample.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import study.interview.codeExample.api.ContactService;
import study.interview.codeExample.exception.ContactNotFoundException;
import study.interview.codeExample.mapper.ContactMapper;
import study.interview.codeExample.model.dto.ContactDto;
import study.interview.codeExample.model.entity.ContactEntity;
import study.interview.codeExample.repository.ContactRepository;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;
    private ContactMapper contactMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ContactDto addContact(ContactDto contactDto) {
        ContactEntity contactEntity = contactMapper.toEntity(contactDto);
        return  contactMapper.toDto(contactRepository.save(contactEntity));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ContactDto updateContact(ContactDto contactDto) {
        if (Objects.isNull(contactDto.getId())) {
            throw new ContactNotFoundException(null);
        }
        ContactEntity contactEntity = findContactEntity(contactDto.getId());
        contactEntity = contactMapper.updateEntity(contactEntity, contactDto);
        contactRepository.save(contactEntity);
        return contactMapper.toDto(contactEntity);
    }

    @Override
    public long count() {
        return contactRepository.count();
    }

    @Override
    public List<ContactDto> findAll(Pageable pageable) {
        return contactMapper.toDtoList(contactRepository.findAll(pageable).toList());
    }

    @Override
    public ContactDto findContact(Integer contactId) {
        return contactMapper.toDto(findContactEntity(contactId));
    }

    @Override
    public List<ContactDto> searchContacts(String pattern) {
        List<ContactEntity> contacts = contactRepository.searchByPattern(pattern, pattern, pattern, pattern);
        log.info("Was found: {}", contacts);
        return contactMapper.toDtoList(contacts);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteContact(Integer contactId) {
        if (!contactRepository.existsById(contactId)) {
           throw new ContactNotFoundException(contactId);
        }
        contactRepository.deleteById(contactId);
        return !contactRepository.existsById(contactId);
    }

    @Override
    public boolean existsByEmail(String email) {
        return contactRepository.existsByEmail(email);
    }

    private ContactEntity findContactEntity(Integer contactId) {
        return contactRepository.findById(contactId).orElseThrow(() -> new ContactNotFoundException(contactId));
    }

}
