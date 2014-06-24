/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeans.util;

import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import managedbeans.AddictionController;
import managedbeans.MotherController;
import managedbeans.ServiceAttentionController;
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

    private BarChartModel recordNewbornBarModel;    
    private PieChartModel prematureNewbornPieModel;
    
    private BarChartModel recordMotherBarModel;
    private PieChartModel addictedMotherPieModel;
    private BarChartModel addictionMotherBarModel;
    
    @Inject
    private SonController sonController;
    
    @Inject
    private MotherController motherController;
    
    @Inject
    private AddictionController addictionController;
    
    @Inject
    private ServiceAttentionController serviceAttentionController;
 
    public StatisticController() {
    }
    
    @PostConstruct
    public void init() {
        createPieModels();
        createBarModels();
    }
     
    private void createPieModels() {
        createPrematureNewbornPieModel();
        
        createAddictedMotherPieModel();
    }
 
    private void createBarModels() {
        createRecordNewbornBarModel();
        
        createRecordMotherBarModel();
        createAddictionMotherBarModel();
    }
 
    public PieChartModel getPrematureNewbornPieModel() {
        return prematureNewbornPieModel;
    }
 
    public PieChartModel getAddictedMotherPieModel() {
        return addictedMotherPieModel;
    }
     
    public BarChartModel getAddictionMotherBarModel() {
        return addictionMotherBarModel;
    }
    
    public BarChartModel getRecordNewbornBarModel() {
        return recordNewbornBarModel;
    }
    
    public BarChartModel getRecordMotherBarModel() {
        return recordMotherBarModel;
    }
    
    private void createPrematureNewbornPieModel() {
        int amountSons = sonController.getAllItems().size();
        int amountPrematureSons = sonController.countPrematureItems();
        
        prematureNewbornPieModel = new PieChartModel();
         
        prematureNewbornPieModel.set("Prematuros Extremos", amountPrematureSons);
        prematureNewbornPieModel.set("No Prematuros Extremos", (amountSons - amountPrematureSons));
         
        prematureNewbornPieModel.setTitle("Prematuridad extrema en neonatos");
        prematureNewbornPieModel.setLegendPosition("e");
        prematureNewbornPieModel.setFill(false);
        prematureNewbornPieModel.setShowDataLabels(true);
        prematureNewbornPieModel.setDiameter(200);
    }
    
    private void createAddictedMotherPieModel() {
        int amountMothers = motherController.getAllItems().size();
        int amountAddictedMothers = addictionController.countAddictedMothers();
        
        addictedMotherPieModel = new PieChartModel();
         
        addictedMotherPieModel.set("Madres Adictas", amountAddictedMothers);
        addictedMotherPieModel.set("Madres No Adictas", (amountMothers - amountAddictedMothers));
         
        addictedMotherPieModel.setTitle("Madres adictas");
        addictedMotherPieModel.setLegendPosition("e");
        addictedMotherPieModel.setFill(false);
        addictedMotherPieModel.setShowDataLabels(true);
        addictedMotherPieModel.setDiameter(200);
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
         
        addictionMotherBarModel.setTitle("Adicción en madres");
        addictionMotherBarModel.setLegendPosition("ne");
         
        Axis xAxis = addictionMotherBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Tipo de adicciones por año");
         
        Axis yAxis = addictionMotherBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("N° Madres");
        yAxis.setMin(0);
        yAxis.setMax(addictionController.getAllItems().size());
    }
    
    private BarChartModel initRecordNewbornBarModel() {
        
        Map registeredSons = sonController.getRegisteredItems();
        
        BarChartModel model = new BarChartModel();
 
        ChartSeries register = new ChartSeries();
        register.setLabel("Neonatos Registrados");        
        register.setData(registeredSons);
         
        model.addSeries(register);
         
        return model;
    }
     
    private void createRecordNewbornBarModel() {
        recordNewbornBarModel = initRecordNewbornBarModel();
         
        recordNewbornBarModel.setTitle("Registro de neonatos");
        recordNewbornBarModel.setLegendPosition("ne");
         
        Axis xAxis = recordNewbornBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Años de registro");
         
        Axis yAxis = recordNewbornBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("N° Neonatos");
        yAxis.setMin(0);
        yAxis.setMax(sonController.getAllItems().size());
    }
    
    private BarChartModel initRecordMotherBarModel() {
        
        Map registeredMothers = serviceAttentionController.getRegisteredItems("Mother");
        
        BarChartModel model = new BarChartModel();
 
        ChartSeries register = new ChartSeries();
        register.setLabel("Madres Registradas");        
        register.setData(registeredMothers);
         
        model.addSeries(register);
         
        return model;
    }
     
    private void createRecordMotherBarModel() {
        recordMotherBarModel = initRecordMotherBarModel();
         
        recordMotherBarModel.setTitle("Registro de madres");
        recordMotherBarModel.setLegendPosition("ne");
         
        Axis xAxis = recordMotherBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Años de registro");
         
        Axis yAxis = recordMotherBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("N° Madres");
        yAxis.setMin(0);
        yAxis.setMax(motherController.getAllItems().size());
    }
}
