/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Scheduling;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface SchedulingFacadeLocal {

    void create(Scheduling scheduling);

    void edit(Scheduling scheduling);

    void remove(Scheduling scheduling);

    Scheduling find(Object id);

    List<Scheduling> findAll();

    List<Scheduling> findRange(int[] range);

    int count();
    
}
