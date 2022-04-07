package study.interview.codeExample.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Integer id;

    @NotBlank
    @Size(max = 32)
    private String name;

    private List<PropertyDto> properties;

    public String getPropertyNames() {
        if (Objects.isNull(properties)) {
            return "";
        }
        return properties.stream().map(PropertyDto::getName).collect(Collectors.joining(", "));
    }
}
