package net.capitalinfotech.rdbms.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address extends BaseEntityAudit {
    private static final long serialVersionUID = 1L;
    public Address() { }
    
    @Column(name = "line1", nullable = false)
    private String line1;
    
    @Column(name = "line2")
    private String line2;
    
    @Column(name = "line3")
    private String line3;
    
    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "postal_code")
    private String postalCode;

 	@ManyToMany(
        mappedBy = "addresses",
        targetEntity = Pnr.class
    )
    private Set<Pnr> pnrs = new HashSet<>();
    

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

    public Set<Pnr> getPnrs() {
        return pnrs;
    }

    public void setPnrs(Set<Pnr> pnrs) {
        this.pnrs = pnrs;
    }

    @Override
    public int hashCode() {
       return Objects.hash(this.line1, this.line2, this.line3, this.city, this.state, this.country, this.postalCode);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Address))
            return false;
        final Address other = (Address)obj;
        return Objects.equals(this.line1, other.line1)
                && Objects.equals(this.line2, other.line2)
                && Objects.equals(this.line3, other.line3)
                && Objects.equals(this.city, other.city)
                && Objects.equals(this.state, other.state)
                && Objects.equals(this.country, other.country)
                && Objects.equals(this.postalCode, other.postalCode);
    }
}
