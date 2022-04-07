package study.interview.codeExample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.interview.codeExample.model.entity.PropertyEntity;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyEntity, Integer> {

    Boolean existsByCategory_IdAndName(Integer categoryId, String name);

    Boolean existsByCategoryId(Integer categoryId);

}
