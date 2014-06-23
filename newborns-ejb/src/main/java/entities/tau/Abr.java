/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities.tau;

import java.io.Serializable;
import javax.persistence.Column;
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
public class Abr implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(precision = 10, scale = 0) 
    private double onda1_od;
    
    @Column(precision = 10, scale = 0)
    private double onda3_od;
    
    @Column(precision = 10, scale = 0)
    private double onda5_od;
    
    @Column(precision = 10, scale = 0)
    private double tcc_od;
    
    @Column(precision = 10, scale = 0)
    private double tcp_od;
    
    @Column(precision = 10, scale = 0)
    private double uondav_od;
    
    @Column(precision = 10, scale = 0)
    private double onda1_oi;
    
    @Column(precision = 10, scale = 0)
    private double onda3_oi;
    
    @Column(precision = 10, scale = 0)
    private double onda5_oi;
    
    @Column(precision = 10, scale = 0)
    private double tcc_oi;
    
    @Column(precision = 10, scale = 0)
    private double tcp_oi;
    
    @Column(precision = 10, scale = 0)
    private double uondav_oi;
    
    @Column(precision = 10, scale = 0)
    private double od;
    
    @Column(precision = 10, scale = 0)
    private double oi;
    
    private String finalResult;
    
    @JoinColumn(nullable = false)
    @OneToOne
    private Exam exam;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getOnda1_od() {
        return onda1_od;
    }

    public void setOnda1_od(double onda1_od) {
        this.onda1_od = onda1_od;
    }

    public double getOnda3_od() {
        return onda3_od;
    }

    public void setOnda3_od(double onda3_od) {
        this.onda3_od = onda3_od;
    }

    public double getOnda5_od() {
        return onda5_od;
    }

    public void setOnda5_od(double onda5_od) {
        this.onda5_od = onda5_od;
    }

    public double getTcc_od() {
        return tcc_od;
    }

    public void setTcc_od(double tcc_od) {
        this.tcc_od = tcc_od;
    }

    public double getTcp_od() {
        return tcp_od;
    }

    public void setTcp_od(double tcp_od) {
        this.tcp_od = tcp_od;
    }

    public double getUondav_od() {
        return uondav_od;
    }

    public void setUondav_od(double uondav_od) {
        this.uondav_od = uondav_od;
    }

    public double getOnda1_oi() {
        return onda1_oi;
    }

    public void setOnda1_oi(double onda1_oi) {
        this.onda1_oi = onda1_oi;
    }

    public double getOnda3_oi() {
        return onda3_oi;
    }

    public void setOnda3_oi(double onda3_oi) {
        this.onda3_oi = onda3_oi;
    }

    public double getOnda5_oi() {
        return onda5_oi;
    }

    public void setOnda5_oi(double onda5_oi) {
        this.onda5_oi = onda5_oi;
    }

    public double getTcc_oi() {
        return tcc_oi;
    }

    public void setTcc_oi(double tcc_oi) {
        this.tcc_oi = tcc_oi;
    }

    public double getTcp_oi() {
        return tcp_oi;
    }

    public void setTcp_oi(double tcp_oi) {
        this.tcp_oi = tcp_oi;
    }

    public double getUondav_oi() {
        return uondav_oi;
    }

    public void setUondav_oi(double uondav_oi) {
        this.uondav_oi = uondav_oi;
    }

    public double getOd() {
        return od;
    }

    public void setOd(double od) {
        this.od = od;
    }

    public double getOi() {
        return oi;
    }

    public void setOi(double oi) {
        this.oi = oi;
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
        if (!(object instanceof Abr)) {
            return false;
        }
        Abr other = (Abr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.tau.Abr[ id=" + id + " ]";
    }
    
}
