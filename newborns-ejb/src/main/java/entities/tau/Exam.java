/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities.tau;

import entities.ExamPhase;
import entities.ExamType;
import entities.core.Son;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author pingeso
 */
@Entity
public class Exam implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @JoinColumn(nullable = false)
    @ManyToOne
    private ExamType examType;
    
    @JoinColumn(nullable = false)
    @ManyToOne
    private ExamPhase phase;
    
    @JoinColumn(nullable = false)
    @ManyToOne
    private Son son;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created;
    
    @JoinColumn(nullable = true)
    @OneToOne(mappedBy = "exam")
    private OaeAabr oaeAabr;
    
    @JoinColumn(nullable = true)
    @OneToOne(mappedBy = "exam")
    private Abr abr;
    
    private String obs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExamType getExamType() {
        return examType;
    }

    public void setExamType(ExamType examType) {
        this.examType = examType;
    }

    public ExamPhase getPhase() {
        return phase;
    }

    public void setPhase(ExamPhase phase) {
        this.phase = phase;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public OaeAabr getOaeAabr() {
        return oaeAabr;
    }

    public void setOaeAabr(OaeAabr oaeAabr) {
        this.oaeAabr = oaeAabr;
    }

    public Abr getAbr() {
        return abr;
    }

    public void setAbr(Abr abr) {
        this.abr = abr;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Son getSon() {
        return son;
    }

    public void setSon(Son son) {
        this.son = son;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exam)) {
            return false;
        }
        Exam other = (Exam) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.tau.Exam[ id=" + id + " ]";
    }
    
}
