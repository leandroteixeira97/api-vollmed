package med.voll.api.domain.doctor;

public record DoctorDTO(Long id, String nome, String email, String crm, Specialty specialty) {
    public DoctorDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
