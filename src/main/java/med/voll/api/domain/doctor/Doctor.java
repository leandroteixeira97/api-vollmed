package med.voll.api.domain.doctor;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.address.Address;

@Table(name = "doctors")
@Entity(name = "Doctor")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String telephone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;
    private boolean expired;

    public Doctor(RegisterDoctorDTO registerDoctorDTO) {
        this.name = registerDoctorDTO.name();
        this.email = registerDoctorDTO.email();
        this.telephone = registerDoctorDTO.telephone();
        this.crm = registerDoctorDTO.crm();
        this.specialty = registerDoctorDTO.specialty();
        this.address = registerDoctorDTO.address();
    }

    public void updateInformations(UpdateDoctorDTO updateDoctorDTO) {
        this.name = updateDoctorDTO.name() != null ? updateDoctorDTO.name() : name;
        this.telephone = updateDoctorDTO.telephone() != null ? updateDoctorDTO.telephone() :  telephone;
        this.address = updateDoctorDTO.address() != null ? updateDoctorDTO.address() : address;
    }
}
