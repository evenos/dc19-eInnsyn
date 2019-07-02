package com.example.demo;

import com.complexible.pinto.RDFMapper;
import com.complexible.stardog.plan.filter.functions.numeric.E;
import com.example.demo.domain.db.Person;
import com.example.demo.domain.rdf.Journalpost;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.openrdf.rio.turtle.TurtleWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Value("${file.difi_export.location}")
	private String file_location;

	@Autowired
	PersonRepository personRepository;

	public static void main(String[] args) throws Exception {
//		SpringApplication.run(DemoApplication.class, args);

		new DemoApplication().run(args);
	}

	@Override
	public void run(String... args) throws Exception {
//		demoJpa();
//		marshalPintoToTurtle();
//		readTurtleWithRDF4J();


	}

	private void demoJpa() {
		System.out.println("Test");

		personRepository.save(new Person( "Adam", "2"));
		personRepository.save(new Person( "Silje", "3"));

		System.out.println(personRepository.findByNavn("Adam"));
	}

	private void marshalPintoToTurtle() {

		Journalpost journalpost = new Journalpost(
				"http://data.einnsyn.no/noark4/Journalpost--991825827--2013--1289--1--2013",
				1,
				"navn",
				"skjult",
				"liste"
		);

		org.openrdf.model.Model model = RDFMapper.create().writeValue(journalpost);
		model.setNamespace("arkiv", "http://www.arkivverket.no/standarder/noark5/arkivstruktur/");


		TurtleWriter tw = new TurtleWriter(System.out);

		tw.startRDF();
		model.getNamespaces().stream().forEach(ns -> tw.handleNamespace(ns.getPrefix(), ns.getName()));
		model.stream().forEach(tw::handleStatement);
		tw.endRDF();
	}

	private void readTurtleWithRDF4J() throws IOException {
		System.out.println("=======================================");
		System.out.println("Starting to parse file at " + file_location);
		System.out.println("=======================================");


		Resource turtle_file = new FileSystemResource(file_location);

		Model model = Rio.parse(turtle_file.getInputStream(), "", RDFFormat.TURTLE);


		for (Statement statement: model) {
			System.out.println(statement);
		}

		System.out.println("======================");
		System.out.println("Finished parsing file!");
		System.out.println("======================");
	}
}
