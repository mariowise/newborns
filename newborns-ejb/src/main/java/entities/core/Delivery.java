/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities.core;

import entities.Account;
import entities.DeliveryType;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author pingeso
 */
@Entity
public class Delivery implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date = new Date();
    
    @NotNull
    @Temporal(TemporalType.TIME) 
    private Date time = new Date();
    
    private Boolean diabetes;
    
    private Boolean hypertension;
    
    private Boolean rhSensibility;
    
    private Boolean brokenMembrane;
    
    private Boolean ovularInfection;
    
    private Boolean dppni;
    
    private Boolean earlyPlacenta;
    
    private Boolean brokenUterus;
    
    private Boolean fetusPain;
    
    private Boolean laceAccident;
    
    private Boolean rciu;
    
    private Boolean anotherPathology;
    
    private Boolean vdrl;
    
    private Boolean polidependencia;
    
    private Boolean risk;
    
    @ManyToOne
    private Mother mother;
    
    @ManyToOne
    private DeliveryType deliveryType;
    
    @ManyToOne
    private Account createdBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Boolean isDiabetes() {
        return diabetes;
    }

    public void setDiabetes(Boolean diabetes) {
        this.diabetes = diabetes;
    }

    public Boolean isHypertension() {
        return hypertension;
    }

    public void setHypertension(Boolean hypertension) {
        this.hypertension = hypertension;
    }

    public Boolean isRhSensibility() {
        return rhSensibility;
    }

    public void setRhSensibility(Boolean rhSensibility) {
        this.rhSensibility = rhSensibility;
    }

    public Boolean isBrokenMembrane() {
        return brokenMembrane;
    }

    public void setBrokenMembrane(Boolean brokenMembrane) {
        this.brokenMembrane = brokenMembrane;
    }

    public Boolean isOvularInfection() {
        return ovularInfection;
    }

    public void setOvularInfection(Boolean ovularInfection) {
        this.ovularInfection = ovularInfection;
    }

    public Boolean isDppni() {
        return dppni;
    }

    public void setDppni(Boolean dppni) {
        this.dppni = dppni;
    }

    public Boolean isEarlyPlacenta() {
        return earlyPlacenta;
    }

    public void setEarlyPlacenta(Boolean earlyPlacenta) {
        this.earlyPlacenta = earlyPlacenta;
    }

    public Boolean isBrokenUterus() {
        return brokenUterus;
    }

    public void setBrokenUterus(Boolean brokenUterus) {
        this.brokenUterus = brokenUterus;
    }

    public Boolean isFetusPain() {
        return fetusPain;
    }

    public void setFetusPain(Boolean fetusPain) {
        this.fetusPain = fetusPain;
    }

    public Boolean isLaceAccident() {
        return laceAccident;
    }

    public void setLaceAccident(Boolean laceAccident) {
        this.laceAccident = laceAccident;
    }

    public Boolean isRciu() {
        return rciu;
    }

    public void setRciu(Boolean rciu) {
        this.rciu = rciu;
    }

    public Boolean isAnotherPathology() {
        return anotherPathology;
    }

    public void setAnotherPathology(Boolean anotherPathology) {
        this.anotherPathology = anotherPathology;
    }

    public Boolean isVdrl() {
        return vdrl;
    }

    public void setVdrl(Boolean vdrl) {
        this.vdrl = vdrl;
    }

    public Boolean isPolidependencia() {
        return polidependencia;
    }

    public void setPolidependencia(Boolean polidependencia) {
        this.polidependencia = polidependencia;
    }

    public Mother getMother() {
        return mother;
    }

    public void setMother(Mother mother) {
        this.mother = mother;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Account getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Account createdBy) {
        this.createdBy = createdBy;
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
        if (!(object instanceof Delivery)) {
            return false;
        }
        Delivery other = (Delivery) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.core.Delivery[ id=" + id + " ]";
    }
    
}
