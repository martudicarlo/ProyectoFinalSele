<%@page import="aplicacion.modelo.entidades.LineaPedido"%>
<%@page import="aplicacion.modelo.entidades.Pedido"%>
<%@page import="aplicacion.modelo.entidades.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp"/>
    <body onload="scrollDiv();">
        <jsp:include page="header.jsp"/>
        <%!ArrayList<Pedido> pendientes;%>
        <% pendientes = (ArrayList)request.getSession().getAttribute("pendientes");%>  
        <div class="cuenta">
            <div class="container">
            <%if(request.getAttribute("ExitoEnvio")!=null){%>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="alert alert-success fade in">
                            Envío registrado.
                        </div>
                    </div>
                </div>
                <%}if(request.getAttribute("ex")!=null){%>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="alert alert-danger">
                           <%=request.getAttribute("ex")%>
                        </div>
                    </div>
                </div>
                <%}%>                      
                <div <%if(session.getAttribute("Scroll")!=null){%> id="Edit" <%session.setAttribute("Scroll", null); };%> class="row">
                    <div class="col-lg-12">
                        <% if(pendientes!=null){%>
                        <h2 class="title text-center">Pedidos pendientes de envío</h2>
                        <%if(pendientes.isEmpty()){%>
                        <div class="alert alert-success fade<%if(pendientes.isEmpty()){ %> in <%session.setAttribute("pendientes", null);} %>">
                            No existen pedidos pendientes de envío.       
                        </div>
                        <%}else{%>
                        <div class="table-responsive">
                            <div class="table-striped">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                        <th>ID Pedido</th>
                                        <th>F Desde</th>
                                        <th>Socio</th>
                                        <th>Peliculas</th>
                                        <th>Destino</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <%for(Pedido p:pendientes){%>
                                        <tr>
                                            <td><%= p.getIdPedido()%></td>
                                            <td><%= p.getFechaDesde() %></td>
                                            <td><%= p.getUsuario().getApellido()%>, <%= p.getUsuario().getNombre()%></td>
                                            <td>
                                            <% for(LineaPedido lp: p.getLineas()){%>                                           
                                            <%=lp.getPelicula().getNombre()%> (<%if(lp.isEsAlquiler()){%>Alquiler<%}else{%>Compra<%}%>)<br>
                                            <%}%> 
                                            </td>
                                            <td><%= p.getUsuario().getDireccion()%></td>
                                            <td>
                                                <form action="Controlador" method="post">
                                                    <input type="hidden"  name="form" value="RegistrarEnvioComando"/>
                                                    <input type="hidden" name="idPedido" value="<%= p.getIdPedido()%>">
                                                    <input type="submit" value="Registrar Envío">
                                                </form>
                                            </td>
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
