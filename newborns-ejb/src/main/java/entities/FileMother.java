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
