/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.core.Addiction;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sylar
 */
@Local
public interface AddictionFacadeLocal {

    void create(Addiction addiction);

    void edit(Addiction addiction);

    void remove(Addiction addiction);

    Addiction find(Object id);

    List<Addiction> findAll();

    List<Addiction> findRange(int[] range);

    int count();
    
}
