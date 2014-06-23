/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeans.util;

import entities.core.Addiction;
import entities.core.Son;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import managedbeans.AddictionController;
import managedbeans.MotherController;
import managedbeans.SonController;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author sylar
 */
@Named(value = "statisticController")
@RequestScoped
public class StatisticController {

    /**
     * Creates a new instance of StatisticController
     */
    public StatisticController() {
    }
    
    private PieChartModel prematureNewbornPieModel;
    private BarChartModel addictionMotherBarModel;
    
    @Inject
    private SonController sonController;
    
    @Inject
    private AddictionController addictionController;
 
    @PostConstruct
    public void init() {
        createPieModels();
        createBarModels();
    }
     
    private void createPieModels() {
        createPrematureNewbornPieModel();
    }
 
    private void createBarModels() {
        createAddictionMotherBarModel();
    }
 
    public PieChartModel getPrematureNewbornPieModel() {
        return prematureNewbornPieModel;
    }
     
    public BarChartModel getAddictionMotherBarModel() {
        return addictionMotherBarModel;
    }
    
    private void createPrematureNewbornPieModel() {
        int allSons = sonController.getAllItems().size();
        int prematureSons = sonController.countPrematureItems();
        
        prematureNewbornPieModel = new PieChartModel();
         
        prematureNewbornPieModel.set("Prematuros Extremos", prematureSons);
        prematureNewbornPieModel.set("No Prematuros Extremos", (allSons - prematureSons));
         
        prematureNewbornPieModel.setTitle("Prematuros Extremos");
        prematureNewbornPieModel.setLegendPosition("e");
        prematureNewbornPieModel.setFill(false);
        prematureNewbornPieModel.setShowDataLabels(true);
        prematureNewbornPieModel.setDiameter(200);
    }
     
    private BarChartModel initAddictionMotherBarModel() {
        String value1 = "Alcohol";
        String value2 = "Droga";
        String value3 = "Cigarro";
        
        Map alcoholAddictions = addictionController.getAddictionsByType(value1);
        Map drugAddictions = addictionController.getAddictionsByType(value2);
        Map cigarAddictions = addictionController.getAddictionsByType(value3);
        
        BarChartModel model = new BarChartModel();
 
        ChartSeries alcohol = new ChartSeries();
        alcohol.setLabel(value1);        
        alcohol.setData(alcoholAddictions);
 
        ChartSeries drug = new ChartSeries();
        drug.setLabel(value2);
        drug.setData(drugAddictions);
        
        ChartSeries cigar = new ChartSeries();
        cigar.setLabel(value3);
        cigar.setData(cigarAddictions);
        
        model.addSeries(alcohol);
        model.addSeries(drug);
        model.addSeries(cigar);
         
        return model;
    }
     
    private void createAddictionMotherBarModel() {
        addictionMotherBarModel = initAddictionMotherBarModel();
         
        addictionMotherBarModel.setTitle("Adicciones de Madres");
        addictionMotherBarModel.setLegendPosition("ne");
         
        Axis xAxis = addictionMotherBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Adicciones");
         
        Axis yAxis = addictionMotherBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Madres");
        yAxis.setMin(0);
        yAxis.setMax(addictionController.getAllItems().size());
    }
}
