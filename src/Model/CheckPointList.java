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
public class CheckPointList {
    public int checkPointId;
    public int sitePackageId;
    public String checkPointName;
    public Boolean isActive;
    public Date activationDate;
    public Date deactivationDate;

    public CheckPointList() {
    }

    public CheckPointList(int checkPointId, int sitePackageId, String checkPointName, Boolean isActive, Date activationDate, Date deactivationDate) {
        this.checkPointId = checkPointId;
        this.sitePackageId = sitePackageId;
        this.checkPointName = checkPointName;
        this.isActive = isActive;
        this.activationDate = activationDate;
        this.deactivationDate = deactivationDate;
    }

    public int getCheckPointId() {
        return checkPointId;
    }

    public void setCheckPointId(int checkPointId) {
        this.checkPointId = checkPointId;
    }

    public int getSitePackageId() {
        return sitePackageId;
    }

    public void setSitePackageId(int sitePackageId) {
        this.sitePackageId = sitePackageId;
    }

    public String getCheckPointName() {
        return checkPointName;
    }

    public void setCheckPointName(String checkPointName) {
        this.checkPointName = checkPointName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public Date getDeactivationDate() {
        return deactivationDate;
    }

    public void setDeactivationDate(Date deactivationDate) {
        this.deactivationDate = deactivationDate;
    }
    
    
}
