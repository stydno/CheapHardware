<%@page import="java.util.List"%>
<%@page import="org.stydno.cheaphardware.tables.Advertisement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <% if (session.getAttribute("allAds") != null) {
            List<Advertisement> ads = (List<Advertisement>) session.getAttribute("allAds");
            String info = "<br><center><table border=1><tr><td><center>";
            for (Advertisement a : ads) {
                String adsId = a.getId().toString();
                info += "<br><b> ID </b>" + a.getId() + " <b>Model</b> " + a.getItem().getModel() + " <b>Price</b> " + a.getPrice()
                        + "<br> <b>Date</b>" + a.getDate() + " <b>Seller's name </b>" + a.getSeller().getName()
                        + "<br><b> Telephone </b>" + a.getSeller().getPhone();

                info += "<br><input type=\"text\" size=\"7\" id=\"newPrice" + adsId + "\" value=\"\"/>";
                info += "<input type=\"button\" id=\"changePrice" + adsId + "\"  value=\"change price\" style=\"width:90Px;height:23Px\"/><br>";
                info += "<input type=\"button\" id=\"delete" + adsId + "\"  value=\"delete ads\" style=\"width:90Px;height:23Px\"/>";

    %>
    <script>$(document).ready(function () {
            $("#delete<%= adsId%>").click(function () {
                $.post('AppServlet', {adsId: "<%= adsId%>", delete: 1}, function (responseText) {
                });
            });
            $("#changePrice<%= adsId%>").click(function () {
                alert('<%= adsId%>');
                $.post('AppServlet', {adsId: "<%=adsId%>", newPrice: $('#newPrice<%=adsId%>').val(), changePrice: 1}, function (responseText) {
                });
            });
        });
    </script>

    <%
            info += " </b><br>";
        }
        info += "</center></tr></td></center></table><br>";%>
    <%= info%>
    <% }%>
</html>