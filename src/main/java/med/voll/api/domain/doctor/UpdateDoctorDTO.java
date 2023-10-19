package med.voll.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.Address;

public record UpdateDoctorDTO(
        @NotNull
        Long id,
        String name,
        String telephone,
        Address address) {
}
