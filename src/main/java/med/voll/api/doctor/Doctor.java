package med.voll.api.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;

@Table(name = "doctors")
@Entity(name = "Doctor")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
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

    public Doctor(RegisterDoctorDTO registerDoctorDTO) {
        this.name = registerDoctorDTO.name();
        this.email = registerDoctorDTO.email();
        this.telephone = registerDoctorDTO.telephone();
        this.crm = registerDoctorDTO.crm();
        this.specialty = registerDoctorDTO.specialty();
        this.address = registerDoctorDTO.address();
    }
}
