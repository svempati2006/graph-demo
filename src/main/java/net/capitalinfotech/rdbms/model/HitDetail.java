package net.capitalinfotech.rdbms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "hit_detail")
public class HitDetail implements Serializable {
    private static final long serialVersionUID = 5219262569468670275L;

    public HitDetail() {
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
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "hits_summary_id", nullable = false, referencedColumnName = "id")
    private HitsSummary parent;

    @Column(name = "title", nullable = false)
    private String Title;

    @Column(name = "description")
    private String Description;

    @Column(name = "hit_type", nullable = false, length = 3)
    private String hitType;
    /**
     * String representation of matched conditions; it can be split into
     * String[]
     */
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "cond_text", columnDefinition = "TEXT NULL")
    private String ruleConditions;

    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "rule_id", nullable = false)
    private Long ruleId;

    public HitsSummary getParent() {
        return parent;
    }

    public void setParent(HitsSummary parent) {
        this.parent = parent;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getRuleConditions() {
        return ruleConditions;
    }

    public void setRuleConditions(String ruleConditions) {
        this.ruleConditions = ruleConditions;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getHitType() {
        return hitType;
    }

    public void setHitType(String hitType) {
        this.hitType = hitType;
    }

}
