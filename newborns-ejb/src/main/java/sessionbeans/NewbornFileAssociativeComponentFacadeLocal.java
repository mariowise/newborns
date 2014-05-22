/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.NewbornFileAssociativeComponent;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sylar
 */
@Local
public interface NewbornFileAssociativeComponentFacadeLocal {

    void create(NewbornFileAssociativeComponent newbornFileAssociativeComponent);

    void edit(NewbornFileAssociativeComponent newbornFileAssociativeComponent);

    void remove(NewbornFileAssociativeComponent newbornFileAssociativeComponent);

    NewbornFileAssociativeComponent find(Object id);

    List<NewbornFileAssociativeComponent> findAll();

    List<NewbornFileAssociativeComponent> findRange(int[] range);

    int count();
    
}
