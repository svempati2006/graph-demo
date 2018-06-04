package net.capitalinfotech.graph.model;

import java.util.ArrayList;
import java.util.List;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="mariaId")
@NodeEntity(label="Agency")
public class AgencyGraph {

	public AgencyGraph(){}
 
    @Id 
    private Long mariaId;
    private String name;
    private String location;
    private String identifier;
    private String country;
    private String phone;
    private String city;
    private String type;

    
    @Relationship(type = "BOOKED_AT",direction = Relationship.INCOMING)
    public List<PassengerGraph> passengerAgencies=new ArrayList<>();

    
	public List<PassengerGraph> getPassengerAgencies() {
		return passengerAgencies;
	}

	public void setPassengerAgencies(List<PassengerGraph> passengerAgencies) {
		this.passengerAgencies = passengerAgencies;
	}

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	public Long getMariaId() {
		return mariaId;
	}

	public void setMariaId(Long mariaId) {
		this.mariaId = mariaId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
