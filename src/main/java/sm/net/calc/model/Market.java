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
public class Market implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "markettype_primary")
    private MarketType primary;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "markettype_secondary")
    private MarketType secondary;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "component_id")
    private Machine component;
    private Long countComponnt;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "appserver_id")
    private Machine appServer;
    private Long countAppServer;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "webserver_id")
    private Machine webServer;
    private Long countWebServer;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dbserver_id")
    private Machine dbServer;
    private Long countDbServer;

    public Market(String name) {
        this.name = name;
    }

    public Market() {
    }

    public Market(Long id, String name, MarketType primary, MarketType secondary, Machine component, Long countComponnt, Machine appServer, Long countAppServer, Machine webServer, Long countWebServer, Machine dbServer, Long countDbServer) {
        this.id = id;
        this.name = name;
        this.primary = primary;
        this.secondary = secondary;
        this.component = component;
        this.countComponnt = countComponnt;
        this.appServer = appServer;
        this.countAppServer = countAppServer;
        this.webServer = webServer;
        this.countWebServer = countWebServer;
        this.dbServer = dbServer;
        this.countDbServer = countDbServer;
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

    public MarketType getPrimary() {
        return primary;
    }

    public Long getPrimaryMarketTypeId() {
        return primary != null ? primary.getId() : 0;
    }

    public void setPrimary(MarketType primary) {
        this.primary = primary;
    }

    public MarketType getSecondary() {
        return secondary;
    }

    public Long getSecondaryMarketTypeId() {
        return secondary != null ? secondary.getId() : 0;
    }

    public void setSecondary(MarketType secondary) {
        this.secondary = secondary;
    }

    public Machine getComponent() {
        return component;
    }

    public Long getComponentMachineId() {
        return component != null ? component.getId() : 0;
    }

    public void setComponent(Machine component) {
        this.component = component;
    }

    public Long getCountComponnt() {
        return countComponnt;
    }

    public void setCountComponnt(Long countComponnt) {
        this.countComponnt = countComponnt;
    }

    public Machine getAppServer() {
        return appServer;
    }

    public Long getAppServerMachineId() {
        return appServer != null ? appServer.getId() : 0;
    }

    public void setAppServer(Machine appServer) {
        this.appServer = appServer;
    }

    public Long getCountAppServer() {
        return countAppServer;
    }

    public void setCountAppServer(Long countAppServer) {
        this.countAppServer = countAppServer;
    }

    public Machine getWebServer() {
        return webServer;
    }

    public Long getWebServerMachineId() {
        return webServer != null ? webServer.getId() : 0;
    }

    public void setWebServer(Machine webServer) {
        this.webServer = webServer;
    }

    public Long getCountWebServer() {
        return countWebServer;
    }

    public void setCountWebServer(Long countWebServer) {
        this.countWebServer = countWebServer;
    }

    public Machine getDbServer() {
        return dbServer;
    }

    public Long getDbServerMachineId() {
        return dbServer != null ? dbServer.getId() : 0;
    }

    public void setDbServer(Machine dbServer) {
        this.dbServer = dbServer;
    }

    public Long getCountDbServer() {
        return countDbServer;
    }

    public void setCountDbServer(Long countDbServer) {
        this.countDbServer = countDbServer;
    }

}
