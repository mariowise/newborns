/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.YearsCount;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface YearsCountFacadeLocal {

    void create(YearsCount yearsCount);

    void edit(YearsCount yearsCount);

    void remove(YearsCount yearsCount);

    YearsCount find(Object id);

    List<YearsCount> findAll();

    List<YearsCount> findRange(int[] range);

    int count();
    
}
