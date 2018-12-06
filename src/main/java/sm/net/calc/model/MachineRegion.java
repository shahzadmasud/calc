/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.net.calc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author shahzadmasud
 */
@Entity
public class MachineRegion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = 0L;

    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;

    private Double price;

    public MachineRegion() {
    }

    public MachineRegion(String name) {
        this.name = name;
    }

    public MachineRegion(String name, Region region, Machine machine, Double price) {
        this.name = name;
        this.region = region;
        this.machine = machine;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public Long getRegionId() {
        return region != null ? region.getId() : 0;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Machine getMachine() {
        return machine;
    }

    public Long getMachineId() {
        return machine != null ? machine.getId() : 0;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
