package study.interview.codeExample.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Builder
@Entity(name="property")
@NoArgsConstructor
@AllArgsConstructor
public class PropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 32)
    private String name;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private CategoryEntity category;

    @Override
    public String toString() {
        return "PropertyEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category_id=" + category.getId() +
                '}';
    }

}
