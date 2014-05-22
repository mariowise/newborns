/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.AdmissionFile;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sylar
 */
@Local
public interface AdmissionFileFacadeLocal {

    void create(AdmissionFile admissionFile);

    void edit(AdmissionFile admissionFile);

    void remove(AdmissionFile admissionFile);

    AdmissionFile find(Object id);

    List<AdmissionFile> findAll();

    List<AdmissionFile> findRange(int[] range);

    int count();
    
}
