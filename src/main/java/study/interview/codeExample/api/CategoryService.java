package study.interview.codeExample.api;

import org.springframework.data.domain.Pageable;
import study.interview.codeExample.model.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    long count();

    CategoryDto findCategory(int categoryId);

    List<CategoryDto> findAll();

    List<CategoryDto> findAll(Pageable pageable);

    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto);

    boolean deleteCategory(Integer categoryId);

    boolean existsByName(String email);

}
