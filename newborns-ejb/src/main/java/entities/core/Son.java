/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities.core;

import entities.Gender;
import entities.YearsCount;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author pingeso
 */
@Entity
public class Son implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private YearsCount year;
    
    private int ticket;
    
    @ManyToOne
    private Gender gender;
    
    private int weight;
    
    private int size;
    
    private int skullPerimeter;
    
    private int apgar1;
    
    private int apgar5;
    
    private int apgar10;
    
    private Boolean vitk;
    
    private Boolean bcg;
    
    private int weightPlacenta;
    
    private Boolean cry;
    
    private int attachedTime;
    
    private int tempAxilar;
    
    private int tempRectal;
    
    private Boolean of;
    
    private Boolean og;
    
    private Boolean ot;
    
    private int secretion;
    
    private String secretionColour;
    
    private String consistency;
    
    private Boolean gas;
    
    private Boolean extremePremature;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Profile profile;
    
    @ManyToOne
    private Mother mother;
    
    @ManyToOne
    private Delivery delivery;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public YearsCount getYear() {
        return year;
    }

    public void setYear(YearsCount year) {
        this.year = year;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSkullPerimeter() {
        return skullPerimeter;
    }

    public void setSkullPerimeter(int skullPerimeter) {
        this.skullPerimeter = skullPerimeter;
    }

    public int getApgar1() {
        return apgar1;
    }

    public void setApgar1(int apgar1) {
        this.apgar1 = apgar1;
    }

    public int getApgar5() {
        return apgar5;
    }

    public void setApgar5(int apgar5) {
        this.apgar5 = apgar5;
    }

    public int getApgar10() {
        return apgar10;
    }

    public void setApgar10(int apgar10) {
        this.apgar10 = apgar10;
    }

    public Boolean getVitk() {
        return vitk;
    }

    public void setVitk(Boolean vitk) {
        this.vitk = vitk;
    }

    public Boolean getBcg() {
        return bcg;
    }

    public void setBcg(Boolean bcg) {
        this.bcg = bcg;
    }

    public int getWeightPlacenta() {
        return weightPlacenta;
    }

    public void setWeightPlacenta(int weightPlacenta) {
        this.weightPlacenta = weightPlacenta;
    }

    public Boolean getCry() {
        return cry;
    }

    public void setCry(Boolean cry) {
        this.cry = cry;
    }

    public int getAttachedTime() {
        return attachedTime;
    }

    public void setAttachedTime(int attachedTime) {
        this.attachedTime = attachedTime;
    }

    public int getTempAxilar() {
        return tempAxilar;
    }

    public void setTempAxilar(int tempAxilar) {
        this.tempAxilar = tempAxilar;
    }

    public int getTempRectal() {
        return tempRectal;
    }

    public void setTempRectal(int tempRectal) {
        this.tempRectal = tempRectal;
    }

    public Boolean getOf() {
        return of;
    }

    public void setOf(Boolean of) {
        this.of = of;
    }

    public Boolean getOg() {
        return og;
    }

    public void setOg(Boolean og) {
        this.og = og;
    }

    public Boolean getOt() {
        return ot;
    }

    public void setOt(Boolean ot) {
        this.ot = ot;
    }

    public int getSecretion() {
        return secretion;
    }

    public void setSecretion(int secretion) {
        this.secretion = secretion;
    }

    public String getSecretionColour() {
        return secretionColour;
    }

    public void setSecretionColour(String secretionColour) {
        this.secretionColour = secretionColour;
    }

    public String getConsistency() {
        return consistency;
    }

    public void setConsistency(String consistency) {
        this.consistency = consistency;
    }

    public Boolean getGas() {
        return gas;
    }

    public void setGas(Boolean gas) {
        this.gas = gas;
    }

    public Boolean getExtremePremature() {
        return extremePremature;
    }

    public void setExtremePremature(Boolean extremePremature) {
        this.extremePremature = extremePremature;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Mother getMother() {
        return mother;
    }

    public void setMother(Mother mother) {
        this.mother = mother;
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
        if (!(object instanceof Son)) {
            return false;
        }
        Son other = (Son) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.core.Son[ id=" + id + " ]";
    }
    
}
