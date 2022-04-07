package study.interview.codeExample.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import study.interview.codeExample.api.PropertyService;
import study.interview.codeExample.mapper.PropertyMapper;
import study.interview.codeExample.model.dto.PropertyDto;
import study.interview.codeExample.model.entity.PropertyEntity;
import study.interview.codeExample.repository.PropertyRepository;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private PropertyRepository propertyRepository;
    private PropertyMapper propertyMapper;

    @Override
    public long count() {
        return propertyRepository.count();
    }

    @Override
    public PropertyDto findProperty(int propertyId) {
        return null;
    }

    @Override
    public List<PropertyDto> findAll() {
        return propertyMapper.toDtoList(propertyRepository.findAll());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PropertyDto addProperty(PropertyDto propertyDto) {
        PropertyEntity propertyEntity = propertyMapper.toEntity(propertyDto);
        propertyEntity = propertyRepository.save(propertyEntity);
        return propertyMapper.toDto(propertyEntity);
    }

    @Override
    public boolean existsByCategoryIdAndName(Integer categoryId, String name) {
        return propertyRepository.existsByCategory_IdAndName(categoryId, name);
    }

}
