package com.example.demo;

import com.example.demo.domain.db.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Set<Person> findByNavn(String navn);

    Set<Person> findByAlder(String alder);

}
