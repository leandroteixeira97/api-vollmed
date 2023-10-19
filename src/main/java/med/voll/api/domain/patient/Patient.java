package med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String telephone;

    private String cpf;

    @Embedded
    private Address address;

    private Boolean expired;

    public Patient(RegisterPatientDTO patientData) {
        this.expired = true;
        this.name = patientData.name();
        this.email = patientData.email();
        this.telephone = patientData.telephone();
        this.cpf = patientData.cpf();
        this.address = patientData.address();
    }

    public void updatePatientInformation(UpdatePatientDTO patientData) {
        this.name = patientData.name() != null ? patientData.name() : name;
        this.telephone = patientData.telephone() != null ? patientData.telephone() : telephone;
        this.address = patientData.address() != null ? patientData.address() : address;
    }

    public void delete() {
        this.expired = false;
    }
}
