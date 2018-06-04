package net.capitalinfotech.graph.model;

import java.util.ArrayList;
import net.capitalinfotech.graph.model.PassengerGraph;
import java.util.List;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "mariaId")
@NodeEntity(label = "Email")
public class EmailGraph {

	public EmailGraph() {
	}

	@Id
	private Long mariaId;

	private String address;
	private String domain;

	@Relationship(type = "EMAIL-ID", direction = Relationship.INCOMING)
	public List<PassengerGraph> passengerEmails = new ArrayList<>();

//    public Long getId() {  
//        return id;  
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

	public Long getMariaId() {
		return mariaId;
	}

	public void setMariaId(Long mariaId) {
		this.mariaId = mariaId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public List<PassengerGraph> getPassengerEmails() {
		return passengerEmails;
	}

	public void setPassengerEmails(List<PassengerGraph> passengerEmails) {
		this.passengerEmails = passengerEmails;
	}

}
