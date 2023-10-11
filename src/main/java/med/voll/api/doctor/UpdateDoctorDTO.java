package med.voll.api.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.address.Address;

public record UpdateDoctorDTO(
        @NotNull
        Long id,
        String name,
        String telephone,
        Address address) {
}
