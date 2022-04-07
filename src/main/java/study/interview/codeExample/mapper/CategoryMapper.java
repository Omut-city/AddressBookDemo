package study.interview.codeExample.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.interview.codeExample.exception.CategoryWrongIdException;
import study.interview.codeExample.model.dto.CategoryDto;
import study.interview.codeExample.model.dto.PropertyDto;
import study.interview.codeExample.model.entity.CategoryEntity;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryMapper {

    private final PropertyMapper propertyMapper;

    public CategoryEntity toEntity(CategoryDto categoryDto) {
        if (Objects.isNull(categoryDto)) {
            return null;
        }
        return CategoryEntity.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
    }

    public CategoryDto toDto(CategoryEntity categoryEntity) {
        if (Objects.isNull(categoryEntity)) {
            return null;
        }
        List<PropertyDto> properties = null;
        if (Objects.nonNull(categoryEntity.getProperties())) {
            properties = propertyMapper.toDtoList(categoryEntity.getProperties());
        }
        return CategoryDto.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .properties(properties)
                .build();
    }

    public List<CategoryDto> toDtoList(List <CategoryEntity> categories) {
        return categories.stream()
                .map(this::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public CategoryEntity updateEntity(CategoryEntity categoryEntity, CategoryDto categoryDto) {
        if (Objects.isNull(categoryDto)) {
            return null;
        }
        if (!Objects.equals(categoryEntity.getId(), categoryDto.getId())) {
            throw new CategoryWrongIdException(categoryEntity.getId(), categoryDto.getId());
        }
        categoryEntity.setName(categoryDto.getName());
        return categoryEntity;
    }

}
