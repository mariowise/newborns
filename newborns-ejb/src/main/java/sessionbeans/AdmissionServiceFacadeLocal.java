/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.AdmissionService;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sylar
 */
@Local
public interface AdmissionServiceFacadeLocal {

    void create(AdmissionService admissionService);

    void edit(AdmissionService admissionService);

    void remove(AdmissionService admissionService);

    AdmissionService find(Object id);

    List<AdmissionService> findAll();

    List<AdmissionService> findRange(int[] range);

    int count();
    
}
