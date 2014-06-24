/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeans.util;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import managedbeans.AddictionController;
import managedbeans.DeliveryController;
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

    /** Newborn Charts **/
    private BarChartModel recordNewbornBarModel;    
    private PieChartModel prematureNewbornPieModel;
    private PieChartModel mortalityNewbornPieModel;
    /** Mother Charts **/
    private BarChartModel recordMotherBarModel;
    private PieChartModel addictedMotherPieModel;
    private BarChartModel addictionMotherBarModel;
    /** Delivery Charts **/
    private BarChartModel recordDeliveryBarModel;
    
    @Inject
    private SonController sonController;
    
    @Inject
    private MotherController motherController;
    
    @Inject
    private DeliveryController deliveryController;
    
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
        createMortalityNewbornPieModel();
        
        createAddictedMotherPieModel();
    }
 
    private void createBarModels() {
        createRecordNewbornBarModel();
        
        createRecordMotherBarModel();
        createAddictionMotherBarModel();
        
        createRecordDeliveryBarModel();
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
        
    public BarChartModel getRecordDeliveryBarModel() {
        return recordDeliveryBarModel;
    }
    
    public PieChartModel getMortalityNewbornPieModel() {
        return mortalityNewbornPieModel;
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
    
    private void createMortalityNewbornPieModel() {
        int amountTotalItems = sonController.getAllItems().size();
        int amountBornAliveSons = sonController.countBornAliveItems();
        
        mortalityNewbornPieModel = new PieChartModel();
         
        mortalityNewbornPieModel.set("Nacidos Vivos", amountBornAliveSons);
        mortalityNewbornPieModel.set("Nacidos Muertos", (amountTotalItems - amountBornAliveSons));
         
        mortalityNewbornPieModel.setTitle("Mortalidad al nacer");
        mortalityNewbornPieModel.setLegendPosition("e");
        mortalityNewbornPieModel.setFill(false);
        mortalityNewbornPieModel.setShowDataLabels(true);
        mortalityNewbornPieModel.setDiameter(200);
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
        
        Map alcoholAddictions = addictionController.getRegisteredItemsByType(value1);
        Map drugAddictions = addictionController.getRegisteredItemsByType(value2);
        Map cigarAddictions = addictionController.getRegisteredItemsByType(value3);
        
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
    
    private BarChartModel initRecordDeliveryBarModel() {
        
        String value1 = "Cesarea";
        String value2 = "Forceps";
        String value3 = "Podálico";
        String value4 = "Fuera del Servicio";
        
        Map valueOneDelivery = deliveryController.getRegisteredItemsByType(value1);
        Map valueTwoDelivery = deliveryController.getRegisteredItemsByType(value2);
        Map valueThreeDelivery = deliveryController.getRegisteredItemsByType(value3);
        Map valueFourDelivery = deliveryController.getRegisteredItemsByType(value4);
        
        BarChartModel model = new BarChartModel();
 
        ChartSeries valueOneChart = new ChartSeries();
        valueOneChart.setLabel(value1);        
        valueOneChart.setData(valueOneDelivery);
 
        ChartSeries valueTwoChart = new ChartSeries();
        valueTwoChart.setLabel(value2);
        valueTwoChart.setData(valueTwoDelivery);
        
        ChartSeries valueThreeChart = new ChartSeries();
        valueThreeChart.setLabel(value3);
        valueThreeChart.setData(valueThreeDelivery);
        
        ChartSeries valueFourChart = new ChartSeries();
        valueFourChart.setLabel(value4);
        valueFourChart.setData(valueFourDelivery);
        
        model.addSeries(valueOneChart);
        model.addSeries(valueTwoChart);
        model.addSeries(valueThreeChart);
        model.addSeries(valueFourChart);
         
        return model;
    }
     
    private void createRecordDeliveryBarModel() {
        recordDeliveryBarModel = initRecordDeliveryBarModel();
         
        recordDeliveryBarModel.setTitle("Registro de partos");
        recordDeliveryBarModel.setLegendPosition("ne");
         
        Axis xAxis = recordDeliveryBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Años de registro");
         
        Axis yAxis = recordDeliveryBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("N° Partos");
        yAxis.setMin(0);
        yAxis.setMax(deliveryController.getAllItems().size());
    }
}
