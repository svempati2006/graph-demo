package net.capitalinfotech.graph.model;


import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="mariaId")
@NodeEntity(label="Document")
public class DocumentGraph {

	public DocumentGraph(){	}
	
	@Id
	private Long mariaId;  
	private String documentType;
	private String documentNumber;
	private String expirationDate;
	private String issuanceCountry;

	@Relationship(type = "HAS_A", direction = Relationship.INCOMING)
	private List<PassengerGraph> passengers=new ArrayList<>();
	
	public Long getMariaId() {
		return mariaId;
	}

	public void setMariaId(Long mariaId) {
		this.mariaId = mariaId;
	}

//	public Long getId() {  
//        return id;  
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

	public List<PassengerGraph> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<PassengerGraph> passengers) {
		this.passengers = passengers;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getIssuanceCountry() {
		return issuanceCountry;
	}

	public void setIssuanceCountry(String issuanceCountry) {
		this.issuanceCountry = issuanceCountry;
	}

}
