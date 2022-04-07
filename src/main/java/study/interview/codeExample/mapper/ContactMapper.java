package study.interview.codeExample.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.interview.codeExample.exception.ContactWrongIdException;
import study.interview.codeExample.model.dto.ContactDto;
import study.interview.codeExample.model.dto.PropertyDto;
import study.interview.codeExample.model.entity.CategoryEntity;
import study.interview.codeExample.model.entity.ContactEntity;
import study.interview.codeExample.model.entity.PropertyEntity;
import study.interview.codeExample.repository.CategoryRepository;
import study.interview.codeExample.repository.PropertyRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContactMapper {

    private final CategoryRepository categoryRepository;
    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    public ContactEntity toEntity(ContactDto contactDto) {
        if (Objects.isNull(contactDto)) {
            return null;
        }
        LocalDate birthday = null;
        CategoryEntity category = null;
        PropertyEntity property = null;
        if (Objects.nonNull(contactDto.getYear()) && Objects.nonNull(contactDto.getMonth()) && Objects.nonNull(contactDto.getDay())) {
            birthday = LocalDate.of(contactDto.getYear().intValue(), contactDto.getMonth(), contactDto.getDay().intValue());
        }
        if (Objects.nonNull(contactDto.getCategoryId())) {
            category = categoryRepository.findById(contactDto.getCategoryId()).orElse(null);
        }
        if (Objects.nonNull(contactDto.getCategoryPropertyId())) {
            String [] categoryPropertyId = contactDto.getCategoryPropertyId().split("_");
            Integer categoryId = Integer.valueOf(categoryPropertyId[0]);
            Integer propertyId = Integer.valueOf(categoryPropertyId[1]);
            property = propertyRepository.findById(propertyId).orElse(null);
            if (Objects.nonNull(property)
                    && Objects.nonNull(property)
                    && property.getCategory().getId().equals(category.getId())
                    && property.getCategory().getId().equals(categoryId)
            ) {
                log.info("Property's category is equals category!");
            }
        }

        return ContactEntity.builder()
                .name(contactDto.getName())
                .surname(contactDto.getSurname())
                .email(contactDto.getEmail())
                .phone(contactDto.getPhone())
                .gender(contactDto.getGender())
                .category(category)
                .property(property)
                .birthday(birthday)
                .created(contactDto.getCreated())
                .updated(contactDto.getUpdated())
                .build();
    }

    public ContactDto toDto(ContactEntity contactEntity) {
        if (Objects.isNull(contactEntity)) {
            return null;
        }
        Integer year = null, month = null, day = null, categoryId = null, propertyId = null;
        LocalDate birthday = null;
        PropertyDto property = null;
        if (Objects.nonNull(contactEntity.getBirthday())) {
            year = contactEntity.getBirthday().getYear();
            month = contactEntity.getBirthday().getMonth().getValue();
            day = contactEntity.getBirthday().getDayOfMonth();
            birthday = LocalDate.of(year, month, day);
        }
        if (Objects.nonNull(contactEntity.getCategory())) {
            categoryId = contactEntity.getCategory().getId();
        }
        if (Objects.nonNull(contactEntity.getProperty())) {
            propertyId = contactEntity.getProperty().getId();
            property = propertyMapper.toDto(propertyRepository.findById(propertyId).orElse(null));
        }
        return ContactDto.builder()
                .id(contactEntity.getId())
                .name(contactEntity.getName())
                .surname(contactEntity.getSurname())
                .email(contactEntity.getEmail())
                .phone(contactEntity.getPhone())
                .gender(contactEntity.getGender())
                .birthday(birthday)
                .year(year)
                .month(month)
                .day(day)
                .categoryId(categoryId)
                .propertyId(propertyId)
                .property(property)
                .created(contactEntity.getCreated())
                .updated(contactEntity.getUpdated())
                .build();
    }

    public ContactEntity updateEntity(ContactEntity contactEntity, ContactDto contactDto) {
        if (Objects.isNull(contactDto)) {
            return null;
        }
        if (!Objects.equals(contactEntity.getId(), contactDto.getId())) {
            throw new ContactWrongIdException(contactEntity.getId(), contactDto.getId());
        }
        LocalDate birthday = null;
        CategoryEntity category = null;
        PropertyEntity property = null;
        if (Objects.nonNull(contactDto.getYear()) && Objects.nonNull(contactDto.getMonth()) && Objects.nonNull(contactDto.getDay())) {
            birthday = LocalDate.of(contactDto.getYear().intValue(), contactDto.getMonth(), contactDto.getDay().intValue());
            log.info("construct birthday: {]", birthday);
        }
        if (Objects.nonNull(contactDto.getCategoryId())) {
            category = categoryRepository.findById(contactDto.getCategoryId()).orElse(null);
        }
        if (Objects.nonNull(contactDto.getCategoryPropertyId())) {
            String [] categoryPropertyId = contactDto.getCategoryPropertyId().split("_");
            Integer categoryId = Integer.valueOf(categoryPropertyId[0]);
            Integer propertyId = Integer.valueOf(categoryPropertyId[1]);
            property = propertyRepository.findById(propertyId).orElse(null);
            if (Objects.nonNull(property)
                    && Objects.nonNull(property)
                    && property.getCategory().getId().equals(category.getId())
                    && property.getCategory().getId().equals(categoryId)
            ) {
                log.info("Property's category is equals category!");
            }
        }
        contactEntity.setName(contactDto.getName());
        contactEntity.setSurname(contactDto.getSurname());
        contactEntity.setEmail(contactDto.getEmail());
        contactEntity.setPhone(contactDto.getPhone());
        contactEntity.setGender(contactDto.getGender());
        contactEntity.setBirthday(birthday);
        contactEntity.setCategory(category);
        contactEntity.setProperty(property);
        contactEntity.setUpdated(Instant.now());
        return contactEntity;
    }

    public List<ContactDto> toDtoList(List <ContactEntity> contacts) {
        return contacts.stream()
                .map(this::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

}
