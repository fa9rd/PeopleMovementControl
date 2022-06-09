package Model;

import java.sql.Date;

public class Audit {

    public String userId;
    public String fullName;
    public String email;
    public String password;
    public String status;
    public Boolean active;

    public Date changeDate;

    public Audit() {
    }

    public Audit(String userId, String fullName, String email, String password, String status, Boolean active, Date changeDate) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.active = active;
        this.changeDate = changeDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }


    
}
