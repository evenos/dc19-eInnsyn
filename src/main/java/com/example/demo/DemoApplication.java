package com.example.demo;

import com.complexible.pinto.RDFMapper;
import com.complexible.stardog.plan.filter.functions.numeric.E;
import com.example.demo.domain.db.Person;
import com.example.demo.domain.graf.GraphPersonRepository;
import com.example.demo.domain.graf.NetworkGraphRepository;
import com.example.demo.domain.rdf.Journalpost;
import com.microsoft.spring.data.gremlin.query.GremlinTemplate;
import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.ResultSet;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.openrdf.rio.turtle.TurtleWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Objects;

@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class })
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

//	@Value("${file.difi_export.location}")
	private String file_location;

//	@Autowired
	PersonRepository personRepository;


    @Autowired
    private NetworkGraphRepository networkGraphRepository;

    @Autowired
    private GraphPersonRepository graphPersonRepository;

    @Autowired
    private GremlinTemplate gremlinTemplate;



	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApplication.class, args);

//		new DemoApplication().run(args);
	}

	@Override
	public void run(String... args) throws Exception {
//		demoJpa();
//		marshalPintoToTurtle();
//		readTurtleWithRDF4J();
        cosmodbDemo();

	}

	private void cosmodbDemo(){
//        NetworkGraph nettwork = new NetworkGraph(NETWORK_GRAPH_ID);
//
//        Long time = System.currentTimeMillis();
//
//        Person personA = new Person("C:" + time, "Person A:" + time, "30" );
//        Person personB = new Person("D:" + time, "Person B:" + time, "31" );
//
//        Relation relation = new Relation("K2:" + time, "Knows", personA, personB);


//        nettwork.getVertexes().add(personA);
//        nettwork.getVertexes().add(personB);
//        nettwork.getEdges().add(relation);
//
//        networkGraphRepository.save(nettwork);





        Client client =  gremlinTemplate.getGremlinClient();
        ResultSet rs = client.submit("g.V().haslabel('Person').out('Relation')");
        rs.stream().map(Objects::toString).forEach(System.out::println);

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
