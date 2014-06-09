/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.core.Revival;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sylar
 */
@Local
public interface RevivalFacadeLocal {

    void create(Revival revival);

    void edit(Revival revival);

    void remove(Revival revival);

    Revival find(Object id);

    List<Revival> findAll();

    List<Revival> findRange(int[] range);

    int count();
    
}
