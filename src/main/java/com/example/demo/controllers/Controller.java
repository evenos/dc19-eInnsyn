package com.example.demo.controllers;

import com.example.demo.domain.db.Person;
import com.example.demo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class Controller {

    @Autowired
    PersonRepository personRepository;

    @GetMapping(path = "/hello")
    @ResponseBody
    public Base hello(){
        return new Base(new Name("Even"));
    }


    @GetMapping(path = "/hello/{navn}")
    @ResponseBody
    public Set<Person> helloYou(@PathVariable("navn") String navn){

        return personRepository.findByNavn(navn);
    }

}
