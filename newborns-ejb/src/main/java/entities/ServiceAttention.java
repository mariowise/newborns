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
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author sylar
 */
@Entity
public class ServiceAttention implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull(message = "Debe seleccionar un Servicio de Salud de origen")
    @JoinColumn(nullable = false)
    @ManyToOne
    private Service originHealthService;
    
    @NotNull(message = "Debe seleccionar el Servicio de Salud presente")
    @JoinColumn(nullable = false)
    @ManyToOne
    private Service healthService;
    
    @NotNull(message = "Debe seleccionar una Persona para registrar")
    @JoinColumn(nullable = false)
    @ManyToOne
    private Person admissionFile;
    
    @NotNull(message = "Debe indicar la fecha y hora de Admisión")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date registerDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Service getOriginHealthService() {
        return originHealthService;
    }

    public void setOriginHealthService(Service originHealthService) {
        this.originHealthService = originHealthService;
    }

    public Service getHealthService() {
        return healthService;
    }

    public void setHealthService(Service healthService) {
        this.healthService = healthService;
    }

    public Person getAdmissionFile() {
        return admissionFile;
    }

    public void setAdmissionFile(Person admissionFile) {
        this.admissionFile = admissionFile;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
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
        if (!(object instanceof ServiceAttention)) {
            return false;
        }
        ServiceAttention other = (ServiceAttention) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString().concat(" - ")
                .concat(registerDate.toString()).concat(" - ")
                .concat(admissionFile.getId().toString()).concat(" - ")
                .concat(healthService.toString());
    }
    
}
