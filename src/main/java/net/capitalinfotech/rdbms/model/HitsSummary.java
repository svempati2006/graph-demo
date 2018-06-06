package net.capitalinfotech.rdbms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "hits_summary")
public class HitsSummary implements Serializable {
    private static final long serialVersionUID = 3436310987156511552L;

    public HitsSummary() {
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
    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HitDetail> hitdetails = new ArrayList<HitDetail>();

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    @Column(name = "rule_hit_count")
    private Integer ruleHitCount;

    @Column(name = "hit_type")
    private String hitType;

    @Column(name = "wl_hit_count")
    private Integer watchListHitCount;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<HitDetail> getHitdetails() {
        return hitdetails;
    }

    public void setHitdetails(List<HitDetail> hitdetails) {
        this.hitdetails = hitdetails;
    }

    public String getHitType() {
        return hitType;
    }

    public void setHitType(String hitType) {
        this.hitType = hitType;
    }

    public Integer getRuleHitCount() {
        return ruleHitCount;
    }

    public void setRuleHitCount(Integer ruleHitCount) {
        this.ruleHitCount = ruleHitCount;
    }

    public Integer getWatchListHitCount() {
        return watchListHitCount;
    }

    public void setWatchListHitCount(Integer watchListHitCount) {
        this.watchListHitCount = watchListHitCount;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
