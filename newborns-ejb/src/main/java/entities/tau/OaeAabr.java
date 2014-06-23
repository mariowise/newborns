/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities.tau;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author pingeso
 */
@Entity
public class OaeAabr implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Boolean resultOd;
    
    private Boolean resultOi;
    
    private String finalResult;
    
    @JoinColumn(nullable = false)
    @OneToOne(cascade = CascadeType.PERSIST)
    private Exam exam;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getResultOd() {
        return resultOd;
    }

    public void setResultOd(Boolean resultOd) {
        this.resultOd = resultOd;
    }

    public Boolean getResultOi() {
        return resultOi;
    }

    public void setResultOi(Boolean resultOi) {
        this.resultOi = resultOi;
    }

    public String getFinalResult() {
        return finalResult;
    }

    public void setFinalResult(String finalResult) {
        this.finalResult = finalResult;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
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
        if (!(object instanceof OaeAabr)) {
            return false;
        }
        OaeAabr other = (OaeAabr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.tau.OaeAabr[ id=" + id + " ]";
    }
    
}
