package uz.pdp.restfullapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.restfullapi.entity.Company;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDto {

    @NotNull(message = "The name must be entered")
    private String name;

    @NotNull(message = "The company must be entered")
    private Integer company;
}
