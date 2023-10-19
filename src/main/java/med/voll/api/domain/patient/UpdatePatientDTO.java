package med.voll.api.domain.patient;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.Address;

public record UpdatePatientDTO(
        @NotNull
        Long id,
        String name,
        String telephone,
        Address address) {
}
