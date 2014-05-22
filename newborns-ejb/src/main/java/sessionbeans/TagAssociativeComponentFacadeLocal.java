/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.TagAssociativeComponent;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sylar
 */
@Local
public interface TagAssociativeComponentFacadeLocal {

    void create(TagAssociativeComponent tagAssociativeComponent);

    void edit(TagAssociativeComponent tagAssociativeComponent);

    void remove(TagAssociativeComponent tagAssociativeComponent);

    TagAssociativeComponent find(Object id);

    List<TagAssociativeComponent> findAll();

    List<TagAssociativeComponent> findRange(int[] range);

    int count();
    
}
