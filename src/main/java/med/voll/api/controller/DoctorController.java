package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid RegisterDoctorDTO registerDoctorDTO) {
        doctorRepository.save(new Doctor(registerDoctorDTO));
    }

    @GetMapping
    public Page<DoctorDTO> getDoctorsList(Pageable pagination) {
        return doctorRepository.findAllByExpiredFalse(pagination).map(DoctorDTO::new);
    }

    @PutMapping
    @Transactional
    public void updateDoctor(@RequestBody @Valid UpdateDoctorDTO updateDoctorDTO) {
        Doctor doctorOptional = doctorRepository.getReferenceById(updateDoctorDTO.id());

        doctorOptional.updateInformations(updateDoctorDTO);
    }

    @DeleteMapping(path = "/{doctorId}")
    @Transactional
    public void deleteDoctor(@PathVariable Long doctorId) {
        Doctor doctor = doctorRepository.getReferenceById(doctorId);
        doctor.setExpired(true);
    }
}
