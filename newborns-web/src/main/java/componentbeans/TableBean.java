/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package componentbeans;

/**
 *
 * @author pingeso
 */
import entities.NewbornFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.faces.model.SelectItem;
 

public class TableBean implements Serializable {
  
    private List<NewbornFile> filteredNewbornFiles;
 
    private List<NewbornFile> newbornFilesSmall;
 
    public TableBean() {
        newbornFilesSmall = new ArrayList<NewbornFile>();
// 
//        populateRandomCars(newbornFilesSmall, 9);
//        manufacturerOptions = createFilterOptions(manufacturers);
    }
 
//    private void populateRandomCars(List<Car> list, int size) {
//        for(int i = 0 ; i < size ; i++)
//            list.add(new Car(getRandomModel(), getRandomYear(), getRandomManufacturer(), getRandomColor(), getRandomPrice(), getRandomSoldState()));
//    }
 
    public List<NewbornFile> getFilteredNewbornFiles() {
        return filteredNewbornFiles;
    }
 
    public void setFilteredNewbornFiles(List<NewbornFile> filteredNewbornFiles) {
        this.filteredNewbornFiles = filteredNewbornFiles;
    }
 
    public List<NewbornFile> getNewbornFilesSmall() {
        return newbornFilesSmall;
    }
// 
//    private int getRandomYear() {
//        return (int) (Math.random() * 50 + 1960);
//    }
// 
//    private String getRandomColor() {
//        return colors[(int) (Math.random() * 10)];
//    }
// 
//    private String getRandomManufacturer() {
//        return manufacturers[(int) (Math.random() * 10)];
//    }
// 
//    private String getRandomModel() {
//        return UUID.randomUUID().toString().substring(0, 8);
//    }
// 
//    public int getRandomPrice() {
//        return (int) (Math.random() * 100000);
//    }
//     
//    public boolean getRandomSoldState() {
//        return (Math.random() > 0.5d) ? true: false;
//    }
// 
//    public SelectItem[] getManufacturerOptions() {
//        return manufacturerOptions;
//    }
// 
    public boolean filterByPrice(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
         
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }
}
