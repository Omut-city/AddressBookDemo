package study.interview.codeExample.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.interview.codeExample.model.dto.PropertyDto;
import study.interview.codeExample.model.entity.CategoryEntity;
import study.interview.codeExample.model.entity.PropertyEntity;
import study.interview.codeExample.repository.CategoryRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PropertyMapper {

    private final CategoryRepository categoryRepository;

    public PropertyEntity toEntity(PropertyDto propertyDto) {
        if (Objects.isNull(propertyDto)) {
            return null;
        }
        CategoryEntity category = null;
        if (Objects.nonNull(propertyDto.getCategoryId())) {
            category = categoryRepository.findById(propertyDto.getCategoryId()).orElse(null);
        }
        log.info("Before mapping to entity, propertyDto: {}", propertyDto);
        return PropertyEntity.builder()
                .id(propertyDto.getId())
                .name(propertyDto.getName())
                .category(category)
                .build();
    }

    public PropertyDto toDto(PropertyEntity propertyEntity) {
        if (Objects.isNull(propertyEntity)) {
            return null;
        }
        int categoryId = 0;
        String categoryName = null;
        if (Objects.nonNull(propertyEntity.getCategory())) {
            categoryId = propertyEntity.getCategory().getId();
            categoryName = propertyEntity.getCategory().getName();
        }
        return PropertyDto.builder()
                .id(propertyEntity.getId())
                .name(propertyEntity.getName())
                .categoryId(categoryId)
                .categoryName(categoryName)
                .build();
    }

    public List<PropertyDto> toDtoList(List <PropertyEntity> properties) {
        return properties.stream()
                .map(this::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

}
