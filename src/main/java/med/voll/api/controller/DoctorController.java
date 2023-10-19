package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetailedDoctorDTO> register(@RequestBody @Valid RegisterDoctorDTO registerDoctorDTO, UriComponentsBuilder uriComponentsBuilder) {
        Doctor doctor = new Doctor(registerDoctorDTO);
        doctorRepository.save(doctor);

        URI uri = uriComponentsBuilder
                .path("/doctors/{id}")
                .buildAndExpand(doctor.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DetailedDoctorDTO(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorDTO>> getDoctorsList(Pageable pagination) {
        return ResponseEntity.ok(doctorRepository.findAllByExpiredFalse(pagination).map(DoctorDTO::new));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DetailedDoctorDTO> getDoctor(@PathVariable Long id) {
        Doctor doctor = doctorRepository.getReferenceById(id);

        return ResponseEntity.ok(new DetailedDoctorDTO(doctor));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetailedDoctorDTO> updateDoctor(@RequestBody @Valid UpdateDoctorDTO updateDoctorDTO) {
        Doctor doctor = doctorRepository.getReferenceById(updateDoctorDTO.id());
        doctor.updateInformations(updateDoctorDTO);

        return ResponseEntity.ok(new DetailedDoctorDTO(doctor));
    }

    @DeleteMapping(path = "/{doctorId}")
    @Transactional
    public ResponseEntity<String> deleteDoctor(@PathVariable Long doctorId) {
        Doctor doctor = doctorRepository.getReferenceById(doctorId);
        doctor.setExpired(true);
        return ResponseEntity.noContent().build();
    }
}
