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
 * @author pingeso
 */
@Entity
public class Scheduling implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date nextDateAppointment;
    
    @NotNull
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date nextTimeAppointment;
    
    private String comments;
    
    @JoinColumn(nullable = false)
    @ManyToOne
    private SchedulingState schedulingState;
    
    @JoinColumn(nullable = false)
    @ManyToOne
    private NewbornFile newbornFile;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getNextDateAppointment() {
        return nextDateAppointment;
    }

    public void setNextDateAppointment(Date nextDateAppointment) {
        this.nextDateAppointment = nextDateAppointment;
    }

    public Date getNextTimeAppointment() {
        return nextTimeAppointment;
    }

    public void setNextTimeAppointment(Date nextTimeAppointment) {
        this.nextTimeAppointment = nextTimeAppointment;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public SchedulingState getSchedulingState() {
        return schedulingState;
    }

    public void setSchedulingState(SchedulingState schedulingState) {
        this.schedulingState = schedulingState;
    }

    public NewbornFile getNewbornFile() {
        return newbornFile;
    }

    public void setNewbornFile(NewbornFile newbornFile) {
        this.newbornFile = newbornFile;
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
        if (!(object instanceof Scheduling)) {
            return false;
        }
        Scheduling other = (Scheduling) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Scheduling[ id=" + id + " ]";
    }
    
}
