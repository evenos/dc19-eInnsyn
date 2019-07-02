package com.example.demo.domain.db;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "tbl_person")

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Person {

    public Person(String navn, String alder){
        this.navn = navn;
        this.alder = alder;
    }

    @Id
    @GeneratedValue
    private long id;

    private String navn;

    private String alder;



}
