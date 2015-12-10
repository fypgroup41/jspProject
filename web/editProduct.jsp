<%-- 
    Document   : editProduct
    Created on : 2015年11月27日, 下午01:42:43
    Author     : kwok1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@page import="ict.bean.*"%>
        <%@page import="java.util.ArrayList"%>
        <title>JSP Page</title>
        <style>
            th {
                text-align: left;
            }
        </style>
        <script>
            var _validFileExtensions = [".jpg", ".jpeg", ".png"];
            function Validate(oForm) {
                var arrInputs = oForm.getElementsByTagName("input");
                for (var i = 0; i < arrInputs.length; i++) {
                    var oInput = arrInputs[i];
                    if (oInput.type == "file") {
                        var sFileName = oInput.value;
                        if (sFileName.length > 0) {
                            var blnValid = false;
                            for (var j = 0; j < _validFileExtensions.length; j++) {
                                var sCurExtension = _validFileExtensions[j];
                                if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) {
                                    blnValid = true;
                                    break;
                                }
                            }

                            if (!blnValid) {
                                alert("Sorry, Your file is invalid, allowed extensions are: " + _validFileExtensions.join(", "));
                                return false;
                            }
                        }
                    }
                }

                return true;
            }
        </script>
    </head>
    <body>
        <jsp:include page="/template/header.jsp"/>
        <%
            ProductBean pb = (ProductBean) request.getAttribute("pb");
            ArrayList<CategoryBean> cb = (ArrayList<CategoryBean>) request.getAttribute("cb");
            ArrayList<ManufacturerBean> mb = (ArrayList<ManufacturerBean>) request.getAttribute("mb");
            String type = pb.getpId() != null ? "edit" : "add";
            String pId = pb.getpId() != null ? pb.getpId() : "";
            String pName = pb.getpName() != null ? pb.getpName() : "";
            String description = pb.getDescription() != null ? pb.getDescription() : "";
            String mId = pb.getmId() != null ? pb.getmId() : "";
            String cId = pb.getcId() != null ? pb.getcId() : "";
            double price = pb != null ? pb.getPrice() : 0;
            int stockQty = pb != null ? pb.getStockQty() : 0;
            if (type.equals("add")) {
                out.println("<h1>Add Product</h1>");
            } else {
                out.println("<h1>Edit Product</h1>");
            }
        %>
        <form method="post" action="handleProduct" enctype="multipart/form-data" onsubmit="return Validate(this);">
            <table>
                <tr>
                <input type="hidden" name="action" value="<%=type%>" />
                <th>ID: </th><td><input name="pId" type="text"  value="<%out.print(pId);%>" <% if (pId.equals("")) {
                    } else {
                        out.print("readonly=\"readonly\"");
                    }%>/> </td>
                <tr><th>Name: </th><td><input name="pName" type="text" required value="<%=pName%>"/> </td></tr>
                <tr> <th>Description: </th><td>
                        <textarea rows="4" cols="25" name="description" required><%=description%></textarea> </td></tr>
                <tr> <th>Manufacturer: </th><td><select name="mId">
                        <%
                            for (int x = 0; x < mb.size(); x++) {
                                String id = mb.get(x).getmId();
                                String name = mb.get(x).getmName();
                                if (id.equals(mId)) {
                                    out.println("<option value=\"" + id + "\" selected>" + name + "</option>");
                                } else {
                                    out.println("<option value=\"" + id + "\">" + name + "</option>");
                                }
                            }
                        %>
                        </select> </td></tr>
                <tr> <th>Category: </th><td><select name="cId">
                        <%
                            for (int x = 0; x < mb.size(); x++) {
                                String id = cb.get(x).getCatId();
                                String name = cb.get(x).getCatName();
                                if (id.equals(cId)) {
                                    out.println("<option value=\"" + id + "\" selected>" + name + "</option>");
                                } else {
                                    out.println("<option value=\"" + id + "\">" + name + "</option>");
                                }
                            }
                        %>
                        </select> </td></tr>
                <tr>  <th>Price: </th><td><input name="price" type="number" step="0.01" min="0" required value="<%=price%>"/> </td></tr>
                <tr>  <th>Stock Qty: </th><td><input name="stockQty" type="number" min="0" pattern="\d+" required value="<%=stockQty%>"/> </td></tr>
                <tr><th>Photo:</th><td><input type="file" name="image"/></td></tr>
                <tr> <td ><input type="submit" value="submit"/> <br></td></tr>
            </table>
        </form>
        <br/>
        <button type="button"><a href="handleProduct?action=list" class="bk">Back</a></button>
        <jsp:include page="/template/footer.jsp"/>
    </body>
</html>
