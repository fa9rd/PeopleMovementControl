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
public class PackageSubscription {

    public int id;
    public int packageId;
    public String userId;
    public Date subscriptionDate;
    public Boolean subscriptionIsActive;
    public String subscriptionDeactiveBy;
    public Date subsciptionDeactivateDate;
    public float subscriptionTotalCost;
    public Date subscriptionEstimateDeactivateDate;

    public PackageSubscription() {
    }

    public PackageSubscription(int id, int packageId, String userId, Date subscriptionDate, Boolean subscriptionIsActive, String subscriptionDeactiveBy, Date subsciptionDeactivateDate, float subscriptionTotalCost, Date subscriptionEstimateDeactivateDate) {
        this.id = id;
        this.packageId = packageId;
        this.userId = userId;
        this.subscriptionDate = subscriptionDate;
        this.subscriptionIsActive = subscriptionIsActive;
        this.subscriptionDeactiveBy = subscriptionDeactiveBy;
        this.subsciptionDeactivateDate = subsciptionDeactivateDate;
        this.subscriptionTotalCost = subscriptionTotalCost;
        this.subscriptionEstimateDeactivateDate = subscriptionEstimateDeactivateDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public Boolean getSubscriptionIsActive() {
        return subscriptionIsActive;
    }

    public void setSubscriptionIsActive(Boolean subscriptionIsActive) {
        this.subscriptionIsActive = subscriptionIsActive;
    }

    public String getSubscriptionDeactiveBy() {
        return subscriptionDeactiveBy;
    }

    public void setSubscriptionDeactiveBy(String subscriptionDeactiveBy) {
        this.subscriptionDeactiveBy = subscriptionDeactiveBy;
    }

    public Date getSubsciptionDeactivateDate() {
        return subsciptionDeactivateDate;
    }

    public void setSubsciptionDeactivateDate(Date subsciptionDeactivateDate) {
        this.subsciptionDeactivateDate = subsciptionDeactivateDate;
    }

    public float getSubscriptionTotalCost() {
        return subscriptionTotalCost;
    }

    public void setSubscriptionTotalCost(float subscriptionTotalCost) {
        this.subscriptionTotalCost = subscriptionTotalCost;
    }

    public Date getSubscriptionEstimateDeactivateDate() {
        return subscriptionEstimateDeactivateDate;
    }

    public void setSubscriptionEstimateDeactivateDate(Date subscriptionEstimateDeactivateDate) {
        this.subscriptionEstimateDeactivateDate = subscriptionEstimateDeactivateDate;
    }

}
