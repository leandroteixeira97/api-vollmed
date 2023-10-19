package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid RegisterPatientDTO patientData) {
        patientRepository.save(new Patient(patientData));
    }

    @GetMapping
    public Page<PatientListDTO> listPatients(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        return patientRepository.findAllByExpiredFalse(pagination).map(PatientListDTO::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdatePatientDTO patientData) {
        Patient patient = patientRepository.getReferenceById(patientData.id());
        patient.updatePatientInformation(patientData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        Patient patient = patientRepository.getReferenceById(id);
        patient.delete();
    }


}
