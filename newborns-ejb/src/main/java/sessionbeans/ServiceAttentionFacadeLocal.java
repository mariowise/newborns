/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.ServiceAttention;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface ServiceAttentionFacadeLocal {

    void create(ServiceAttention serviceAttention);

    void edit(ServiceAttention serviceAttention);

    void remove(ServiceAttention serviceAttention);

    ServiceAttention find(Object id);

    List<ServiceAttention> findAll();

    List<ServiceAttention> findRange(int[] range);

    int count();
    
    public Boolean registerAdmission(ServiceAttention sa);
}
