package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.DoctorDTO;
import med.voll.api.doctor.DoctorRepository;
import med.voll.api.doctor.RegisterDoctorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return doctorRepository.findAll(pagination).map(DoctorDTO::new);
    }
}
