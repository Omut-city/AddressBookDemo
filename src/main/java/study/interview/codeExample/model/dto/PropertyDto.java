package study.interview.codeExample.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDto {

    private Integer id;

    private String name;

    private Integer categoryId;

    private String categoryName;

    public String getCategoryPropertyId() {
        return categoryId + "_" + id;
    }
}
