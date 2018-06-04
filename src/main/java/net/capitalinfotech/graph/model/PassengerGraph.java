package net.capitalinfotech.graph.model;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "mariaId")
@NodeEntity(label = "Passenger")
public class PassengerGraph {

	public PassengerGraph() {

	}

	public PassengerGraph(String fName, String lName, String gender, String dob) {
		this.firstName = fName;
		this.lastName = lName;
		this.gender = gender;
		this.dob = dob;
	}

	@Id
	private Long mariaId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String citizenshipCountry;
	private String residencyCountry;
	private String dob;

	public Long getMariaId() {
		return mariaId;
	}

	public void setMariaId(Long mariaId) {
		this.mariaId = mariaId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCitizenshipCountry() {
		return citizenshipCountry;
	}

	public void setCitizenshipCountry(String citizenshipCountry) {
		this.citizenshipCountry = citizenshipCountry;
	}

	public String getResidencyCountry() {
		return residencyCountry;
	}

	public void setResidencyCountry(String residencyCountry) {
		this.residencyCountry = residencyCountry;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

}
