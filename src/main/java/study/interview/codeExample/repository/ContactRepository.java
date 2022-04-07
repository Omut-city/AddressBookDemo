package study.interview.codeExample.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import study.interview.codeExample.model.entity.ContactEntity;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {

    Page<ContactEntity> findAll(Pageable pageable);

    Boolean existsByEmail(String email);

    @Query("select entity from contact entity " +
            "where entity.name like %:name% " +
            "or entity.surname like %:surname% " +
            "or entity.email like %:email% " +
            "or entity.phone like %:phone%")
    List<ContactEntity> searchByPattern(@Param("name") String name,
                                        @Param("surname") String surname,
                                        @Param("email") String email,
                                        @Param("phone") String phone);

}
