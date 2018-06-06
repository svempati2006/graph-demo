package net.capitalinfotech.graph.model;

import java.util.ArrayList;
import java.util.List;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="mariaId")
@NodeEntity(label="Flight")
public class FlightGraph {

	@Id
    private Long mariaId;
    private String flightNumber;
    private String flightDate;
    private String embarkation;
    private String debarkation;
    private String embarkCountry;
    private String debarkCountry;

   
    public FlightGraph(Long mId,String fn,String fd,String emb,String deb,String emC,String dbC){
    	this.mariaId=mId;
    	this.flightNumber=fn;
    	this.flightDate=fd;
    	this.embarkation=emb;
    	this.debarkation=deb;
    	this.embarkCountry=emC;
    	this.debarkCountry=dbC;
    }
    public FlightGraph(){
    	
    }

	@Relationship(type = "FLEW_ON", direction = Relationship.INCOMING)
	private List<PassengerGraph> passengers=new ArrayList<>();


	public Long getMariaId() {
		return mariaId;
	}
	public void setMariaId(Long mariaId) {
		this.mariaId = mariaId;
	}

	public String getEmbarkation() {
		return embarkation;
	}

	public void setEmbarkation(String embarkation) {
		this.embarkation = embarkation;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}

	public String getDebarkation() {
		return debarkation;
	}

	public void setDebarkation(String debarkation) {
		this.debarkation = debarkation;
	}

	public String getEmbarkCountry() {
		return embarkCountry;
	}

	public void setEmbarkCountry(String embarkCountry) {
		this.embarkCountry = embarkCountry;
	}

	public String getDebarkCountry() {
		return debarkCountry;
	}

	public void setDebarkCountry(String debarkCountry) {
		this.debarkCountry = debarkCountry;
	}
	public List<PassengerGraph> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<PassengerGraph> passengers) {
		this.passengers = passengers;
	}

}
