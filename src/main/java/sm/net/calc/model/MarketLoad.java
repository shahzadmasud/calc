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
public class MarketLoad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "markettype_id")
    private MarketType marketType;
    private Long contracts;
    private Long users;

    public MarketLoad(String name) {
        this.name = name;
    }

    public MarketLoad() {
    }

    public MarketLoad(Long id, String name, MarketType marketType, Long contracts, Long users) {
        this.id = id;
        this.name = name;
        this.marketType = marketType;
        this.contracts = contracts;
        this.users = users;
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

    public MarketType getMarketType() {
        return marketType;
    }

    public Long getMarketTypeId() {
        return marketType != null ? marketType.getId() : 0;
    }

    public void setMarketType(MarketType marketType) {
        this.marketType = marketType;
    }

    public Long getContracts() {
        return contracts;
    }

    public void setContracts(Long contracts) {
        this.contracts = contracts;
    }

    public Long getUsers() {
        return users;
    }

    public void setUsers(Long users) {
        this.users = users;
    }

}
