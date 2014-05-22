/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.ForecastHealth;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sylar
 */
@Local
public interface ForecastHealthFacadeLocal {

    void create(ForecastHealth forecastHealth);

    void edit(ForecastHealth forecastHealth);

    void remove(ForecastHealth forecastHealth);

    ForecastHealth find(Object id);

    List<ForecastHealth> findAll();

    List<ForecastHealth> findRange(int[] range);

    int count();
    
}
