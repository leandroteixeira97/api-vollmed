package med.voll.api.doctor;

public record DoctorDTO(String nome, String email, String crm, Specialty specialty) {
    public DoctorDTO(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
