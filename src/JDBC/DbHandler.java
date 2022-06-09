package JDBC;

import Model.Audit;
import Model.CheckIn;
import Model.CheckOut;
import Model.CheckPointList;
import Model.User;
import Model.Package;
import Model.PackageSubscription;
import Model.RevenueReport;
import Model.SitePackages;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

public class DbHandler {

    private Connection con;
    private String DBURL = "jdbc:mysql://localhost/movementrecord";
    private String DBUser = "root";
    private String DBPassword = "";

    //connection
    private void connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DBURL, DBUser, DBPassword);
        } catch (Exception e) {
            System.err.println("Error1");
            System.err.println(e);
        }

    }

    private void close() {
        try {
            con.close();
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    //Users Table Related Quries
    public boolean register(User user) {
        if (isAlreadyExist(user.getEmail())) {
            return false;
        }
        connection();
        PreparedStatement prst = null;
        String query = "INSERT INTO User(userid,fullName,email,password,status,active,insertionDate) VALUES(?,?,?,?,?,?,?);";
        try {
            prst = con.prepareStatement(query);
            prst.setString(1, user.getUserId());
            prst.setString(2, user.getFullName());
            prst.setString(3, user.getEmail());
            prst.setString(4, user.getPassword());
            prst.setString(5, user.getStatus());
            prst.setBoolean(6, user.getActive());
            prst.setDate(7, new Date(System.currentTimeMillis()));

            prst.execute();
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return false;
        }
        this.close();
        return true;

    }

    public boolean login(String userId, String password) {
        connection();
        PreparedStatement prst = null;
        try {
            String query = "SELECT * FROM User WHERE userid =?  AND password=?;";
            prst = con.prepareStatement(query);
            prst.setString(1, userId);
            prst.setString(2, password);
            ResultSet rs = prst.executeQuery();

            if (rs.next()) {
                this.close();
                return true;
            }
            this.close();
            return false;
        } catch (Exception e) {
            this.close();
            System.err.println(e);
            return false;
        }
    }

    public boolean isAlreadyExist(String id) {
        connection();
        try {
            String query = "SELECT * FROM User WHERE userid =?;";
            PreparedStatement prst = null;
            prst = con.prepareStatement(query);
            prst.setString(1, id);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                this.close();
                return true;
            }
            this.close();
            return false;
        } catch (Exception e) {
            System.err.println(e);
            this.close();
            return false;
        }

    }

    public User getUser(String userid) {
        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM User WHERE userid=?;";
        User user = null;
        try {
            prst = con.prepareStatement(query);
            prst.setString(1, userid);
            ResultSet rs = prst.executeQuery();

            if (rs.next()) {

                String fname = rs.getString(2);
                String password = rs.getString(4);
                String email = rs.getString(3);

                user = new User();
                user.setFullName(fname);
                user.setPassword(password);
                user.setEmail(email);

                con.close();
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return user;
        }
        return user;
    }

    public String getStatus(String userId) {
        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM User WHERE userid=?;";
        try {
            prst = con.prepareStatement(query);
            prst.setString(1, userId);
            ResultSet rs = prst.executeQuery();

            if (rs.next()) {
                return rs.getString(5);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return null;
        }
        return null;
    }

    public boolean isActive(String userId) {
        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM User WHERE userid=?;";
        try {
            prst = con.prepareStatement(query);
            prst.setString(1, userId);
            ResultSet rs = prst.executeQuery();

            if (rs.next()) {
                return rs.getBoolean(6);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return false;
        }
        return false;
    }

    public boolean updateUser(User u) {
        connection();
        PreparedStatement prst = null;
        String query = "UPDATE user SET password=?, fullName=?, email=? where userid=?";
        try {
            prst = (PreparedStatement) con.prepareStatement(query);
            prst.setString(1, u.getPassword());
            prst.setString(2, u.getFullName());
            prst.setString(3, u.getEmail());
            prst.setString(4, u.getUserId());
            int rows = prst.executeUpdate();
            if (rows > 0) {
                this.close();
                return true;
            } else {
                this.close();
                return false;
            }
        } catch (Exception e) {
            this.close();
            e.printStackTrace();
            return false;

        }
    }

    public ArrayList<User> getUsers(String userid) {
        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM User WHERE userid!=?;";
        ArrayList<User> list = new ArrayList<>();
        try {
            prst = con.prepareStatement(query);
            prst.setString(1, userid);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                String userId = rs.getString(1);
                String fname = rs.getString(2);
                String emails = rs.getString(3);
                String password = rs.getString(4);
                String status = rs.getString(5);
                boolean active = rs.getBoolean(6);
                Date activationDate = rs.getDate(7);
                if (rs.getString(8) == null) {
                    list.add(new User(userId, fname, emails, password, status, active, activationDate));

                } else {
                    Date deactivateDate = rs.getDate(8);
                    String deactivateBy = rs.getString(9);
                    list.add(new User(userId, fname, emails, password, status, active, activationDate, deactivateDate, deactivateBy));

                }
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

    public boolean updateActivation(String deactivatedBy, String userid, boolean activate) {
        connection();
        PreparedStatement prst = null;
        String query = "UPDATE user SET active=? ,DeactivationDate=?,DeactivatedBy=? where userid=?";
        try {
            prst = (PreparedStatement) con.prepareStatement(query);

            prst.setBoolean(1, activate);
            prst.setDate(2, new Date(System.currentTimeMillis()));
            prst.setString(3, deactivatedBy);
            prst.setString(4, userid);
            int rows = prst.executeUpdate();
            if (rows > 0) {
                this.close();
                return true;
            } else {
                this.close();
                return false;
            }
        } catch (Exception e) {
            this.close();
            e.printStackTrace();
            return false;

        }
    }

    public ArrayList<Audit> getAudits(String userid) {
        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM audit WHERE userid=?;";
        ArrayList<Audit> list = new ArrayList<>();
        try {
            prst = con.prepareStatement(query);
            prst.setString(1, userid);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                String userId = rs.getString(1);
                String fname = rs.getString(2);
                String emails = rs.getString(3);
                String password = rs.getString(4);
                String status = rs.getString(5);
                boolean active = rs.getBoolean(6);
           
                Date date = rs.getDate(7);

                list.add(new Audit(userId, fname, emails, password, status, active,  date));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

//    public boolean deleteUser(String email){
//        connection();
//        PreparedStatement prst=null;
//        String query="DELETE FROM public.\"User\" WHERE email=?";
//        try{
//        prst=(PreparedStatement) con.prepareStatement(query);
//      
//        prst.setString(1,email);
//       
//        int rows=prst.executeUpdate();
//        if (rows>0)
//        {
//            this.close();
//            return true;
//        }
//        else
//        {
//            this.close();
//            return false;
//        }
//        }catch(Exception e)
//        {
//            this.close();
//            e.printStackTrace();
//        	return false;
//            
//        }
//	
//    }
    //Package Table Queries
    public ArrayList<Package> getPackages() {
        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM package where packageIsActive=" + true;
        ArrayList<Package> list = new ArrayList<Package>();

        try {
            prst = con.prepareStatement(query);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);

                String name = rs.getString(2);
                int sites = rs.getInt(3);
                int checks = rs.getInt(4);
                Date validity = Date.valueOf(rs.getString(5).toString());
                float cost = rs.getFloat(6);
                int duration = rs.getInt(7);
                Boolean isActive = rs.getBoolean(8);
                String createdBy = rs.getString(9);
                list.add(new Package(id, name, sites, checks, validity, cost, duration, isActive, createdBy));
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        this.close();
        return list;
    }

        public ArrayList<Package> getAllPackages() {
        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM package";
        ArrayList<Package> list = new ArrayList<Package>();

        try {
            prst = con.prepareStatement(query);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);

                String name = rs.getString(2);
                int sites = rs.getInt(3);
                int checks = rs.getInt(4);
                Date validity = Date.valueOf(rs.getString(5).toString());
                float cost = rs.getFloat(6);
                int duration = rs.getInt(7);
                Boolean isActive = rs.getBoolean(8);
                String createdBy = rs.getString(9);
                list.add(new Package(id, name, sites, checks, validity, cost, duration, isActive, createdBy));
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        this.close();
        return list;
    }

    
    
    public boolean addPackage(Package p) {
        connection();
        PreparedStatement prst = null;
        String query = "INSERT INTO package(packageName,sites,checkpoints,validity,cost,duration,packageIsActive,PackageCreatedBy) VALUES(?,?,?,?,?,?,?,?);";

        try {
            prst = con.prepareStatement(query);
            prst.setString(1, p.getPackageName());
            prst.setInt(2, p.getSites());
            prst.setInt(3, p.getCheckpoints());
            prst.setDate(4, p.getValidity());
            prst.setFloat(5, p.getCost());
            prst.setInt(6, p.getDuration());
            prst.setBoolean(7, p.getPackageIsActive());
            prst.setString(8, p.getPackageCreatedBy());

            prst.execute();
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return false;
        }
        this.close();
        return true;
    }

    public void renewPackage(int pakageID) {
        PackageSubscription temp = this.getPackageubscriptionById(pakageID);
        Package packTmep = this.getPackageById(temp.getPackageId());
        connection();
        PreparedStatement prst = null;
        String query = "UPDATE `packagesubscription` SET `subscriptionTotalCost`=?,`subscriptionEstimateDeactivateDate`=? WHERE id=?";

        try {
            prst = con.prepareStatement(query);
            prst.setInt(3, pakageID);
            prst.setFloat(1, temp.subscriptionTotalCost + packTmep.cost);
            Timestamp timestamp = new Timestamp(temp.subscriptionEstimateDeactivateDate.getTime());
            LocalDateTime loc = timestamp.toLocalDateTime().plusDays(packTmep.duration);

            prst.setDate(2, Date.valueOf(loc.toLocalDate()));

            prst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return;
        }
        this.close();
        return;

    }

    public Package getPackageById(int id) {
        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM package where packageId=?";
        Package tempPack = new Package();
        try {
            prst = con.prepareStatement(query);
            prst.setInt(1, id);

            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                tempPack.setPackageId(rs.getInt(1));

                tempPack.setPackageName(rs.getString(2));
                tempPack.setSites(rs.getInt(3));
                tempPack.setCheckpoints(rs.getInt(4));
                tempPack.setValidity(Date.valueOf(rs.getString(5).toString()));
                tempPack.setCost(rs.getFloat(6));
                tempPack.duration = rs.getInt(7);
                tempPack.packageIsActive = rs.getBoolean(8);
                tempPack.packageCreatedBy = rs.getString(9);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return tempPack;
        }
        this.close();
        return tempPack;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {

//            LocalDateTime.
//            Date temp = Date.valueOf(.toString());
//            System.out.println(temp);
            DbHandler db = new DbHandler();
//        User user = new User("user", "user@gmail.com", "user", "03124691226");
            Package p = new Package(1, "Basic", 5, 10, new Date(Timestamp.valueOf(LocalDateTime.now()).getTime()), 12.2f, 7, true, "WE");
            //System.out.println(db.register(user));
            //System.out.println(db.getAudits("emails"));
            System.out.println(db.addPackage(p));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //Package Subscriber Table Query
    public ArrayList<PackageSubscription> getAllPackageSubsriber() {

        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM packagesubscription;";
        ArrayList<PackageSubscription> list = new ArrayList<>();
        try {
            prst = con.prepareStatement(query);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                PackageSubscription temp = new PackageSubscription();

                temp.packageId = rs.getInt(2);
                temp.id = rs.getInt(1);
                temp.userId = rs.getString(3);
                temp.subscriptionDate = Date.valueOf(rs.getString(4));
                temp.subscriptionIsActive = rs.getBoolean(5);
                temp.subscriptionTotalCost = rs.getFloat(8);
                temp.subscriptionEstimateDeactivateDate = Date.valueOf(rs.getString(9));

                if (!temp.subscriptionIsActive) {
                    temp.subscriptionDeactiveBy = rs.getString(6);
                    temp.subsciptionDeactivateDate = Date.valueOf(rs.getString(7));

                }
                list.add(temp);
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

    public Boolean addPackageSubsriber(String userId, int pakageId) {
        try {

            Package temp = this.getPackageById(pakageId);
            this.connection();
            String query = "INSERT INTO `packagesubscription`( `packageId`, `userId`, `subscriptionDate`, `subscriptionIsActive`, `subscriptionTotalCost`, `subscriptionEstimateDeactivateDate`) VALUES (?,?,?,?,?,?)";
            PreparedStatement prst = null;
            prst = con.prepareStatement(query);
            prst.setInt(1, pakageId);
            prst.setString(2, userId);
            prst.setDate(3, new Date(System.currentTimeMillis()));
            prst.setBoolean(4, true);
            prst.setFloat(5, temp.cost);
            prst.setDate(6, new Date(Timestamp.valueOf(LocalDateTime.now().plusDays(temp.duration)).getTime()));
            prst.execute();
        } catch (Exception ex) {
            this.close();
            return false;

        }
        this.close();

        return true;
    }

    public ArrayList<PackageSubscription> getAllActivePackageSubsriber() {

        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM packagesubscription where subscriptionIsActive=" + true;
        ArrayList<PackageSubscription> list = new ArrayList<>();
        try {
            prst = con.prepareStatement(query);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                PackageSubscription temp = new PackageSubscription();

                temp.packageId = rs.getInt(2);
                temp.id = rs.getInt(1);
                temp.userId = rs.getString(3);
                temp.subscriptionDate = Date.valueOf(rs.getString(4));
                temp.subscriptionIsActive = rs.getBoolean(5);
                temp.subscriptionTotalCost = rs.getFloat(8);
                temp.subscriptionEstimateDeactivateDate = Date.valueOf(rs.getString(9));

                if (!temp.subscriptionIsActive) {
                    temp.subscriptionDeactiveBy = rs.getString(6);
                    temp.subsciptionDeactivateDate = Date.valueOf(rs.getString(7));

                }
                list.add(temp);
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

    public ArrayList<PackageSubscription> getAllSubsriberByUserId(String userId) {

        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM packagesubscription where userId=?";
        ArrayList<PackageSubscription> list = new ArrayList<>();
        try {

            prst = con.prepareStatement(query);
            prst.setString(1, userId);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                PackageSubscription temp = new PackageSubscription();

                temp.packageId = rs.getInt(2);
                temp.id = rs.getInt(1);
                temp.userId = rs.getString(3);
                temp.subscriptionDate = Date.valueOf(rs.getString(4));
                temp.subscriptionIsActive = rs.getBoolean(5);
                temp.subscriptionTotalCost = rs.getFloat(8);
                temp.subscriptionEstimateDeactivateDate = Date.valueOf(rs.getString(9));

                if (!temp.subscriptionIsActive) {
                    temp.subscriptionDeactiveBy = rs.getString(6);
                    temp.subsciptionDeactivateDate = Date.valueOf(rs.getString(7));

                }
                list.add(temp);
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

    public ArrayList<PackageSubscription> getAllSubsriberByPackageId(int packId) {

        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM packagesubscription where packageId=?";
        ArrayList<PackageSubscription> list = new ArrayList<>();
        try {

            prst = con.prepareStatement(query);
            prst.setInt(1, packId);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                PackageSubscription temp = new PackageSubscription();

                temp.packageId = rs.getInt(2);
                temp.id = rs.getInt(1);
                temp.userId = rs.getString(3);
                temp.subscriptionDate = Date.valueOf(rs.getString(4));
                temp.subscriptionIsActive = rs.getBoolean(5);
                temp.subscriptionTotalCost = rs.getFloat(8);
                temp.subscriptionEstimateDeactivateDate = Date.valueOf(rs.getString(9));

                if (!temp.subscriptionIsActive) {
                    temp.subscriptionDeactiveBy = rs.getString(6);
                    temp.subsciptionDeactivateDate = Date.valueOf(rs.getString(7));
                }
                list.add(temp);

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

    public ArrayList<PackageSubscription> getAllSubsriberByPackageIdAndUserID(String userId, int packId) {

        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM packagesubscription where packageId=? and userId=?";
        ArrayList<PackageSubscription> list = new ArrayList<>();
        try {

            prst = con.prepareStatement(query);
            prst.setInt(1, packId);
            prst.setString(2, userId);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                PackageSubscription temp = new PackageSubscription();

                temp.packageId = rs.getInt(2);
                temp.id = rs.getInt(1);
                temp.userId = rs.getString(3);
                temp.subscriptionDate = Date.valueOf(rs.getString(4));
                temp.subscriptionIsActive = rs.getBoolean(5);
                temp.subscriptionTotalCost = rs.getFloat(8);
                temp.subscriptionEstimateDeactivateDate = Date.valueOf(rs.getString(9));

                if (!temp.subscriptionIsActive) {
                    temp.subscriptionDeactiveBy = rs.getString(6);
                    temp.subsciptionDeactivateDate = Date.valueOf(rs.getString(7));
                }
                list.add(temp);

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

    public ArrayList<RevenueReport> getRevenueReport(Date from, Date to, int interval) {
        ArrayList<RevenueReport> temp = new ArrayList<>();
        try {

            Date next;
            do {
                Timestamp timestamp = new Timestamp(from.getTime());

                LocalDateTime loc = null;
                if (interval == 1) {
                    loc = timestamp.toLocalDateTime().plusDays(1);
                } else if (interval == 7) {
                    loc = timestamp.toLocalDateTime().plusWeeks(1);
                } else if (interval == 30) {
                    loc = timestamp.toLocalDateTime().plusMonths(1);
                } else if (interval == 365) {
                    loc = timestamp.toLocalDateTime().plusYears(1);
                }
                next = Date.valueOf(loc.toLocalDate());

                RevenueReport tem = new RevenueReport();
                tem.fromDate = from;
                if(to.before(next))
                {
                    next=to;
                }
                tem.toDate = next;

                tem.detail = this.getAllSubsriberBetweenDate(from, next);
                temp.add(tem);
                from = next;
            } while (from.before(to));

        } catch (Exception ex) {
        }
        return temp;
    }

    public ArrayList<PackageSubscription> getAllSubsriberBetweenDate(Date from, Date to) {

        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM packagesubscription where subscriptionDate >= ?and subscriptionDate<?";
        ArrayList<PackageSubscription> list = new ArrayList<>();
        try {

            prst = con.prepareStatement(query);
            prst.setDate(1, from);
            prst.setDate(2, to);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                PackageSubscription temp = new PackageSubscription();

                temp.packageId = rs.getInt(2);
                temp.id = rs.getInt(1);
                temp.userId = rs.getString(3);
                temp.subscriptionDate = Date.valueOf(rs.getString(4));
                temp.subscriptionIsActive = rs.getBoolean(5);
                temp.subscriptionTotalCost = rs.getFloat(8);
                temp.subscriptionEstimateDeactivateDate = Date.valueOf(rs.getString(9));

                if (!temp.subscriptionIsActive) {
                    temp.subscriptionDeactiveBy = rs.getString(6);
                    temp.subsciptionDeactivateDate = Date.valueOf(rs.getString(7));
                }
                list.add(temp);

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

    public PackageSubscription getPackageubscriptionById(int id) {
        PackageSubscription temp = new PackageSubscription();

        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM packagesubscription where id=?";
        ArrayList<PackageSubscription> list = new ArrayList<>();
        try {

            prst = con.prepareStatement(query);
            prst.setInt(1, id);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {

                temp.packageId = rs.getInt(2);
                temp.id = rs.getInt(1);
                temp.userId = rs.getString(3);
                temp.subscriptionDate = Date.valueOf(rs.getString(4));
                temp.subscriptionIsActive = rs.getBoolean(5);
                temp.subscriptionTotalCost = rs.getFloat(8);
                temp.subscriptionEstimateDeactivateDate = Date.valueOf(rs.getString(9));

                if (!temp.subscriptionIsActive) {
                    temp.subscriptionDeactiveBy = rs.getString(6);
                    temp.subsciptionDeactivateDate = Date.valueOf(rs.getString(7));
                }

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return temp;
        }
        return temp;

    }

    //Sites Data Table
    public ArrayList<SitePackages> getAllSitesBySubsciberPackageId(int packId) {

        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM sitepackages where packageSubscriptionId=?";
        ArrayList<SitePackages> list = new ArrayList<>();
        try {

            prst = con.prepareStatement(query);
            prst.setInt(1, packId);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                SitePackages temp = new SitePackages();

                temp.packageSubscriptionId = rs.getInt(2);
                temp.id = rs.getInt(1);
                temp.name = rs.getString(3);
                temp.activateDate = Date.valueOf(rs.getString(5));
                temp.isActive = rs.getBoolean(4);

                if (!temp.isActive) {
                    temp.deactivateDate = Date.valueOf(rs.getString(6));
                }
                list.add(temp);

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

    public ArrayList<SitePackages> getAllSites() {

        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM sitepackages";
        ArrayList<SitePackages> list = new ArrayList<>();
        try {

            prst = con.prepareStatement(query);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                SitePackages temp = new SitePackages();

                temp.packageSubscriptionId = rs.getInt(2);
                temp.id = rs.getInt(1);
                temp.name = rs.getString(3);
                temp.activateDate = Date.valueOf(rs.getString(5));
                temp.isActive = rs.getBoolean(4);

                if (!temp.isActive) {
                    temp.deactivateDate = Date.valueOf(rs.getString(6));
                }
                list.add(temp);

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

    public ArrayList<SitePackages> getAllSitesByUserId(String userId) {

        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM sitepackages where packageSubscriptionId in (select id from packagesubscription where userId=?)";
        ArrayList<SitePackages> list = new ArrayList<>();
        try {

            prst = con.prepareStatement(query);
            prst.setString(1, userId);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                SitePackages temp = new SitePackages();

                temp.packageSubscriptionId = rs.getInt(2);
                temp.id = rs.getInt(1);
                temp.name = rs.getString(3);
                temp.activateDate = Date.valueOf(rs.getString(5));
                temp.isActive = rs.getBoolean(4);

                if (!temp.isActive) {
                    temp.deactivateDate = Date.valueOf(rs.getString(6));
                }
                list.add(temp);

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

    public int getSiteCount(int packId) {
        connection();
        PreparedStatement prst = null;
        String query = "SELECT count(*) FROM sitepackages where packageSubscriptionId=? and isActive=" + true;
        try {

            prst = con.prepareStatement(query);
            prst.setInt(1, packId);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return -1;
        }
        return 0;

    }

    public int getTotalSitesAllow(int packId) {
        connection();
        PreparedStatement prst = null;
        String query = "SELECT sites FROM package where packageId in (select packageId from packagesubscription where id=?)";
        try {

            prst = con.prepareStatement(query);
            prst.setInt(1, packId);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return -1;
        }
        return -1;

    }

    public Boolean addSite(int packId, String name) {
        connection();
        PreparedStatement prst = null;
        String query = "INSERT INTO `sitepackages`( `packageSubscriptionId`, `siteName`, `isActive`, `activateDate`) VALUES (?,?,?,?)";

        try {
            prst = con.prepareStatement(query);
            prst.setString(2, name);
            prst.setInt(1, packId);
            prst.setBoolean(3, true);
            prst.setDate(4, new Date(System.currentTimeMillis()));

            prst.execute();
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return false;
        }
        this.close();
        return true;

    }

    public void changeStatusSite(int packId, Boolean status,int packSubsId) {
        connection();;

        PreparedStatement prst = null;
        String query = "UPDATE `sitepackages` SET `isActive`=?,`deactivateDate`=? WHERE id=? and ?=(select subscriptionIsActive from packagesubscription where id=? )";

        try {
            prst = con.prepareStatement(query);
            prst.setInt(3, packId);
            prst.setBoolean(1, status);
            prst.setDate(2, new Date(System.currentTimeMillis()));
prst.setInt(5, packSubsId);

prst.setBoolean(4, true);
            prst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return;
        }
        this.close();
        return;
    }

//CheckPoint Table Query
    public ArrayList<CheckPointList> getAllCheckPointssBySiteId(int siteId) {

        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM checkpointlist where sitePackageId=?";
        ArrayList<CheckPointList> list = new ArrayList<>();
        try {

            prst = con.prepareStatement(query);
            prst.setInt(1, siteId);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                CheckPointList temp = new CheckPointList();

                temp.sitePackageId = rs.getInt(2);
                temp.checkPointId = rs.getInt(1);
                temp.checkPointName = rs.getString(3);
                temp.activationDate = Date.valueOf(rs.getString(5));
                temp.isActive = rs.getBoolean(4);

                if (!temp.isActive) {
                    temp.deactivationDate = Date.valueOf(rs.getString(6));
                }
                list.add(temp);

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

    public ArrayList<CheckPointList> getAllCheckPoints() {

        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM checkpointlist";
        ArrayList<CheckPointList> list = new ArrayList<>();
        try {

            prst = con.prepareStatement(query);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                CheckPointList temp = new CheckPointList();

                temp.sitePackageId = rs.getInt(2);
                temp.checkPointId = rs.getInt(1);
                temp.checkPointName = rs.getString(3);
                temp.activationDate = Date.valueOf(rs.getString(5));
                temp.isActive = rs.getBoolean(4);

                if (!temp.isActive) {
                    temp.deactivationDate = Date.valueOf(rs.getString(6));
                }
                list.add(temp);

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

    public ArrayList<CheckPointList> getAllCheckPointsByUserId(String userID) {

        connection();
        PreparedStatement prst = null;
        String query = "select * from checkpointlist where sitePackageId in ( SELECT id FROM sitepackages where packageSubscriptionId in (select id from packagesubscription where userId=?)";
        ArrayList<CheckPointList> list = new ArrayList<>();
        try {

            prst = con.prepareStatement(query);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                CheckPointList temp = new CheckPointList();

                temp.sitePackageId = rs.getInt(2);
                temp.checkPointId = rs.getInt(1);
                temp.checkPointName = rs.getString(3);
                temp.activationDate = Date.valueOf(rs.getString(5));
                temp.isActive = rs.getBoolean(4);

                if (!temp.isActive) {
                    temp.deactivationDate = Date.valueOf(rs.getString(6));
                }
                list.add(temp);

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

    public int getCheckPointCount(int siteId) {
        connection();
        PreparedStatement prst = null;
        String query = "SELECT count(*) FROM checkpointlist where sitePackageId=? and isActive=" + true;
        try {

            prst = con.prepareStatement(query);
            prst.setInt(1, siteId);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return -1;
        }
        return 0;

    }

    public int getTotalCheckPointsAllow(int siteId) {
        connection();
        PreparedStatement prst = null;
        String query = "SELECT sites FROM package where packageId in (select packageId from packagesubscription where id=(select packageSubscriptionId  from sitepackages where id=?  ))";
        try {

            prst = con.prepareStatement(query);
            prst.setInt(1, siteId);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return -1;
        }
        return -1;

    }

    public Boolean addCheckPoint(int siteId, String name) {
        connection();
        PreparedStatement prst = null;
        String query = "INSERT INTO `checkpointlist`( `sitePackageId`, `checkPointName`, `isActive`, `activationDate`) VALUES (?,?,?,?)";

        try {
            prst = con.prepareStatement(query);
            prst.setString(2, name);
            prst.setInt(1, siteId);
            prst.setBoolean(3, true);
            prst.setDate(4, new Date(System.currentTimeMillis()));

            prst.execute();
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return false;
        }
        this.close();
        return true;

    }

    public void changeStatusCheckpoint(int siteId, Boolean status) {
        connection();
        PreparedStatement prst = null;
        String query = "UPDATE `checkpointlist` SET `isActive`=?,`deactivationDate`=? WHERE id=?";

        try {
            prst = con.prepareStatement(query);
            prst.setInt(3, siteId);
            prst.setBoolean(1, status);
            prst.setDate(2, new Date(System.currentTimeMillis()));

            prst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return;
        }
        this.close();
        return;
    }

    //Check InTable Query
    public ArrayList<CheckIn> getAllCheckInDate() {

        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM checkin";
        ArrayList<CheckIn> list = new ArrayList<>();
        try {

            prst = con.prepareStatement(query);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                CheckIn temp = new CheckIn();

                temp.userId = rs.getString(2);
                temp.id = rs.getInt(1);
                temp.checkPointId = rs.getInt(3);
                temp.date = Date.valueOf(rs.getString(5));
                temp.bodyTemperature = rs.getFloat(4);
                temp.time = rs.getTime(6);
                list.add(temp);

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

    public ArrayList<CheckIn> geChectkInDateByCheckPoint(int id) {

        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM checkin where id=?";
        ArrayList<CheckIn> list = new ArrayList<>();
        try {

            prst = con.prepareStatement(query);
            prst.setInt(1, id);

            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                CheckIn temp = new CheckIn();

                temp.userId = rs.getString(2);
                temp.id = rs.getInt(1);
                temp.checkPointId = rs.getInt(3);
                temp.date = Date.valueOf(rs.getString(5));
                temp.bodyTemperature = rs.getFloat(4);
                temp.time = rs.getTime(6);
                list.add(temp);

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

    public ArrayList<CheckIn> geCheckInDataByUserId(String id) {

        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM checkin where userId=?";
        ArrayList<CheckIn> list = new ArrayList<>();
        try {

            prst = con.prepareStatement(query);
            prst.setString(1, id);

            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                CheckIn temp = new CheckIn();

                temp.userId = rs.getString(2);
                temp.id = rs.getInt(1);
                temp.checkPointId = rs.getInt(3);
                temp.date = Date.valueOf(rs.getString(5));
                temp.bodyTemperature = rs.getFloat(4);
                temp.time = rs.getTime(6);
                list.add(temp);

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

    public ArrayList<CheckIn> geCheckInDataByUserIdAndCheckPoint(String id, int point) {

        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM checkin where userId=? and  checkPointId=?";
        ArrayList<CheckIn> list = new ArrayList<>();
        try {

            prst = con.prepareStatement(query);
            prst.setString(1, id);

            prst.setInt(2, point);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                CheckIn temp = new CheckIn();

                temp.userId = rs.getString(2);
                temp.id = rs.getInt(1);
                temp.checkPointId = rs.getInt(3);
                temp.date = Date.valueOf(rs.getString(5));
                temp.bodyTemperature = rs.getFloat(4);
                temp.time = rs.getTime(6);
                list.add(temp);

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

    public Boolean addCheckIn(CheckIn temp) {
        connection();
        PreparedStatement prst = null;
        String query = "INSERT INTO `checkin`( `userId`, `checkPointId`, `bodyTemperature`, `date`, `time`) VALUES (?,?,?,?,?)";

        try {
            prst = con.prepareStatement(query);
            prst.setString(1, temp.userId);
            prst.setInt(2, temp.checkPointId);
            prst.setFloat(3, temp.bodyTemperature);
            prst.setDate(4, temp.date);
            prst.setTime(5, Time.valueOf(LocalTime.now()));

            prst.execute();
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return false;
        }
        this.close();
        return true;

    }

    public ArrayList<CheckIn> geCheckInDataByUserIdAndCheckPointAndDate(String id, int point, Date from, Date to) {

        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM checkin where userId=? and  checkPointId=? and date Between ? and ?";
        ArrayList<CheckIn> list = new ArrayList<>();
        try {

            prst = con.prepareStatement(query);
            prst.setString(1, id);

            prst.setInt(2, point);

            prst.setDate(3, from);
            prst.setDate(4, to);

            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                CheckIn temp = new CheckIn();

                temp.userId = rs.getString(2);
                temp.id = rs.getInt(1);
                temp.checkPointId = rs.getInt(3);
                temp.date = Date.valueOf(rs.getString(5));
                temp.bodyTemperature = rs.getFloat(4);
                temp.time = rs.getTime(6);
                list.add(temp);

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

    //Check OutTable Query
    public ArrayList<CheckOut> getAllCheckOutDate() {

        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM checkin";
        ArrayList<CheckOut> list = new ArrayList<>();
        try {

            prst = con.prepareStatement(query);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                CheckOut temp = new CheckOut();

                temp.userId = rs.getString(2);
                temp.id = rs.getInt(1);
                temp.checkPointId = rs.getInt(3);
                temp.date = Date.valueOf(rs.getString(5));
                temp.bodyTemperature = rs.getFloat(4);
                temp.time = rs.getTime(6);
                list.add(temp);

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

    public ArrayList<CheckOut> geChectkOutDateByCheckPoint(int id) {

        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM checkin where id=?";
        ArrayList<CheckOut> list = new ArrayList<>();
        try {

            prst = con.prepareStatement(query);
            prst.setInt(1, id);

            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                CheckOut temp = new CheckOut();

                temp.userId = rs.getString(2);
                temp.id = rs.getInt(1);
                temp.checkPointId = rs.getInt(3);
                temp.date = Date.valueOf(rs.getString(5));
                temp.bodyTemperature = rs.getFloat(4);
                temp.time = rs.getTime(6);
                list.add(temp);

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

    public Boolean addCheckOut(CheckOut temp) {
        connection();
        PreparedStatement prst = null;
        String query = "INSERT INTO `checkout`( `userId`, `checkPointId`, `bodyTemperature`, `date`, `time`) VALUES (?,?,?,?,?)";

        try {
            prst = con.prepareStatement(query);
            prst.setString(1, temp.userId);
            prst.setInt(2, temp.checkPointId);
            prst.setFloat(3, temp.bodyTemperature);
            prst.setDate(4, temp.date);
            prst.setTime(5, Time.valueOf(LocalTime.now()));

            prst.execute();
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return false;
        }
        this.close();
        return true;

    }

    public ArrayList<CheckOut> geCheckOutDataByUserId(String id) {

        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM checkout"
                + " where userId=?";
        ArrayList<CheckOut> list = new ArrayList<>();
        try {

            prst = con.prepareStatement(query);
            prst.setString(1, id);

            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                CheckOut temp = new CheckOut();

                temp.userId = rs.getString(2);
                temp.id = rs.getInt(1);
                temp.checkPointId = rs.getInt(3);
                temp.date = Date.valueOf(rs.getString(5));
                temp.bodyTemperature = rs.getFloat(4);
                temp.time = rs.getTime(6);
                list.add(temp);

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

    public ArrayList<CheckOut> geCheckOutDataByUserIdAndCheckPoint(String id, int point) {

        connection();
        PreparedStatement prst = null;
        String query = "SELECT * FROM checkout where userId=? and  checkPointId=?";
        ArrayList<CheckOut> list = new ArrayList<>();
        try {

            prst = con.prepareStatement(query);
            prst.setString(1, id);

            prst.setInt(2, point);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                CheckOut temp = new CheckOut();

                temp.userId = rs.getString(2);
                temp.id = rs.getInt(1);
                temp.checkPointId = rs.getInt(3);
                temp.date = Date.valueOf(rs.getString(5));
                temp.bodyTemperature = rs.getFloat(4);
                temp.time = rs.getTime(6);
                list.add(temp);

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return list;
        }
        return list;
    }

    
    
    
    
    
    
    //Systems Queries
    
    public void updatePackageExpire()
    {
           connection();
        PreparedStatement prst = null;
        String query = "UPDATE `package` SET `packageIsActive`=? WHERE validity<? and packageIsActive="+true;

        try {
            prst = con.prepareStatement(query);
   prst.setBoolean(1, false);
            prst.setDate(2, new Date(System.currentTimeMillis()));

            prst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return;
        }
        this.close();
        return;
    }
    

 public void updateSubscriptionExpire()
    {
           connection();
        PreparedStatement prst = null;
        String query = "UPDATE `packagesubscription` SET `subscriptionIsActive`=?,`subscriptionDeactiveDate`=?,`subscriptionDeactiveBy`=? WHERE `subscriptionEstimateDeactivateDate`<? and `subscriptionIsActive`="+true;

        try {
            prst = con.prepareStatement(query);
            prst.setDate(4, new Date(System.currentTimeMillis()));
   prst.setBoolean(1, false);
            prst.setDate(2, new Date(System.currentTimeMillis()));
prst.setString(3, "System Autmation");
            prst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return;
        }
        this.close();
        return;
    }


 
 public void updateSitesExpire()
    {
           connection();
        PreparedStatement prst = null;
        String query = "UPDATE `sitepackages` SET `isActive`=?,`deactivateDate`=?  WHERE `isActive`=true and packageSubscriptionId in (select id from packagesubscription where subscriptionEstimateDeactivateDate<?)";

        try {
            prst = con.prepareStatement(query);
            prst.setDate(3, new Date(System.currentTimeMillis()));
   prst.setBoolean(1, false);
            prst.setDate(2, new Date(System.currentTimeMillis()));

            prst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return;
        }
        this.close();
        return;
    }

 
 
  public void updateCheckPointsExpire()
    {
           connection();
        PreparedStatement prst = null;
        String query = "UPDATE `sitepackages` SET `isActive`=?,`	deactivationDate`=?  WHERE `isActive`=true and sitePackageId in (select id from sitepackages where isActive=false and deactivateDate=?)";

        try {
            prst = con.prepareStatement(query);
            prst.setDate(3, new Date(System.currentTimeMillis()));
   prst.setBoolean(1, false);
            prst.setDate(2, new Date(System.currentTimeMillis()));

            prst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
            return;
        }
        this.close();
        return;
    }
 
 
}
