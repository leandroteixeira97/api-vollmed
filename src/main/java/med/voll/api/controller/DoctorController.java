package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDoctorDTO registerDoctorDTO) {
        doctorRepository.save(new Doctor(registerDoctorDTO));
        return new ResponseEntity<>("The doctor was registered successfully", HttpStatus.OK);
    }

    @GetMapping
    public Page<DoctorDTO> getDoctorsList(Pageable pagination) {
        return doctorRepository.findAllByExpiredFalse(pagination).map(DoctorDTO::new);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> updateDoctor(@RequestBody @Valid UpdateDoctorDTO updateDoctorDTO) {
        Doctor doctorOptional = doctorRepository.getReferenceById(updateDoctorDTO.id());
        doctorOptional.updateInformations(updateDoctorDTO);
        return new ResponseEntity<>("The doctor of id " + updateDoctorDTO.id() +
                " was updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping(path = "/{doctorId}")
    @Transactional
    public ResponseEntity<String> deleteDoctor(@PathVariable Long doctorId) {
        Doctor doctor = doctorRepository.getReferenceById(doctorId);
        doctor.setExpired(true);
        return new ResponseEntity<>("The doctor of id " + doctorId +
                " was deleted successfully!", HttpStatus.OK);
    }
}
