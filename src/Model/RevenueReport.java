/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author Shah
 */
public class RevenueReport {
    public Date fromDate;
    public Date toDate;
   public ArrayList<PackageSubscription> detail = new ArrayList<>();

    public RevenueReport() {
    }

    public RevenueReport(Date fromDate, Date toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public ArrayList<PackageSubscription> getDetail() {
        return detail;
    }

    public void setDetail(ArrayList<PackageSubscription> detail) {
        this.detail = detail;
    }

    
}
