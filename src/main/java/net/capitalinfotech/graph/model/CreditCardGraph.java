package net.capitalinfotech.graph.model;

import java.util.ArrayList;
import java.util.List;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="mariaId")
@NodeEntity(label="CreditCard")
public class CreditCardGraph {
	
    public CreditCardGraph() { }
    
    @Id 
    private Long mariaId;  
    private String cardType;
    private String number;
    private String expiration;
    private String accountHolder;
 
    
    @Relationship(type = "PAID_WITH",direction = Relationship.INCOMING)
    public List<PassengerGraph> passengerCards=new ArrayList<>();
    
// 	public Long getId() {  
//        return id;  
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    
	public List<PassengerGraph> getPassengerCards() {
		return passengerCards;
	}

	public void setPassengerCards(List<PassengerGraph> passengerCards) {
		this.passengerCards = passengerCards;
	}

	public Long getMariaId() {
		return mariaId;
	}

	public void setMariaId(Long mariaId) {
		this.mariaId = mariaId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
 
}
