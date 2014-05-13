/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.SchedulingState;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface SchedulingStateFacadeLocal {

    void create(SchedulingState schedulingState);

    void edit(SchedulingState schedulingState);

    void remove(SchedulingState schedulingState);

    SchedulingState find(Object id);

    List<SchedulingState> findAll();

    List<SchedulingState> findRange(int[] range);

    int count();
    
}
