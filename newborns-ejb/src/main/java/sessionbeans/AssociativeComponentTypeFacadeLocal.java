/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.AssociativeComponentType;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface AssociativeComponentTypeFacadeLocal {

    void create(AssociativeComponentType associativeComponentType);

    void edit(AssociativeComponentType associativeComponentType);

    void remove(AssociativeComponentType associativeComponentType);

    AssociativeComponentType find(Object id);

    List<AssociativeComponentType> findAll();

    List<AssociativeComponentType> findRange(int[] range);

    int count();
    
}
