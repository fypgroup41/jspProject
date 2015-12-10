/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.tag;

import ict.bean.CategoryBean;
import ict.bean.GiftBean;
import ict.bean.ManufacturerBean;
import ict.bean.OrderBean;
import ict.bean.ProductBean;
import ict.bean.ProductOrderBean;
import ict.bean.UserBean;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author test
 */
public class TableTag extends SimpleTagSupport {

    private ArrayList beanName = new ArrayList();

    public ArrayList getBeanName() {
        return beanName;
    }

    public void setBeanName(ArrayList beanName) {
        this.beanName = beanName;
    }

    @Override
    public void doTag() {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();
        try {

            if (beanName.get(0) instanceof ProductBean) {
                outputProduct(beanName, out);
            }
        /*    if (beanName.get(0) instanceof ProductOrderBean) {
                outputProductOrder(beanName, out);
            }
            if (beanName.get(0) instanceof OrderBean) {
                outputOrder(beanName, out);
            }
            if (beanName.get(0) instanceof CategoryBean) {
                outputCategory(beanName, out);
            }
            if (beanName.get(0) instanceof ManufacturerBean) {
                outputManufacturer(beanName, out);
            }
            if (beanName.get(0) instanceof UserBean) {
                outputUser(beanName, out);
            }
            if (beanName.get(0) instanceof GiftBean) {
                outputGift(beanName, out);
            }*/
            StringWriter sw = new StringWriter();
            this.getJspBody().invoke(sw);
            this.getJspContext().getOut().println(sw.toString());

        } catch (Exception ex) {
        }

    }

    public void outputProduct(ArrayList<ProductBean> products, JspWriter out) throws IOException {

        out.println("<h1>Products</h1>");
        out.println("<table border='1' >");
        out.println("<tr>");
        out.println("<th>Product Photo</th><th>Product Name</th> <th>Product Description</th><th>Price</th><th>Stock Quantity</th >");
        out.println("</tr>");
        // loop through the customer array to display each customer record
        if (products.isEmpty()) {
            out.println("<td colspan = 4 align = center>No Such Product(s)</td>");
        }
        for (int i = 0; i < products.size(); i++) {
            ProductBean p = products.get(i);
            out.println("<tr>");
            out.println("<td><img src=\"img/" + "\"></img> </td>");
            out.println("<td>" + p.getpName() + "</td>");
            out.println("<td>" + p.getDescription() + "</td>");
            out.println("<td>$" + p.getPrice() + "</td>");
            out.println("<td>" + p.getStockQty() + "</td>");

            out.println("</tr>");
        }
        out.println("</table>");

    }
/*
    public void outputProductOrder(ArrayList<ProductOrderBean> productOrder, JspWriter out) throws IOException {

        out.println("<h1>Product Order</h1>");
        out.println("<table border='1' >");
        out.println("<tr>");
        out.println("<th>Order ID</th> <th> Product ID</th><th> Gift ID</th><th> Qty </th ><th> Price </th >");
        out.println("</tr>");
        for (int i = 0; i < productOrder.size(); i++) {
            ProductOrderBean c = productOrder.get(i);
            out.println("<tr>");
            out.println("<td>" + c.getoId() + "</td>");
            out.println("<td>" + c.getpId() + "</td>");
            out.println("<td>" + c.getgId() + "</td>");
            out.println("<td>" + c.getQty() + "</td>");
            out.println("<td>$ " + c.getPrice() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");

    }

    public void outputOrder(ArrayList<OrderBean> order, JspWriter out) throws IOException {

        out.println("<h1>Order</h1>");
        out.println("<table border='1' >");
        out.println("<tr>");
        out.println("<th>Order ID </th> <th> User ID</th><th>Order Mode</th><th>Order Status</th>");
        out.println("</tr>");
        // loop through the customer array to display each customer record
        for (int i = 0; i < order.size(); i++) {
            OrderBean c = order.get(i);
            out.println("<tr>");
            out.println("<td>" + c.getoId() + "</td>");
            out.println("<td>" + c.getuId() + "</td>");
            out.println("<td>" + c.getoMode() + "</td>");
            out.println("<td>" + c.getoStatus() + "</td>");
            //out.println("<td>" + "<a href='editCustomer?action=delete&id=" + c.getCustId() + "'>delete</a>" + "</td>");
            //out.println("<td>" + "<a href='editCustomer?action=getEditCustomer&id=" + c.getCustId() + "'>edit</a>" + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");

    }

    public void outputCategory(ArrayList<CategoryBean> category, JspWriter out) throws IOException {

        out.println("<h1>Category</h1>");
        out.println("<table border='1' >");
        out.println("<tr>");
        out.println("<th>Category ID</th> <th>Category Name</th>");
        out.println("</tr>");
        // loop through the customer array to display each customer record
        for (int i = 0; i < category.size(); i++) {
            CategoryBean c = category.get(i);
            out.println("<tr>");
            out.println("<td>" + c.getCatId() + "</td>");
            out.println("<td>" + c.getCatName() + "</td>");

            //out.println("<td>" + "<a href='editCustomer?action=delete&id=" + c.getCustId() + "'>delete</a>" + "</td>");
            //out.println("<td>" + "<a href='editCustomer?action=getEditCustomer&id=" + c.getCustId() + "'>edit</a>" + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");

    }

    public void outputManufacturer(ArrayList<ManufacturerBean> manufacturer, JspWriter out) throws IOException {

        out.println("<h1>Manufacturer</h1>");
        out.println("<table border='1' >");
        out.println("<tr>");
        out.println("<th>Manufacturer ID</th> <th> Name </th>");
        out.println("</tr>");
        // loop through the customer array to display each customer record
        for (int i = 0; i < manufacturer.size(); i++) {
            ManufacturerBean c = manufacturer.get(i);
            out.println("<tr>");
            out.println("<td>" + c.getmId() + "</td>");
            out.println("<td>" + c.getmName() + "</td>");

            //out.println("<td>" + "<a href='editCustomer?action=delete&id=" + c.getCustId() + "'>delete</a>" + "</td>");
            //out.println("<td>" + "<a href='editCustomer?action=getEditCustomer&id=" + c.getCustId() + "'>edit</a>" + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");

    }

    public void outputUser(ArrayList<UserBean> user, JspWriter out) throws IOException {

        out.println("<h1>User</h1>");
        out.println("<table border='1' >");
        out.println("<tr>");
        out.println("<th>User ID</th> <th> User Password </th><th> User Type </th><th> Email </th><th> Credit Amount </th><th> Bouns Point </th><th> Available </th>");
        out.println("</tr>");
        // loop through the customer array to display each customer record
        out.println(user.size());
        for (int i = 0; i < user.size(); i++) {
            UserBean c = user.get(i);
            out.println("<tr>");
            out.println("<td>" + c.getuId() + "</td>");
            out.println("<td>" + c.getuPasswd() + "</td>");
            out.println("<td>" + c.getuType() + "</td>");
            out.println("<td>" + c.getEmail() + "</td>");
            out.println("<td>" + c.getCreditAmount() + "</td>");
            out.println("<td>" + c.getBounspt() + "</td>");
            out.println("<td>" + c.getAvailable() + "</td>");

            //out.println("<td>" + "<a href='editCustomer?action=delete&id=" + c.getCustId() + "'>delete</a>" + "</td>");
            //out.println("<td>" + "<a href='editCustomer?action=getEditCustomer&id=" + c.getCustId() + "'>edit</a>" + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");

    }

    public void outputGift(ArrayList<GiftBean> gifts, JspWriter out) throws IOException {
        out.println("<h1>Gift</h1>");
        out.println("<table border='1' class=\"sortable\">");
        out.println("<tr>");
        out.println("<th>Gift Photo</th><th>Gift Name</th> <th>Gift Description</th><th>Point Required</th><th>Stock Quantity</th >");
        out.println("</tr>");
        // loop through the customer array to display each customer record
        if (gifts.size() == 0) {
            out.println("<td colspan = 5 align = center>No Such Gift(s)</td>");
        }
        for (int i = 0; i < gifts.size(); i++) {
            GiftBean g = gifts.get(i);
            out.println("<tr>");
            out.println("<td><img src=\"img/" + g.getGiftPhoto()+ "\" width=\"200\" height=\"200\"></img> </td>");
            out.println("<td>" + g.getgName() + "</td>");
            out.println("<td>" + g.getDescription() + "</td>");
            out.println("<td>" + g.getPt() + "pt</td>");
            out.println("<td>" + g.getStockQty() + "</td>");
     
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("<a href=\"searchGift.jsp\">Back To Search</a>");
    }
*/
}
