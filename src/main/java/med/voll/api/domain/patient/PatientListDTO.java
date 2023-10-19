package med.voll.api.domain.patient;

public record PatientListDTO(Long id, String name, String email, String cpf) {

    public PatientListDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }

}
