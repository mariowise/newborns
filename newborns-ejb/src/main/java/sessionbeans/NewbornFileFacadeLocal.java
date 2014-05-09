/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.NewbornFile;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface NewbornFileFacadeLocal {

    void create(NewbornFile newbornFile);

    void edit(NewbornFile newbornFile);

    void remove(NewbornFile newbornFile);

    NewbornFile find(Object id);

    List<NewbornFile> findAll();

    List<NewbornFile> findRange(int[] range);

    int count();
    
}
