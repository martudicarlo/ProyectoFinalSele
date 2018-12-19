<%-- 
    Document   : pedidos
    Created on : 04/08/2016, 14:49:47
    Author     : USER
--%>

<%@page import="aplicacion.modelo.entidades.LineaPedido"%>
<%@page import="aplicacion.modelo.entidades.Pedido"%>
<%@page import="java.util.ArrayList"%>
<%@page import="aplicacion.modelo.entidades.Usuario"%>
<html>
    <jsp:include page="head.jsp"/>
    <body onload="scrollDiv();">
        <jsp:include page="header.jsp"/>
        <%!ArrayList<Pedido> pedidos;%>
        <% pedidos = (ArrayList)request.getAttribute("pedidos"); %>
        <div class="cuenta">
            <div class="container"> 
                <div <%if(session.getAttribute("Scroll")!=null){%> id="Edit" <%session.setAttribute("Scroll", null); };%> class="row">
                    <div class="col-lg-12">
                        <%if(request.getAttribute("ex")!=null){%>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="alert alert-danger">
                                    <%=request.getAttribute("ex")%>
                                </div>
                            </div>
                        </div>
                        <%} else if(pedidos!=null){%>
                        <h2 class="title text-center">Historial de Pedidos</h2>
                        <%if(pedidos.isEmpty()){%>
                        <div class="alert alert-danger">
                            Usted nunca ha realizado pedidos.       
                        </div>
                        <%}else{%>
                        <div class="table-responsive">
                            <div class="table-striped">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>ID Pedido</th>
                                            <th>F Desde</th>
                                            <th>F Hasta</th>
                                            <th>Peliculas</th>
                                            <th>Recargo</th>
                                            <th>Estado</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <%for(Pedido p:pedidos){%>
                                        <tr>
                                            <td><%= p.getIdPedido()%></td>
                                            <td><%= p.getFechaDesde() %></td>
                                            <td><%= p.getFechaHasta() %></td>
                                            <td>
                                            <% for(LineaPedido lp: p.getLineas()){
                                            if(lp.isEsAlquiler()){%>Alquiler<%}else{%>Compra<%}%> - <%=lp.getPelicula().getNombre()%><br>                                          
                                            <%}%> 
                                            </td>
                                            <td>$ <%= p.getRecargo()%></td>
                                            <td><%= p.getEstado()%></td>
                                        </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <%}}%>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <jsp:include page="footer.jsp"/>
</html>
