package med.voll.api.doctor;

public record DoctorDTO(Long id, String nome, String email, String crm, Specialty specialty) {
    public DoctorDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
