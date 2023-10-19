package med.voll.api.doctor;

import med.voll.api.address.Address;

public record DetailedDoctorDTO(
        Long id,
        String name,
        String email,
        String crm,
        String telephone,
        Specialty specialty,
        Address address
) {

    public DetailedDoctorDTO(Doctor doctor) {
        this(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getCrm(),
                doctor.getTelephone(),
                doctor.getSpecialty(),
                doctor.getAddress()
            );
    }

}
