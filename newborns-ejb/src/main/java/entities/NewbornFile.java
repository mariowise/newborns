/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author pingeso
 */
@Entity
public class NewbornFile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private MotherFile motherFile;
    
    @NotNull
    @ManyToOne
    private DeliveryType deliveryType;
    
    private Float gestationWeeks;
    
    private String rut;
    
    private String firstName;
    
    private String secondName;
    
    private String firstLastName;

    private String secondLastName;
    
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;
    
    private Float size;
    
    private Float weight;
    
    private Float firstApgar;
    
    private Float secondApgar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public MotherFile getMotherFile() {
        return motherFile;
    }

    public void setMotherFile(MotherFile motherFile) {
        this.motherFile = motherFile;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Float getGestationWeeks() {
        return gestationWeeks;
    }

    public void setGestationWeeks(Float gestationWeeks) {
        this.gestationWeeks = gestationWeeks;
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getFirstApgar() {
        return firstApgar;
    }

    public void setFirstApgar(Float firstApgar) {
        this.firstApgar = firstApgar;
    }

    public Float getSecondApgar() {
        return secondApgar;
    }

    public void setSecondApgar(Float secondApgar) {
        this.secondApgar = secondApgar;
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
        if (!(object instanceof NewbornFile)) {
            return false;
        }
        NewbornFile other = (NewbornFile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.NewbornFile[ id=" + id + " ]";
    }
    
}
