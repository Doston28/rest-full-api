package uz.pdp.restfullapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    @NotNull(message = "The corpName must be entered")
    private String corpName;

    @NotNull(message = "The directorName must be entered")
    private String directorName;

    @NotNull(message = "The address must be entered")
    private Integer address;
}
