/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.core;

import entities.BloodGroup;
import entities.Commune;
import entities.Forecast;
import entities.Gender;
import entities.Region;
import entities.Service;
import entities.ServiceAttention;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author pingeso
 */
@Entity
public class Mother implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 1, message = "Debe ingresar un Run")
    @Pattern(regexp = "^[0-9kK]+", message = "El Run debe estar formado solo por números y si es necesario la letra K")
    private String run;

    @Size(min = 1, message = "Debe ingresar un nombre")
    @Pattern(regexp = "\\D*", message = "El campo Nombres no debe contener números")
    private String name;

    @Size(min = 1, message = "Debe ingresar un Apellido paterno")
    @Pattern(regexp = "\\D*", message = "El campo Primer apellido no debe contener números ni tildes")
    private String firstLastname;

    @Size(min = 1, message = "Debe ingresar un Apellido materno")
    @Pattern(regexp = "\\D*", message = "El campo Segundo apellido no puede contener números ni tildes")
    private String secondLastname;

    @NotNull(message = "Debe ingresar una fecha de nacimiento")
    @Temporal(javax.persistence.TemporalType.DATE)
    @Past(message = "Coloque una fecha de nacimiento anterior al día de hoy")
    private Date birthDate;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Gender gender;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Forecast forecastHealth;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Region region;

    private String city;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Commune commune;

    private Boolean ruralAddress = false;

    private String address;

    private String addressDetail;

    private String phone;

    private String mobile;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$", message = "Ingrese un correo valido, e.g.=mail@mail.com")
    private String email;
    
    private Boolean hasDeafRelatives = false;
    
    private String deafRelatives;

    @ManyToOne
    @JoinColumn(nullable = true)
    private BloodGroup bloodGroup;

    private Boolean coombs = false;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Service healthService;

    @NotNull
    @ManyToOne
    private Profile profile;

    @OneToMany(mappedBy = "mother")
    private List<ServiceAttention> attentions;

    @OneToMany(mappedBy = "mother")
    private List<Delivery> deliveries;
    
    @OneToMany(mappedBy = "mother")
    private List<Son> sons;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstLastname() {
        return firstLastname;
    }

    public void setFirstLastname(String firstLastname) {
        this.firstLastname = firstLastname;
    }

    public String getSecondLastname() {
        return secondLastname;
    }

    public void setSecondLastname(String secondLastname) {
        this.secondLastname = secondLastname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Forecast getForecastHealth() {
        return forecastHealth;
    }

    public void setForecastHealth(Forecast forecastHealth) {
        this.forecastHealth = forecastHealth;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    public Boolean getRuralAddress() {
        return ruralAddress;
    }

    public void setRuralAddress(Boolean ruralAddress) {
        this.ruralAddress = ruralAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Boolean getCoombs() {
        return coombs;
    }

    public void setCoombs(Boolean coombs) {
        this.coombs = coombs;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<ServiceAttention> getAttentions() {
        if (attentions == null) {
            attentions = new ArrayList<ServiceAttention>();
        }
        return attentions;
    }

    public void setAttentions(List<ServiceAttention> attentions) {
        this.attentions = attentions;
    }

    public Service getHealthService() {
        return healthService;
    }

    public void setHealthService(Service healthService) {
        this.healthService = healthService;
    }

    public List<Delivery> getDeliveries() {
        if(deliveries == null) {
            deliveries = new ArrayList<Delivery>();
        }
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public List<Son> getSons() {
        if (sons == null) {
            sons = new ArrayList<Son>();
        }
        return sons;
    }

    public Boolean getHasDeafRelatives() {
        return hasDeafRelatives;
    }

    public void setHasDeafRelatives(Boolean hasDeafRelatives) {
        this.hasDeafRelatives = hasDeafRelatives;
    }

    public String getDeafRelatives() {
        return deafRelatives;
    }

    public void setDeafRelatives(String DeafRelatives) {
        this.deafRelatives = DeafRelatives;
    }

    public void setSons(List<Son> sons) {
        this.sons = sons;
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
        if (!(object instanceof Mother)) {
            return false;
        }
        Mother other = (Mother) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.core.Mother[ id=" + id + " ]";
    }

}
