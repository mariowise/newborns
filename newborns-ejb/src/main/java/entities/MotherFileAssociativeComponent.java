/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author pingeso
 */
@Entity
public class MotherFileAssociativeComponent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne
    private MotherFile motherFile;
    
    @JoinColumn(nullable = false)
    @ManyToOne
    private AssociativeComponent associativeComponent;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public MotherFile getMotherFile() {
        return motherFile;
    }

    public void setMotherFile(MotherFile motherFile) {
        this.motherFile = motherFile;
    }

    public AssociativeComponent getAssociativeComponent() {
        return associativeComponent;
    }

    public void setAssociativeComponent(AssociativeComponent associativeComponent) {
        this.associativeComponent = associativeComponent;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MotherFileAssociativeComponent)) {
            return false;
        }
        MotherFileAssociativeComponent other = (MotherFileAssociativeComponent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MotherFileAssociativeComponent[ id=" + id + " ]";
    }
    
}
