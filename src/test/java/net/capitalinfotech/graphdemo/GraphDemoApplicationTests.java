package net.capitalinfotech.graphdemo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import net.capitalinfotech.graph.model.FlightGraph;
import net.capitalinfotech.graph.repositories.FlightGraphRepository;
import net.capitalinfotech.config.MultipleDataSourceConfiguration;
import net.capitalinfotech.rdbms.model.Flight;
import net.capitalinfotech.rdbms.repositories.FlightRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { MultipleDataSourceConfiguration.class })
@SpringBootTest
public class GraphDemoApplicationTests {
	
	@Autowired
	private Session session;
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private FlightGraphRepository instance;
	//@Test
	public void contextLoads() {
}
	@Test
	@Transactional
	public void testPassengerObejetsFromRdbms(){
		List<Flight> flights=flightRepository.findAll();
		for(Flight f:flights){
			System.out.println(" Flight "+f.getFullFlightNumber());
			FlightGraph fg=new FlightGraph();
			fg.setMariaId(f.getId());
			fg.setDebarkation(f.getDestination());
			fg.setDebarkCountry(f.getDestinationCountry());
			fg.setEmbarkation(f.getOrigin());
			fg.setEmbarkCountry(f.getOriginCountry());
			fg.setFlightDate(f.getFlightDate().toString());
			fg.setFlightNumber(f.getFullFlightNumber());
			instance.save(fg);
			System.out.println(" Flight Graph Saved "+fg.getFlightNumber());
		}
	}



}
