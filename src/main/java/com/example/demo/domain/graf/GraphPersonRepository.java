package com.example.demo.domain.graf;

import com.microsoft.spring.data.gremlin.repository.GremlinRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface GraphPersonRepository extends GremlinRepository<Person, String> {

    Stream<Person> findByName(String name);
}
