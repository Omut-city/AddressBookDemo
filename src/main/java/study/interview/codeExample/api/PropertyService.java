package study.interview.codeExample.api;

import study.interview.codeExample.model.dto.PropertyDto;

import java.util.List;

public interface PropertyService {

    long count();

    PropertyDto findProperty(int propertyId);

    List<PropertyDto> findAll();

    PropertyDto addProperty(PropertyDto propertyDto);

    boolean existsByCategoryIdAndName(Integer categoryId, String name);

}
