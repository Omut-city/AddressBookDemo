package study.interview.codeExample.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import study.interview.codeExample.model.enums.Gender;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@Entity(name="contact")
@NoArgsConstructor
@AllArgsConstructor
public class ContactEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(nullable = false, length = 128)
    private String surname;

    @Column(unique = true, nullable = false, length = 128)
    private String email;

    @Column(nullable = false, length = 16)
    private String phone;

    private Gender gender;

    private LocalDate birthday;

    @Basic
    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Instant created;

    private Instant updated;

    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name="property_id")
    private PropertyEntity property;

}
