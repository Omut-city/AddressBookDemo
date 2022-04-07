package study.interview.codeExample.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.interview.codeExample.model.enums.Gender;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {

    private Integer id;

    @NotBlank
    @Size(max = 128)
    private String name;

    @NotBlank
    @Size(max = 128)
    private String surname;

    @NotBlank
    @Size(max = 128)
    private String email;

    @NotBlank
    @Size(max = 16)
    private String phone;

    private Gender gender;

    private Instant created;

    private Instant updated;

    private LocalDate birthday;

    private Integer year;

    private Integer month;

    private Integer day;

    private Integer categoryId;

    private Integer propertyId;

    private String categoryPropertyId;

    private PropertyDto property;


}
