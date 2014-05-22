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
import javax.persistence.Temporal;

/**
 *
 * @author pingeso
 */
@Entity
public class MotherFile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileCode;
    
    public Long getFileCode() {
        return fileCode;
    }

    public void setFileCode(Long fileCode) {
        this.fileCode = fileCode;
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
        if (!(object instanceof MotherFile)) {
            return false;
        }
        MotherFile other = (MotherFile) object;
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
