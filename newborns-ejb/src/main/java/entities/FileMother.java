/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author pingeso
 */
@Entity
public class FileMother implements Serializable {
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
    
    private String age;
    
    private String presentation;
    
    private String gestationalAge;
    
    @JoinColumn(nullable = false)
    @ManyToOne
    private Service healthService;
    
    private String bloodGroup;
    
//    private Boolean rh;
//    
//    private Boolean coombs;
//    
//    private Boolean fatherEscort;
//    
//    private Boolean otherEscort;
    
    @ManyToMany
    private List<Illness> complications;
    
    
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getGestationalAge() {
        return gestationalAge;
    }

    public void setGestationalAge(String gestationalAge) {
        this.gestationalAge = gestationalAge;
    }

    public Service getHealthService() {
        return healthService;
    }

    public void setHealthService(Service healthService) {
        this.healthService = healthService;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

//    public Boolean isRh() {
//        return rh;
//    }
//
//    public void setRh(Boolean rh) {
//        this.rh = rh;
//    }
//
//    public Boolean isCoombs() {
//        return coombs;
//    }
//
//    public void setCoombs(Boolean coombs) {
//        this.coombs = coombs;
//    }
//
//    public Boolean isFatherEscort() {
//        return fatherEscort;
//    }
//
//    public void setFatherEscort(Boolean fatherEscort) {
//        this.fatherEscort = fatherEscort;
//    }
//
//    public Boolean isOtherEscort() {
//        return otherEscort;
//    }
//
//    public void setOtherEscort(Boolean otherEscort) {
//        this.otherEscort = otherEscort;
//    }

    public List<Illness> getComplications() {
        return complications;
    }

    public void setComplications(List<Illness> complications) {
        this.complications = complications;
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
        if (!(object instanceof FileMother)) {
            return false;
        }
        FileMother other = (FileMother) object;
        if ((this.fileCode == null && other.fileCode != null) || (this.fileCode != null && !this.fileCode.equals(other.fileCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return fileCode.toString();
    }
    
}
