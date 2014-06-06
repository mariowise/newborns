/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.BloodGroup;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface BloodGroupFacadeLocal {

    void create(BloodGroup bloodGroup);

    void edit(BloodGroup bloodGroup);

    void remove(BloodGroup bloodGroup);

    BloodGroup find(Object id);

    List<BloodGroup> findAll();

    List<BloodGroup> findRange(int[] range);

    int count();
    
}
