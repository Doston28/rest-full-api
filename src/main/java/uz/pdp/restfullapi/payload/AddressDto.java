package uz.pdp.restfullapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDto {

    @NotNull(message = "The street must be entered")
    private String street;

    @NotNull(message = "The homeNumber must be entered")
    private String homeNumber;

}
