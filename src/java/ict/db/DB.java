/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.CategoryBean;
import ict.bean.GiftBean;
import ict.bean.ManufacturerBean;
import ict.bean.OrderBean;
import ict.bean.ProductBean;
import ict.bean.ProductOrderBean;
import ict.bean.UserBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a1
 */
public class DB {

    public DB() {
    }

    private GiftBean gift = null;
    private UserBean user = null;
    private ProductOrderBean productOrder = null;
    private ManufacturerBean manufacturer = null;
    private CategoryBean category = null;
    private OrderBean order = null;
    private ProductBean product = null;
    private String url = "";
    private String username = "";
    private String password = "";

    public DB(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

    }

    public UserBean queryUserById(String uId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {

            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM user WHERE uid=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, uId);
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                user = new UserBean(rs.getString("uid"), rs.getString("uname"),
                        rs.getString("upasswd"), rs.getString("utype"),
                        rs.getString("email"),
                        rs.getInt("creditamount"), rs.getInt("bounspt"),
                        rs.getInt("available"), rs.getString("deliveryAddress"), rs.getInt("a_f_t"),
                        rs.getInt("transactions"), rs.getDouble("total_amount"));
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public Connection getConnection() throws SQLException, IOException {
        System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            //  Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_asg_db", "root", "");
    }

    public ArrayList queryCategory() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList list = new ArrayList();
        try {

            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT* FROM CATEGORY";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                category = new CategoryBean(rs.getString("catId"), rs.getString("catName"));
                list.add(category);
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ArrayList queryUser() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList list = new ArrayList();
        try {

            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT* FROM USER";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                user = new UserBean(rs.getString("uId"), rs.getString("uName"), rs.getString("uPasswd"), rs.getString("uType"), rs.getString("email"), rs.getInt("creditAmount"), rs.getInt("bounspt"), rs.getInt("available"), rs.getString("deliveryAddress"), rs.getInt("a_f_t"), rs.getInt("transactions"), rs.getDouble("total_amount"));

                list.add(user);
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ArrayList queryProductOrder() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList list = new ArrayList();
        try {

            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT* FROM PRODUCT_ORDER";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                productOrder = new ProductOrderBean(rs.getString("oId"), rs.getString("pId"), rs.getString("gId"), rs.getInt("qty"), rs.getDouble("price"));
                list.add(productOrder);
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ArrayList queryManufacturer() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList list = new ArrayList();
        try {

            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT* FROM MANUFACTURER";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                manufacturer = new ManufacturerBean(rs.getString("mId"), rs.getString("mName"));
                list.add(manufacturer);
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ArrayList queryProduct() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList list = new ArrayList();
        try {

            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT* FROM PRODUCT";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                product = new ProductBean(rs.getString("pId"), rs.getString("pName"), rs.getString("description"), rs.getString("mId"), rs.getString("catid"), rs.getDouble("price"), rs.getInt("stockQty"), rs.getString("productphoto"));
                list.add(product);
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public OrderBean queryOrderByOID(String oId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT* FROM UORDER WHERE oId=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setString(1, oId);
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                order = new OrderBean(rs.getString("oId"), rs.getString("uId"), rs.getString("oMode"), rs.getString("oStatus"),
                        rs.getString("odate"), rs.getString("deliverydate"));
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return order;
    }

    public ArrayList queryCategoryByName(String catName) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList list = new ArrayList();

        try {

            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT* FROM CATEGORY WHERE CATNAME=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, catName);
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                category = new CategoryBean(rs.getString("catId"), rs.getString("catName"));
                list.add(category);
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    // Don't Use
    public CategoryBean queryCategoryByID(String catId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT* FROM CATEGORY WHERE CATID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setString(1, catId);
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                category = new CategoryBean(rs.getString("catId"), rs.getString("catName"));
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return category;
    }

    public ManufacturerBean queryManufacturerByMID(String mId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT* FROM MANUFACTURER WHERE MID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setString(1, mId);
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                manufacturer = new ManufacturerBean(rs.getString("mId"), rs.getString("mName"));
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return manufacturer;
    }

    public ProductBean queryProductByID(String pId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {

            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT* FROM PRODUCT WHERE PID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setString(1, pId);
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                product = new ProductBean(rs.getString("pId"), rs.getString("pName"), rs.getString("description"), rs.getString("mId"), rs.getString("catid"), rs.getDouble("price"), rs.getInt("stockQty"), rs.getString("productphoto"));
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return product;
    }

    //Anson Added Method
    public ArrayList<ProductBean> queryProductByKeyword(String keywords) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<ProductBean> products = new ArrayList<ProductBean>();
        try {

            //ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM PRODUCT WHERE pname like ?OR description like?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setString(1, "%" + keywords + "%");
            pStmnt.setString(2, "%" + keywords + "%");
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                products.add(new ProductBean(rs.getString("pId"), rs.getString("pName"), rs.getString("description"), rs.getString("mId"), rs.getString("catid"), rs.getDouble("price"), rs.getInt("stockQty"), rs.getString("productphoto")));
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public ArrayList queryOrder() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList list = new ArrayList();
        try {

            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT* FROM uorder";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                order = new OrderBean(rs.getString("oid"), rs.getString("uid"),
                        rs.getString("omode"), rs.getString("ostatus"),
                        rs.getString("odate"), rs.getString("deliverydate"));
                list.add(order);
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ArrayList queryOrderByUID(String uId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList list = new ArrayList();

        try {

            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT* FROM UORDER WHERE UID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, uId);
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                order = new OrderBean(rs.getString("oId"), rs.getString("uId"), rs.getString("oMode"), rs.getString("oStatus"),
                        rs.getString("odate"), rs.getString("deliverydate"));
                list.add(order);
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ArrayList queryProductByName(String pName) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList list = new ArrayList();

        try {

            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT* FROM PRODUCT WHERE pName=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setString(1, pName);
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                product = new ProductBean(rs.getString("pId"), rs.getString("pName"), rs.getString("description"), rs.getString("mId"), rs.getString("catid"), rs.getDouble("price"), rs.getInt("stockQty"), rs.getString("productphoto"));
                list.add(product);
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ArrayList queryProductByMID(String mId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList list = new ArrayList();
        try {

            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT* FROM PRODUCT WHERE MID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setString(1, mId);
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                product = new ProductBean(rs.getString("pId"), rs.getString("pName"), rs.getString("description"), rs.getString("mId"), rs.getString("catid"), rs.getDouble("price"), rs.getInt("stockQty"), rs.getString("productphoto"));
                list.add(product);
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ArrayList queryProductOrderByID(String oId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList list = new ArrayList();
        try {

            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT* FROM PRODUCT_ORDER WHERE OID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setString(1, oId);
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                productOrder = new ProductOrderBean(rs.getString("oId"), rs.getString("pId"), rs.getString("gId"), rs.getInt("qty"), rs.getDouble("price"));
                list.add(productOrder);
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean editOrderRecord(OrderBean cb) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE UORDER SET UID=? , OMODE=?, OSTATUS=? WHERE OID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, cb.getuId());
            pStmnt.setString(2, cb.getoMode());
            pStmnt.setString(3, cb.getoStatus());
            pStmnt.setString(4, cb.getoId());

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }

            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean editManufacturerRecord(ManufacturerBean cb) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE ManufacturerBean SET MNAME=? WHERE MID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, cb.getmName());
            pStmnt.setString(2, cb.getmId());
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }

            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean editProductRecord(ProductBean cb) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE PRODUCT SET PNAME=? , DESCRIPTION=?, MID=?, catid=?, PRICE=?, STOCKQTY=? WHERE PID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, cb.getpName());
            pStmnt.setString(2, cb.getDescription());
            pStmnt.setString(3, cb.getmId());
            pStmnt.setString(4, cb.getcId());
            pStmnt.setDouble(5, cb.getPrice());
            pStmnt.setInt(6, cb.getStockQty());
            pStmnt.setString(7, cb.getpId());
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }

            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean editCategoryRecord(CategoryBean cb) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE CATEGORY SET CATNAME=? WHERE CATID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, cb.getCatName());
            pStmnt.setString(2, cb.getCatId());
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }

            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean isValidUser(String user, String pwd) {
        boolean isValid = false;
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ResultSet rs = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM USER WHERE uid =  ? and  upasswd =  ? ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, user);
            pStmnt.setString(2, pwd);

            rs = pStmnt.executeQuery();
            if (rs.next()) {
                isValid = true;
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        /*     if (rs.next()) {
         isValid = true;
         }*/
        return isValid;
    }

    public boolean addProductRecord(String pId, String pName, String description, String mId, String cId, double price, int stockQty, String productPhoto) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO PRODUCT VALUES (?,?,?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, pId);
            pStmnt.setString(2, pName);
            pStmnt.setString(3, description);
            pStmnt.setString(4, mId);
            pStmnt.setString(5, cId);
            pStmnt.setDouble(6, price);
            pStmnt.setInt(7, stockQty);
            pStmnt.setString(8, productPhoto);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;

    }

    public boolean addUserInfo(String uId, String uName, String uPasswd, String uType, String email, int creditAmount, double bounspt, int available, String deliveryAddress, String a_f_t, int transactions, double total_amount) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO USER VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, uId);
            pStmnt.setString(2, uName);
            pStmnt.setString(3, uPasswd);
            pStmnt.setString(4, uType);
            pStmnt.setString(5, email);
            pStmnt.setInt(6, creditAmount);
            pStmnt.setDouble(7, bounspt);
            pStmnt.setInt(8, available);
            pStmnt.setString(9, deliveryAddress);
            pStmnt.setString(10, a_f_t);
            pStmnt.setInt(11, transactions);
            pStmnt.setDouble(12, total_amount);

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean addOrderRecord(String oId, String uId, String oMode, String oStatus, String oDate, String deliveryDate) throws ParseException {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO UORDER VALUES (?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, oId);
            pStmnt.setString(2, uId);
            pStmnt.setString(3, oMode);
            pStmnt.setString(4, oStatus);
            /*
            SimpleDateFormat from = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat to = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat to2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            
            
            java.sql.Timestamp timestamp = new java.sql.Timestamp();
            statement.setTimestamp(1, timstamp);

            Calendar c = new GregorianCalendar();
            c.set(Calendar.HOUR_OF_DAY, 0); //anything 0 - 23
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            Date d1 = c.getTime();
             */
            String[] od = oDate.split("-");
            String[] dd = oDate.split("-");
            int year = Integer.parseInt(od[0]);
            int month = Integer.parseInt(od[1]);
            int day = Integer.parseInt(od[2]);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.DAY_OF_MONTH, day);

            pStmnt.setDate(5, new java.sql.Date(cal.getTimeInMillis()));
            year = Integer.parseInt(dd[0]);
            month = Integer.parseInt(dd[1]);
            day = Integer.parseInt(dd[2]);
            cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.DAY_OF_MONTH, day);
            pStmnt.setDate(6, new java.sql.Date(cal.getTimeInMillis()));
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;

    }

    public boolean addProductOrderRecord(String oId, String pId, String gId, int qty, double price) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO PRODUCT_ORDER VALUES (?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, oId);
            pStmnt.setString(2, pId);
            pStmnt.setString(3, gId);
            pStmnt.setInt(4, qty);
            pStmnt.setDouble(5, price);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;

    }

    public boolean addManufacturerRecord(String mId, String mName) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO MANUFACTURER VALUES (?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, mId);
            pStmnt.setString(2, mName);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;

    }

    public boolean addCategoryRecord(String catId, String catName) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO CATEGORY VALUES (?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, catId);
            pStmnt.setString(2, catName);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;

    }

    public boolean delRecord(String table, String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        String preQueryStatement = null;
        try {
            ResultSet rs = null;
            cnnct = getConnection();
            if (table.equals("Category")) {
                preQueryStatement = "DELETE FROM CATEGORY WHERE CATID='" + id + "'";
            }
            if (table.equals("ProductOrder")) {
                preQueryStatement = "DELETE FROM PRODUCT_ORDER WHERE OID='" + id + "'";
            }
            if (table.equals("Product")) {
                preQueryStatement = "DELETE FROM PRODUCT WHERE PID='" + id + "'";
            }
            if (table.equals("Order")) {
                preQueryStatement = "DELETE FROM UORDER WHERE OID='" + id + "'";
            }
            if (table.equals("Manufacturer")) {
                preQueryStatement = "DELETE FROM MANUFACTURER WHERE MID='" + id + "'";
            }

            if (table.equals("Gift")) {
                preQueryStatement = "DELETE FROM gift WHERE gid='" + id + "'";
            }

            pStmnt = cnnct.prepareStatement(preQueryStatement);
            int i = pStmnt.executeUpdate();
            if (i >= 1) {
                return true;
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    //Anson Added Method
    public ArrayList<ProductBean> queryProductByCatID(String catId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<ProductBean> products = new ArrayList<ProductBean>();
        try {

            //ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM PRODUCT WHERE catid=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setString(1, catId);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                products.add(new ProductBean(rs.getString("pId"), rs.getString("pName"), rs.getString("description"), rs.getString("mId"), rs.getString("catid"), rs.getDouble("price"), rs.getInt("stockQty"), rs.getString("productphoto")));
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public ArrayList<ProductBean> queryProductByKeywordPrice(String keywords, double price) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<ProductBean> products = new ArrayList<ProductBean>();
        try {

            String preQueryStatement = null;
            //ResultSet rs = null;
            cnnct = getConnection();
            if (price != 0) {
                preQueryStatement = "SELECT * FROM PRODUCT WHERE (pname like ?OR description like?) AND price<=?";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, "%" + keywords + "%");
                pStmnt.setString(2, "%" + keywords + "%");
                pStmnt.setDouble(3, price);
            } else {
                preQueryStatement = "SELECT * FROM PRODUCT WHERE (pname like ?OR description like?) ";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, "%" + keywords + "%");
                pStmnt.setString(2, "%" + keywords + "%");
            }

            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                products.add(new ProductBean(rs.getString("pId"), rs.getString("pName"), rs.getString("description"), rs.getString("mId"), rs.getString("catid"), rs.getDouble("price"), rs.getInt("stockQty"), rs.getString("productphoto")));
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public ArrayList<ProductBean> queryProductByPrice(double price, String order) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<ProductBean> products = new ArrayList<ProductBean>();
        try {
            String preQueryStatement = null;
            //ResultSet rs = null;
            cnnct = getConnection();
            if (order.equalsIgnoreCase("asc")) {
                preQueryStatement = "SELECT * FROM PRODUCT WHERE price<=? order by price ASC";
            } else {
                preQueryStatement = "SELECT * FROM PRODUCT WHERE price<=? order by price DESC";
            }
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setDouble(1, price);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                products.add(new ProductBean(rs.getString("pId"), rs.getString("pName"), rs.getString("description"), rs.getString("mId"), rs.getString("catid"), rs.getDouble("price"), rs.getInt("stockQty"), rs.getString("productphoto")));
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public String getNextPk(String prefix, String column, String table, int start, int end) {
        String newPk = "Nothing";
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        //ArrayList<ProductBean> products = new ArrayList<ProductBean>();
        try {

            //ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT max(" + column + ") AS lastPK FROM " + table;
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                newPk = rs.getString("lastPk");
            }
            pStmnt.close();
            cnnct.close();
            return prefix + String.format("%03d", (Integer.parseInt(newPk.substring(start, end)) + 1)) + "";

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return newPk;
    }

    public boolean register(String uId, String uname, String uPasswd, String email, String deliveryAddress) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            String uType = "m";
            int creditAmount = 0;
            double bounspt = 0;
            int available = 0;
            int a_f_t = 0;
            int transactions = 0;
            double total_amount = 0;

            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO USER VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, uId);
            pStmnt.setString(2, uname);
            pStmnt.setString(3, uPasswd);
            pStmnt.setString(4, uType);
            pStmnt.setString(5, email);
            pStmnt.setString(6, deliveryAddress);
            pStmnt.setInt(7, creditAmount);
            pStmnt.setDouble(8, bounspt);
            pStmnt.setInt(9, available);
            pStmnt.setInt(10, a_f_t);
            pStmnt.setInt(11, transactions);
            pStmnt.setDouble(12, total_amount);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public String editOrderRecord2(OrderBean cb) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE uorder SET uid=? , omode=?, ostatus=? WHERE oid=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, cb.getuId());
            pStmnt.setString(2, cb.getoMode());
            pStmnt.setString(3, cb.getoStatus());
            pStmnt.setString(4, cb.getoId());

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }

            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //return isSuccess;
        return pStmnt.toString();
    }

    //End of Anson added method
    public ArrayList<GiftBean> queryGiftByCatID(String catId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<GiftBean> gifts = new ArrayList<GiftBean>();
        try {

            //ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM gift WHERE catid=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setString(1, catId);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                gifts.add(new GiftBean(rs.getString("gid"), rs.getString("gname"), rs.getDouble("pt"), rs.getString("description"), rs.getString("catid"), rs.getInt("stockQty"), rs.getString("giftphoto"), rs.getString("mId")));
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return gifts;
    }

    public GiftBean queryGiftById(String gId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {

            //ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM GIFT WHERE GID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setString(1, gId);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                gift = new GiftBean(rs.getString("gid"), rs.getString("gname"), rs.getDouble("pt"), rs.getString("description"), rs.getString("catid"), rs.getInt("stockQty"), rs.getString("giftphoto"), rs.getString("mId"));
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return gift;
    }

    public ArrayList<GiftBean> queryGiftByPoint(double pt, String order) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<GiftBean> gifts = new ArrayList<GiftBean>();
        try {
            String preQueryStatement = null;
            //ResultSet rs = null;
            cnnct = getConnection();
            if (order.equalsIgnoreCase("desc")) {
                preQueryStatement = "SELECT * FROM gift WHERE pt<=? order by price DESC";
            } else {
                preQueryStatement = "SELECT * FROM gift WHERE pt<=? order by price ASC";
            }
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setDouble(1, pt);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                gifts.add(new GiftBean(rs.getString("gid"), rs.getString("gname"), rs.getDouble("pt"), rs.getString("description"), rs.getString("catid"), rs.getInt("stockQty"), rs.getString("giftphoto"), rs.getString("mId")));
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return gifts;
    }

    public ArrayList<GiftBean> queryGiftByKeyword(String keywords) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<GiftBean> gifts = new ArrayList<GiftBean>();
        try {

            //ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM gift WHERE gname like ?OR description like?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setString(1, "%" + keywords + "%");
            pStmnt.setString(2, "%" + keywords + "%");
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                gifts.add(new GiftBean(rs.getString("gid"), rs.getString("gname"), rs.getDouble("pt"), rs.getString("description"), rs.getString("catid"), rs.getInt("stockQty"), rs.getString("giftphoto"), rs.getString("mId")));
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return gifts;
    }

    public ArrayList<GiftBean> queryGift() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList list = new ArrayList();
        try {

            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT* FROM GIFT";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            rs = pStmnt.executeQuery();
            while (rs.next()) {
                gift = new GiftBean(rs.getString("gId"), rs.getString("gName"), rs.getDouble("pt"), rs.getString("description"), rs.getString("catId"), rs.getInt("stockQty"), rs.getString("giftPhoto"), rs.getString("mId"));
                list.add(gift);
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean editGiftRecord(GiftBean cb) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE GIFT SET GNAME=?, PT=? , DESCRIPTION=? , CATID=? , STOCKQTY=?  WHERE GID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, cb.getgName());
            pStmnt.setDouble(2, cb.getPt());
            pStmnt.setString(3, cb.getDescription());
            pStmnt.setString(4, cb.getCatId());
            pStmnt.setInt(5, cb.getStockQty());
            pStmnt.setString(6, cb.getgId());
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }

            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean addGiftRecord(String gId, String gName, double pt, String description, String catId, int stockQty, String giftPhoto, String mId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO GIFT VALUES (?,?,?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, gId);
            pStmnt.setString(2, gName);
            pStmnt.setDouble(3, pt);
            pStmnt.setString(4, description);
            pStmnt.setString(5, catId);
            pStmnt.setInt(6, stockQty);
            pStmnt.setString(7, giftPhoto);
            pStmnt.setString(8, mId);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;

    }

    public UserBean queryUserByUID(String uId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {

            //ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM USER WHERE UID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setString(1, uId);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                user = new UserBean(rs.getString("uId"), rs.getString("uName"), rs.getString("uPasswd"), rs.getString("uType"), rs.getString("email"), rs.getInt("creditAmount"), rs.getInt("bounspt"), rs.getInt("available"), rs.getString("deliveryAddress"), rs.getInt("a_f_t"), rs.getInt("transactions"), rs.getDouble("total_amount"));
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public boolean editUserRecord(UserBean cb) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE user SET uname=?, upasswd=? ,"
                    + " utype=? , email=? , deliveryAddress=?,"
                    + "creditamount=?, bounspt=?, available=?, a_f_t=?,"
                    + "transactions=?, total_amount=? WHERE uid=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, cb.getuName());
            pStmnt.setString(2, cb.getuPasswd());
            pStmnt.setString(3, cb.getuType());
            pStmnt.setString(4, cb.getEmail());
            pStmnt.setString(5, cb.getDeliveryAddress());
            pStmnt.setInt(6, cb.getCreditAmount());
            pStmnt.setInt(7, cb.getBounspt());
            pStmnt.setInt(8, cb.getAvailable());
            pStmnt.setInt(9, cb.getA_f_t());
            pStmnt.setInt(10, cb.getTransactions());
            pStmnt.setDouble(11, cb.getTotal_amount());
            pStmnt.setString(12, cb.getuId());

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }

            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public GiftBean queryGiftByID(String pId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {

            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT* FROM gift WHERE gid=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setString(1, pId);
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                gift = new GiftBean(rs.getString("gid"), rs.getString("gname"),
                        rs.getDouble("pt"), rs.getString("description"),
                        rs.getString("catid"), rs.getInt("stockqty"),
                        rs.getString("giftphoto"), rs.getString("mid"));
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return gift;
    }
    
    public ArrayList queryOrderByUID2(String uId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList list = new ArrayList();

        try {

            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT* FROM UORDER WHERE UID=?  ORDER BY OID DESC LIMIT 10";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, uId);
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                order = new OrderBean(rs.getString("oId"), rs.getString("uId"), rs.getString("oMode"), rs.getString("oStatus"), rs.getString("oDate"), rs.getString("deliveryDate"));
                list.add(order);
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    
    public String queryOrderByUID3(String uId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
     String output=null;

        try {

            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT* FROM UORDER WHERE UID=\"mem001\" ORDER BY OID DESC LIMIT 10";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, uId);
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                output+=rs.getString("uid");
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return output;
    }
}
