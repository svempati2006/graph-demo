package net.capitalinfotech.graph.model;

import java.util.ArrayList;
import java.util.List;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "mariaId")
@NodeEntity(label = "Phone")
public class PhoneGraph {

	public PhoneGraph() {
	}

	@Id
	private Long mariaId;
	private String number;

	@Relationship(type = "PHONE", direction = Relationship.INCOMING)
	private List<PassengerGraph> passengers = new ArrayList<>();

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getMariaId() {
		return mariaId;
	}

	public void setMariaId(Long mariaId) {
		this.mariaId = mariaId;
	}

	public List<PassengerGraph> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<PassengerGraph> passengers) {
		this.passengers = passengers;
	}

}
