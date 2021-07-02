package uz.pdp.restfullapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.restfullapi.entity.Address;
import uz.pdp.restfullapi.entity.Department;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkerDto {

    @NotNull(message = "The name must be entered")
    private String name;

    @NotNull(message = "The phoneNumber must be entered")
    private String phoneNumber;

    @NotNull(message = "The address must be entered")
    private Integer address;

    @NotNull(message = "The department must be entered")
    private Integer department;
}
