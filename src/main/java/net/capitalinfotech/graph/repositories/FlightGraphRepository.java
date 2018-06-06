package net.capitalinfotech.graph.repositories;


import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.capitalinfotech.graph.model.FlightGraph;

@Repository
public interface FlightGraphRepository extends Neo4jRepository<FlightGraph,Long>{

	Collection<FlightGraph> findByFlightNumber(String flightNumber);
	FlightGraph findByMariaId(Long id);
	
	@Query("MATCH (f:Flight) WHERE flightNumber(f)={fn} AND flightDate(f)={fd} AND embarkation(f)={emb} AND debarkation(f)={deb} RETURN f")
	FlightGraph findFlightByCriteria(@Param("flightNumber") String fn,
			@Param("flightDate") String fd,@Param("embarkation") String emb,@Param("debarkation") String deb);
	
	@Query("MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r")
	void deleteAllObjects();
}
