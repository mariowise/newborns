/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.ExamType;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pingeso
 */
@Local
public interface ExamTypeFacadeLocal {

    void create(ExamType examType);

    void edit(ExamType examType);

    void remove(ExamType examType);

    ExamType find(Object id);

    List<ExamType> findAll();

    List<ExamType> findRange(int[] range);

    int count();
    
}
