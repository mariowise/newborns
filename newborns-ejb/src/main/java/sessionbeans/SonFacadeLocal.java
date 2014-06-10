/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.core.Son;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface SonFacadeLocal {

    void create(Son son);

    void edit(Son son);

    void remove(Son son);

    Son find(Object id);

    List<Son> findAll();

    List<Son> findRange(int[] range);

    int count();
    
}
