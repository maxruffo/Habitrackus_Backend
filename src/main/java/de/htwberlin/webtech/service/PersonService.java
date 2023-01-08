package de.htwberlin.webtech.service;

import de.htwberlin.webtech.persistence.PersonEntity;
import de.htwberlin.webtech.persistence.PersonRepository;
import de.htwberlin.webtech.web.api.Person;
import de.htwberlin.webtech.web.api.PersonManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public List<Person> findAll(){
        List<PersonEntity> persons = personRepository.findAll();
        return persons.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Person findById(Long id){
        var personEntity = personRepository.findById(id);
        return personEntity.map(this::transformEntity).orElse(null);
    }
    public Person create(PersonManipulationRequest request){
        var personEntity = new PersonEntity(request.getFirstname(),request.getLastname(),request.getEmail(),request.getPassword());
        personEntity = personRepository.save(personEntity);
        return transformEntity(personEntity);
    }

    public Person update(Long id,PersonManipulationRequest request){
        var personEntityOptional = personRepository.findById(id);
        if(personEntityOptional.isEmpty()){
            return null;
        }
        var personEntity = personEntityOptional.get();
        personEntity.setFirstname(request.getFirstname());
        personEntity.setLastname(request.getLastname());
        personEntity.setEmail(request.getEmail());
        personEntity.setPassword(request.getPassword());
        personEntity = personRepository.save(personEntity);

        return transformEntity(personEntity);
    }

    public boolean deleteById(Long id){
        if(!personRepository.existsById(id)){
            return false;
        }
        personRepository.deleteById(id);
        return true;
    }

    private Person transformEntity(PersonEntity personEntity){
        return new Person(
                personEntity.getId(),
                personEntity.getFirstname(),
                personEntity.getLastname(),
                personEntity.getEmail(),
                personEntity.getPassword()
        );


    }

}
