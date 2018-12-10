/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.net.calc.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;

/**
 *
 * @author shahzadmasud
 */
@Entity
@ConfigurationProperties
public class Machine extends Base implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = 0L;

    private String name;
    private Boolean includeSQLEnt;
    @Range(min = 1, message = "Number of CPUs should be greater than zero")
    private Integer noOfVCPUs;
    @Range(min = 1, message = "RAM should be greater than zero")
    private Integer ram;
    @Range(min = 1, message = "Temp Storage should be greater than zero")
    private Integer tempStorage;
    @Range(min = 1, message = "Disk size should be greater than zero")
    private Integer hardDiskSSD;

    public Machine() {
    }

    public Machine(String name) {
        this.name = name;
    }

    public Machine(String message, HttpStatus status) {
        this.status = status;
        this.message = message;
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

    public Boolean getIncludeSQLEnt() {
        return includeSQLEnt;
    }

    public void setIncludeSQLEnt(Boolean includeSQLEnt) {
        this.includeSQLEnt = includeSQLEnt;
    }

    public Integer getNoOfVCPUs() {
        return noOfVCPUs;
    }

    public void setNoOfVCPUs(Integer noOfVCPUs) {
        this.noOfVCPUs = noOfVCPUs;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Integer getTempStorage() {
        return tempStorage;
    }

    public void setTempStorage(Integer tempStorage) {
        this.tempStorage = tempStorage;
    }

    public Integer getHardDiskSSD() {
        return hardDiskSSD;
    }

    public void setHardDiskSSD(Integer hardDiskSSD) {
        this.hardDiskSSD = hardDiskSSD;
    }

}
