/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.HealthService;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sylar
 */
@Local
public interface HealthServiceFacadeLocal {

    void create(HealthService healthService);

    void edit(HealthService healthService);

    void remove(HealthService healthService);

    HealthService find(Object id);

    List<HealthService> findAll();

    List<HealthService> findRange(int[] range);

    int count();
    
}
