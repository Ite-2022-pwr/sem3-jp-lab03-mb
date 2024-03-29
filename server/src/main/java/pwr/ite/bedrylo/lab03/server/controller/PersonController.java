package pwr.ite.bedrylo.lab03.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.ite.bedrylo.lab03.server.dto.PersonDto;
import pwr.ite.bedrylo.lab03.server.model.Person;
import pwr.ite.bedrylo.lab03.server.repository.PersonRepository;
import pwr.ite.bedrylo.lab03.server.services.PersonService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/person")
public class PersonController {
    
    private final PersonRepository personRepository;
    private final PersonService personService;

    public PersonController(PersonRepository personRepository, PersonService personService) {
        this.personRepository = personRepository;
        this.personService = personService;
    }

    @GetMapping("/get/{pesel}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable String pesel) {
        ResponseEntity responseEntity = ResponseEntity.ok(personService.createDto(personRepository.findByPesel(pesel).orElse(null)));
        return responseEntity;
    }
    
    @GetMapping("/get/all")
    public ResponseEntity<Set<PersonDto>> getAllPersons() {
        return ResponseEntity.ok(personService.createDtoSet(new HashSet<>(personRepository.findAll())));
    }
    
    
    @PostMapping("/add")
    public ResponseEntity<PersonDto> addPerson(@RequestBody PersonDto personDto) {
        Person person = personService.createPerson(personDto);
        personRepository.saveAndFlush(person);
        return ResponseEntity.ok(personService.createDto(person));
    }
    
}
