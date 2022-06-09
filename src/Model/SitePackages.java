/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Shah
 */
public class SitePackages {

    public int id;
    public int packageSubscriptionId;
    public String name;
    public Boolean isActive;
    public Date activateDate;
    public Date deactivateDate;

    public SitePackages() {
    }

    public SitePackages(int id, int packageSubscriptionId, String name, Boolean isActive, Date activateDate, Date deactivateDate) {
        this.id = id;
        this.packageSubscriptionId = packageSubscriptionId;
        this.name = name;
        this.isActive = isActive;
        this.activateDate = activateDate;
        this.deactivateDate = deactivateDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPackageSubscriptionId() {
        return packageSubscriptionId;
    }

    public void setPackageSubscriptionId(int packageSubscriptionId) {
        this.packageSubscriptionId = packageSubscriptionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Date getActivateDate() {
        return activateDate;
    }

    public void setActivateDate(Date activateDate) {
        this.activateDate = activateDate;
    }

    public Date getDeactivateDate() {
        return deactivateDate;
    }

    public void setDeactivateDate(Date deactivateDate) {
        this.deactivateDate = deactivateDate;
    }
    
    
    

}
