/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.DeliveryType;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface DeliveryTypeFacadeLocal {

    void create(DeliveryType deliveryType);

    void edit(DeliveryType deliveryType);

    void remove(DeliveryType deliveryType);

    DeliveryType find(Object id);

    List<DeliveryType> findAll();

    List<DeliveryType> findRange(int[] range);

    int count();
    
}
