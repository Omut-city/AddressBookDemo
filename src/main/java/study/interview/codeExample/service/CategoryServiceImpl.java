package study.interview.codeExample.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import study.interview.codeExample.api.CategoryService;
import study.interview.codeExample.exception.CategoryHasPropertiesException;
import study.interview.codeExample.exception.CategoryNotFoundException;
import study.interview.codeExample.mapper.CategoryMapper;
import study.interview.codeExample.model.dto.CategoryDto;
import study.interview.codeExample.model.entity.CategoryEntity;
import study.interview.codeExample.repository.CategoryRepository;
import study.interview.codeExample.repository.PropertyRepository;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private PropertyRepository propertyRepository;
    private CategoryMapper categoryMapper;

    @Override
    public long count() {
        return categoryRepository.count();
    }

    @Override
    public CategoryDto findCategory(int categoryId) {
        return categoryMapper.toDto(categoryRepository.findById(categoryId).orElse(null));
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryMapper.toDtoList(categoryRepository.findAll());
    }

    @Override
    public List<CategoryDto> findAll(Pageable pageable) {
        return categoryMapper.toDtoList(categoryRepository.findAll(pageable).toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CategoryDto addCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = categoryMapper.toEntity(categoryDto);
        categoryEntity = categoryRepository.save(categoryEntity);
        return categoryMapper.toDto(categoryEntity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        if (Objects.isNull(categoryDto.getId())) {
            throw new CategoryNotFoundException(null);
        }
        CategoryEntity categoryEntity = findCategoryEntity(categoryDto.getId());
        categoryEntity = categoryMapper.updateEntity(categoryEntity, categoryDto);
        categoryRepository.save(categoryEntity);
        return categoryMapper.toDto(categoryEntity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteCategory(Integer categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new CategoryNotFoundException(categoryId);
        } else if (propertyRepository.existsByCategoryId(categoryId)) {
            throw new CategoryHasPropertiesException(categoryId);
        }
        categoryRepository.deleteById(categoryId);
        return !categoryRepository.existsById(categoryId);
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    private CategoryEntity findCategoryEntity(int categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }
}
