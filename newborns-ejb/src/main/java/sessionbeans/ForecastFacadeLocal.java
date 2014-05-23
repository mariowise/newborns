/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Forecast;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface ForecastFacadeLocal {

    void create(Forecast forecast);

    void edit(Forecast forecast);

    void remove(Forecast forecast);

    Forecast find(Object id);

    List<Forecast> findAll();

    List<Forecast> findRange(int[] range);

    int count();
    
}
