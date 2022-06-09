package Model;

import java.sql.Date;

public class User{
public String userId;
public String fullName;
public String email;
public String password;

    public User(String userId, String fullName, String email, String password) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }
public String status;
public Boolean active;
public Date insertionDate;
public Date deactivationDate;
public String deactivatedBy;

    public User() {
    }

    public User(String userId, String fullName, String email, String password, String status, Boolean active, Date insertionDate, Date deactivationDate, String deactivatedBy) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.active = active;
        this.insertionDate = insertionDate;
        this.deactivationDate = deactivationDate;
        this.deactivatedBy = deactivatedBy;
    }

    public User(String userId, String fullName, String email, String password, String status, Boolean active, Date insertionDate) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.active = active;
        this.insertionDate = insertionDate;
    }

    public User(String userId, String fullName, String email, String password, String status, Boolean active) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.active = active;
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

    public Date getInsertionDate() {
        return insertionDate;
    }

    public void setInsertionDate(Date insertionDate) {
        this.insertionDate = insertionDate;
    }

    public Date getDeactivationDate() {
        return deactivationDate;
    }

    public void setDeactivationDate(Date deactivationDate) {
        this.deactivationDate = deactivationDate;
    }

    public String getDeactivatedBy() {
        return deactivatedBy;
    }

    public void setDeactivatedBy(String deactivatedBy) {
        this.deactivatedBy = deactivatedBy;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", fullName=" + fullName + ", email=" + email + ", password=" + password + ", status=" + status + ", active=" + active + ", insertionDate=" + insertionDate + ", deactivationDate=" + deactivationDate + ", deactivatedBy=" + deactivatedBy + '}';
    }
    
    
   
    
}