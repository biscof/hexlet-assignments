package exercise.controller;

import exercise.dto.PersonDto;
import exercise.model.Person;
import exercise.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {

    // Автоматически заполняем значение поля
    private final PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person getPerson(@PathVariable long id) {
        return personRepository.findById(id).orElseThrow();
    }

    @GetMapping(path = "")
    public Iterable<Person> getPeople() {
        return this.personRepository.findAll();
    }

    // BEGIN
    @PostMapping("")
    public void addPerson(@RequestBody PersonDto personDto) {
        Person person = new Person();
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        personRepository.save(person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
        Person person = personRepository.findById(id).orElseThrow();
        person.setId(id);
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        personRepository.save(person);
    }
    // END
}
