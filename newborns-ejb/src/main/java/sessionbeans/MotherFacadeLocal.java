/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.core.Mother;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface MotherFacadeLocal {

    void create(Mother mother);
    
    void createWithProfile(Mother mother);

    void edit(Mother mother);

    void remove(Mother mother);

    Mother find(Object id);

    List<Mother> findAll();

    List<Mother> findRange(int[] range);

    int count();
    
}
