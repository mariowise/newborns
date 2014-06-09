/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities.core;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author sylar
 */
@Entity
public class Revival implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Boolean oxygen=false;
    
    private Boolean intubation=false;
    
    private Boolean bicarbonate=false;
    
    private Boolean positivePressureVentilation=false; 

    private Boolean cardiacMassage=false;
    
    private Boolean naloxone=false; 

    private Boolean adrenalin=false;
    
    @ManyToOne
    private Profile profile;
        
    @ManyToOne
    private Delivery delivery;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getOxygen() {
        return oxygen;
    }

    public void setOxygen(Boolean oxygen) {
        this.oxygen = oxygen;
    }

    public Boolean getIntubation() {
        return intubation;
    }

    public void setIntubation(Boolean intubation) {
        this.intubation = intubation;
    }

    public Boolean getBicarbonate() {
        return bicarbonate;
    }

    public void setBicarbonate(Boolean bicarbonate) {
        this.bicarbonate = bicarbonate;
    }

    public Boolean getPositivePressureVentilation() {
        return positivePressureVentilation;
    }

    public void setPositivePressureVentilation(Boolean positivePressureVentilation) {
        this.positivePressureVentilation = positivePressureVentilation;
    }

    public Boolean getCardiacMassage() {
        return cardiacMassage;
    }

    public void setCardiacMassage(Boolean cardiacMassage) {
        this.cardiacMassage = cardiacMassage;
    }

    public Boolean getNaloxone() {
        return naloxone;
    }

    public void setNaloxone(Boolean naloxone) {
        this.naloxone = naloxone;
    }

    public Boolean getAdrenalin() {
        return adrenalin;
    }

    public void setAdrenalin(Boolean adrenalin) {
        this.adrenalin = adrenalin;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
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
        if (!(object instanceof Revival)) {
            return false;
        }
        Revival other = (Revival) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.core.Revival[ id=" + id + " ]";
    }
    
}
