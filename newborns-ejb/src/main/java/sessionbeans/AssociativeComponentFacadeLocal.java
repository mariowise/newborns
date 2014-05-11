/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.AssociativeComponent;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface AssociativeComponentFacadeLocal {

    void create(AssociativeComponent associativeComponent);

    void edit(AssociativeComponent associativeComponent);

    void remove(AssociativeComponent associativeComponent);

    AssociativeComponent find(Object id);

    List<AssociativeComponent> findAll();

    List<AssociativeComponent> findRange(int[] range);

    int count();
    
}
