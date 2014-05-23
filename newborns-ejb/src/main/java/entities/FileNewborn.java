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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author pingeso
 */
@Entity 
public class FileNewborn implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileCode;
    
    @JoinColumn(nullable = false)
    @OneToOne
    private File file;
    
    @JoinColumn(nullable = false)
    @OneToOne
    private Person person;
   
    @ManyToOne
    private FileMother motherFile;
    
    @JoinColumn(nullable = false)
    @ManyToOne
    private DeliveryType deliveryType;
    
    private Float gestationWeeks;
    
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;
    
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date timeOfBirth;
    
    private Float height;
    
    private Float weight;
    
    private Float firstApgar;
    
    private Float secondApgar;
    
    public Long getFileCode() {
        return fileCode;
    }

    public void setFileCode(Long fileCode) {
        this.fileCode = fileCode;
    }


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getTimeOfBirth() {
        return timeOfBirth;
    }

    public void setTimeOfBirth(Date timeOfBirth) {
        this.timeOfBirth = timeOfBirth;
    }

    public FileMother getMotherFile() {
        return motherFile;
    }

    public void setMotherFile(FileMother motherFile) {
        this.motherFile = motherFile;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
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
        hash += (fileCode != null ? fileCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FileNewborn)) {
            return false;
        }
        FileNewborn other = (FileNewborn) object;
        if ((this.fileCode == null && other.fileCode != null) || (this.fileCode != null && !this.fileCode.equals(other.fileCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.motherFile.getFileCode().toString();
    }
    
}
