package net.capitalinfotech.graph.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="mariaId")
@NodeEntity(label="Address")
public class AddressGraph {
    public AddressGraph() { }
    
    @Id 
    private Long mariaId;
	//private Long id;
    private String line1;
    private String line2;
    private String line3;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    
    
    @Relationship(type = "LIVED_AT",direction = Relationship.INCOMING)
    public List<PassengerGraph> passengerAddresses=new ArrayList<>();
    
	public Long getMariaId() {
		return mariaId;
	}
	public void setMariaId(Long mariaId) {
		this.mariaId = mariaId;
	}
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	public String getLine3() {
		return line3;
	}
	public void setLine3(String line3) {
		this.line3 = line3;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public List<PassengerGraph> getPassengerAddresses() {
		return passengerAddresses;
	}
	public void setPassengerAddresses(List<PassengerGraph> passengerAddresses) {
		this.passengerAddresses = passengerAddresses;
	}

}
