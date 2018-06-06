package net.capitalinfotech.rdbms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "document")
public class Document implements Serializable {
	private static final long serialVersionUID = 1L;

	public Document() {
	}

    @Id  
    @GeneratedValue(strategy = GenerationType.AUTO)  
    @Basic(optional = false)  
    @Column(name = "id", nullable = false, columnDefinition = "bigint unsigned")  
    protected Long id;  
  
    public Long getId() {  
        return id;  
    }

    public void setId(Long id) {
        this.id = id;
    }
	@Column(name = "document_type", length = 3, nullable = false)
	private String documentType;

	@Column(name = "document_number", nullable = false)
	private String documentNumber;

	@Column(name = "expiration_date")
	@Temporal(TemporalType.DATE)
	private Date expirationDate;

	@Column(name = "issuance_date")
	@Temporal(TemporalType.DATE)
	private Date issuanceDate;

	@Column(name = "issuance_country")
	private String issuanceCountry;

	@ManyToOne
	private Passenger passenger;

    /** calculated field */
    @Column(name = "days_valid")
    private Integer numberOfDaysValid;
 
    
	public Integer getNumberOfDaysValid() {
		return numberOfDaysValid;
	}

	public void setNumberOfDaysValid(Integer numberOfDaysValid) {
		this.numberOfDaysValid = numberOfDaysValid;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public Date getIssuanceDate() {
		return issuanceDate;
	}

	public void setIssuanceDate(Date issuanceDate) {
		this.issuanceDate = issuanceDate;
	}

	public String getIssuanceCountry() {
		return issuanceCountry;
	}

	public void setIssuanceCountry(String issuanceCountry) {
		this.issuanceCountry = issuanceCountry;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	@Override
	public int hashCode() {
		return Objects.hash( this.documentNumber
				);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Document))
			return false;
		final Document other = (Document) obj;
		return Objects.equals(this.documentNumber, other.documentNumber);
	}
}
