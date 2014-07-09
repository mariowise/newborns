/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities.core;

import entities.ExamPhase;
import entities.Gender;
import entities.YearsCount;
import entities.tau.Exam;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author pingeso
 */
@Entity
public class Son implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    @ManyToOne
    private YearsCount year;
    
    private int ticket;
    
    @ManyToOne
    private Gender gender;
    
    @Size(min = 1, message = "Debe ingresar un Peso")
    @Pattern(regexp = "^(\\d*\\.?\\d*)$", message = "Debe ingresar un Peso válido")
    private String weight;
    
    @Size(min = 1, message = "Debe ingresar una Tamaño")
    @Pattern(regexp = "^(\\d*\\.?\\d*)$", message = "Debe ingresar un Tamaño válido")
    private String size;
    
    @Size(min = 1, message = "Debe ingresar un Perímetro de cabeza")
    @Pattern(regexp = "^(\\d*\\.?\\d*)$", message = "Debe ingresar un Perímetro de cabeza válido")
    private String skullPerimeter;
    
    private String apgar1;
    
    private String apgar5;
    
    private String apgar10;
    
    private Boolean vitk;
    
    private Boolean bcg;
    
    @Size(min = 1, message = "Debe ingresar un Peso de placenta")
    @Pattern(regexp = "^(\\d*\\.?\\d*)$", message = "Debe ingresar un Peso de placenta válido")
    private String weightPlacenta;
    
    private Boolean cry;
    
    private String attachedTime;
    
    @Size(min = 1, message = "Debe ingresar una Temperatura axilar")
    @Pattern(regexp = "^(\\d*\\.?\\d*)$", message = "Debe ingresar una Temperatura axilar válida")
    private String tempAxilar;
    
    @Size(min = 1, message = "Debe ingresar una Temperatura rectal")
    @Pattern(regexp = "^(\\d*\\.?\\d*)$", message = "Debe ingresar una Temperatura rectal válida")
    private String tempRectal;
    
    private Boolean of;
    
    private Boolean og;
    
    private Boolean ot;
    
    private String secretion;
    
    @Size(min = 1, message = "Debe ingresar un Color de secreción")
    private String secretionColour;
    
    @Size(min = 1, message = "Debe ingresar una Consistencia")
    private String consistency;
    
    private Boolean gas;
    
    private Boolean asphyxiaDelivery;
    
    private Boolean extremePremature;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    private Profile profile;
    
    @ManyToOne
    private Mother mother;
    
    @ManyToOne
    private Delivery delivery;
    
    private Boolean bornAlive=true;
    
    private Boolean mechanicalVentilation;
    
    private int pediatricExamination;
    
    private String examState;
    
    // Risk factors
    private Boolean torch;
    
    private Boolean torchSuspect;
    
    private Boolean cmv;
    
    private Boolean toxo;
    
    private Boolean herpes;
    
    private Boolean rubeola;
    
    private Boolean sifilis;
    
    private Boolean meningitis;
    
    private Boolean sepsisConnatal;
    
    private Boolean hiperbilirrubinemia;
    
    private double hiperbilirrubinemiaMax;
    
    private Boolean ototoxicos;
    
    private Boolean furosemida;
    
    private Boolean aminoglicosidos;
    
    private String otherOtos;
    
    private int ventMecanica;
    
    private Boolean antFamiliar;
    
    private String parienteSordo;
    
    private Boolean icn;
    
    @JoinColumn(nullable = true)
    @ManyToOne
    private ExamPhase examPhase;
    
    @OneToMany(mappedBy = "son")
    private List<Exam> exams;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public YearsCount getYear() {
        return year;
    }

    public void setYear(YearsCount year) {
        this.year = year;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSkullPerimeter() {
        return skullPerimeter;
    }

    public void setSkullPerimeter(String skullPerimeter) {
        this.skullPerimeter = skullPerimeter;
    }

    public String getApgar1() {
        return apgar1;
    }

    public void setApgar1(String apgar1) {
        this.apgar1 = apgar1;
    }

    public String getApgar5() {
        return apgar5;
    }

    public void setApgar5(String apgar5) {
        this.apgar5 = apgar5;
    }

    public String getApgar10() {
        return apgar10;
    }

    public void setApgar10(String apgar10) {
        this.apgar10 = apgar10;
    }

    public Boolean getVitk() {
        return vitk;
    }

    public void setVitk(Boolean vitk) {
        this.vitk = vitk;
    }

    public Boolean getBcg() {
        return bcg;
    }

    public void setBcg(Boolean bcg) {
        this.bcg = bcg;
    }

    public String getWeightPlacenta() {
        return weightPlacenta;
    }

    public void setWeightPlacenta(String weightPlacenta) {
        this.weightPlacenta = weightPlacenta;
    }

    public Boolean getCry() {
        return cry;
    }

    public void setCry(Boolean cry) {
        this.cry = cry;
    }

    public String getAttachedTime() {
        return attachedTime;
    }

    public void setAttachedTime(String attachedTime) {
        this.attachedTime = attachedTime;
    }

    public String getTempAxilar() {
        return tempAxilar;
    }

    public void setTempAxilar(String tempAxilar) {
        this.tempAxilar = tempAxilar;
    }

    public String getTempRectal() {
        return tempRectal;
    }

    public void setTempRectal(String tempRectal) {
        this.tempRectal = tempRectal;
    }

    public Boolean getOf() {
        return of;
    }

    public void setOf(Boolean of) {
        this.of = of;
    }

    public Boolean getOg() {
        return og;
    }

    public void setOg(Boolean og) {
        this.og = og;
    }

    public Boolean getOt() {
        return ot;
    }

    public void setOt(Boolean ot) {
        this.ot = ot;
    }

    public String getSecretion() {
        return secretion;
    }

    public void setSecretion(String secretion) {
        this.secretion = secretion;
    }

    public String getSecretionColour() {
        return secretionColour;
    }

    public void setSecretionColour(String secretionColour) {
        this.secretionColour = secretionColour;
    }

    public String getConsistency() {
        return consistency;
    }

    public void setConsistency(String consistency) {
        this.consistency = consistency;
    }

    public Boolean getGas() {
        return gas;
    }

    public void setGas(Boolean gas) {
        this.gas = gas;
    }

    public Boolean getAsphyxiaDelivery() {
        return asphyxiaDelivery;
    }

    public void setAsphyxiaDelivery(Boolean asphyxiaDelivery) {
        this.asphyxiaDelivery = asphyxiaDelivery;
    }

    public Boolean getExtremePremature() {
        return extremePremature;
    }

    public void setExtremePremature(Boolean extremePremature) {
        this.extremePremature = extremePremature;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Mother getMother() {
        return mother;
    }

    public void setMother(Mother mother) {
        this.mother = mother;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
    
    public Boolean getBornAlive() {
        return bornAlive;
    }

    public void setBornAlive(Boolean bornAlive) {
        this.bornAlive = bornAlive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMechanicalVentilation() {
        return mechanicalVentilation;
    }

    public void setMechanicalVentilation(Boolean mechanicalVentilation) {
        this.mechanicalVentilation = mechanicalVentilation;
    }

    public int getPediatricExamination() {
        return pediatricExamination;
    }

    public void setPediatricExamination(int pediatricExamination) {
        this.pediatricExamination = pediatricExamination;
    }

    public String getExamState() {
        return examState;
    }

    public void setExamState(String examState) {
        this.examState = examState;
    }

    public ExamPhase getExamPhase() {
        return examPhase;
    }
    
    

    public Boolean getTorchSuspect() {
        return torchSuspect;
    }

    public void setTorchSuspect(Boolean torchSuspect) {
        this.torchSuspect = torchSuspect;
    }

    public Boolean getCmv() {
        return cmv;
    }

    public void setCmv(Boolean cmv) {
        this.cmv = cmv;
    }

    public Boolean getToxo() {
        return toxo;
    }

    public void setToxo(Boolean toxo) {
        this.toxo = toxo;
    }

    public Boolean getHerpes() {
        return herpes;
    }

    public void setHerpes(Boolean herpes) {
        this.herpes = herpes;
    }

    public Boolean getRubeola() {
        return rubeola;
    }

    public void setRubeola(Boolean rubeola) {
        this.rubeola = rubeola;
    }

    public Boolean getSifilis() {
        return sifilis;
    }

    public void setSifilis(Boolean sifilis) {
        this.sifilis = sifilis;
    }

    public Boolean getMeningitis() {
        return meningitis;
    }

    public void setMeningitis(Boolean meningitis) {
        this.meningitis = meningitis;
    }

    public Boolean getSepsisConnatal() {
        return sepsisConnatal;
    }

    public void setSepsisConnatal(Boolean sepsisConnatal) {
        this.sepsisConnatal = sepsisConnatal;
    }

    public Boolean getFurosemida() {
        return furosemida;
    }

    public void setFurosemida(Boolean furosemida) {
        this.furosemida = furosemida;
    }

    public Boolean getAminoglicosidos() {
        return aminoglicosidos;
    }

    public void setAminoglicosidos(Boolean aminoglicosidos) {
        this.aminoglicosidos = aminoglicosidos;
    }

    public int getVentMecanica() {
        return ventMecanica;
    }

    public void setVentMecanica(int ventMecanica) {
        this.ventMecanica = ventMecanica;
    }

    public Boolean getAntFamiliar() {
        return antFamiliar;
    }

    public void setAntFamiliar(Boolean antFamiliar) {
        this.antFamiliar = antFamiliar;
    }

    public String getParienteSordo() {
        return parienteSordo;
    }

    public void setParienteSordo(String parienteSordo) {
        this.parienteSordo = parienteSordo;
    }

    public Boolean getTorch() {
        return torch;
    }

    public void setTorch(Boolean torch) {
        this.torch = torch;
    }

    public Boolean getHiperbilirrubinemia() {
        return hiperbilirrubinemia;
    }

    public void setHiperbilirrubinemia(Boolean hiperbilirrubinemia) {
        this.hiperbilirrubinemia = hiperbilirrubinemia;
    }

    public double getHiperbilirrubinemiaMax() {
        return hiperbilirrubinemiaMax;
    }

    public void setHiperbilirrubinemiaMax(double hiperbilirrubinemiaMax) {
        this.hiperbilirrubinemiaMax = hiperbilirrubinemiaMax;
    }

    public Boolean getOtotoxicos() {
        return ototoxicos;
    }

    public void setOtotoxicos(Boolean ototoxicos) {
        this.ototoxicos = ototoxicos;
    }

    public String getOtherOtos() {
        return otherOtos;
    }

    public void setOtherOtos(String otherOtos) {
        this.otherOtos = otherOtos;
    }

    public Boolean getIcn() {
        return icn;
    }

    public void setIcn(Boolean icn) {
        this.icn = icn;
    }
    
    public void setExamPhase(ExamPhase examPhase) {
        this.examPhase = examPhase;
    }

    public List<Exam> getExams() {
        if(exams == null) 
            exams = new ArrayList<Exam>();
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Son)) {
            return false;
        }
        Son other = (Son) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.core.Son[ id=" + id + " ]";
    }
    
}
