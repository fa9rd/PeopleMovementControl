package Model;

import java.sql.Date;

public class Package {

    public int packageId;
    public String packageName;
    public int sites;
    public int checkpoints;
    public Date validity;
    public float cost;
    public int duration;
    public Boolean packageIsActive;
    public String packageCreatedBy;

    public Package() {
    }

    public Package(int packageId, String packageName, int sites, int checkpoints, Date validity, float cost, int duration, Boolean packageIsActive, String packageCreatedBy) {
        this.packageId = packageId;
        this.packageName = packageName;
        this.sites = sites;
        this.checkpoints = checkpoints;
        this.validity = validity;
        this.cost = cost;
        this.duration = duration;
        this.packageIsActive = packageIsActive;
        this.packageCreatedBy = packageCreatedBy;
    }

    public Package(String packageName, int sites, int checkpoints, Date validity, float cost, int duration, Boolean packageIsActive, String packageCreatedBy) {
        this.packageName = packageName;
        this.sites = sites;
        this.checkpoints = checkpoints;
        this.validity = validity;
        this.cost = cost;
        this.duration = duration;
        this.packageIsActive = packageIsActive;
        this.packageCreatedBy = packageCreatedBy;
    }
    
    
    

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getSites() {
        return sites;
    }

    public void setSites(int sites) {
        this.sites = sites;
    }

    public int getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(int checkpoints) {
        this.checkpoints = checkpoints;
    }

    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Boolean getPackageIsActive() {
        return packageIsActive;
    }

    public void setPackageIsActive(Boolean packageIsActive) {
        this.packageIsActive = packageIsActive;
    }

    public String getPackageCreatedBy() {
        return packageCreatedBy;
    }

    public void setPackageCreatedBy(String packageCreatedBy) {
        this.packageCreatedBy = packageCreatedBy;
    }
    
     

    
}
