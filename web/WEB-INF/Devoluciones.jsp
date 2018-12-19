<%-- 
    Document   : Devoluciones
    Created on : 05/07/2016, 09:37:31
    Author     : JP
--%>

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
        <%!ArrayList<Usuario> usuarios;%>
        <%!ArrayList<Pedido> enviados;%>
        <% enviados = (ArrayList)session.getAttribute("ListaEnviados");%>
        <% usuarios = (ArrayList)session.getAttribute("ListaEncontrados");%>
        <div class="cuenta">
            <div class="container"> 
                <div class="row">
                    <form action="Controlador" method="post">  
                        <div class="col-lg-5 col-lg-offset-1">
                            <h2 class="title text-center">Buscar Usuario</h2>
                            <input class="control form-control"  type="text" placeholder="ID" maxlength="15" name="ID">
                            <input class="control form-control" type="text" placeholder="Nombre" maxlength="15" name="Nombre">
                            <input class="control form-control" type="text" placeholder="Apellido" name="Apellido" maxlength="15">
                            <input class="control form-control" type="text" placeholder="DNI" name="Dni" pattern="^[0-9]*$" minlength="2" maxlength="9">     
                            <input type="hidden" name="form" value="BuscarUsuarioComando">
                            <button type="submit" class="btn btn-default">Buscar</button>
                        </div>
                    </form>
                    <div class="col-lg-5">
                        <h2 class="title text-center">Lista de Usuarios</h2>
                        <%if(usuarios!=null && !usuarios.isEmpty()){ %>
                        <div class="table-responsive" style="height:250px; overflow:auto;">
                            <div class="table-striped">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Nombre</th>
                                            <th>Apellido</th>
                                            <th>DNI</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%for(Usuario u:usuarios)
                                          {%>
                                          <tr>
                                            <td><%= u.getIdUsuario()%></td>
                                            <td><%= u.getNombre()%></td>
                                            <td><%= u.getApellido()%></td>
                                            <td><%= u.getDni()%></td>
                                            <td>
                                                <form action="Controlador" method="post">
                                                <input type="hidden"  name="form" value="VerPedidosComando"/>
                                                <input type="hidden" name="idUsuPedidos" value="<%= u.getIdUsuario()%>">
                                                <input type="submit" value="Ver Pedidos">
                                                </form>
                                            </td>
                                          </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <%} else if(usuarios!=null && usuarios.isEmpty()){%>
                        <div class="alert alert-danger fade<%if(usuarios.isEmpty()){ %> in <%session.setAttribute("ListaEncontrados", null);} %>">
                                No Existen clientes que coincidan con la búsqueda.       
                        </div>
                        <%}%>
                    </div>
                </div>           
                <%if(session.getAttribute("ExitoCierre")!=null){%>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="alert alert-success fade in">
                        Pedido cerrado con Éxito.
                        </div>
                    </div>
                </div>
                <%} session.setAttribute("ExitoCierre", null);
                if(request.getAttribute("ex")!=null){%>
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
                    <% if(enviados!=null){%>
                    <h2 class="title text-center">Pedidos pendientes de devolucion</h2>
                        <%if(enviados.isEmpty()){%>
                            <div class="alert alert-success fade<%if(enviados.isEmpty()){ %> in <%session.setAttribute("ListaEnviados", null);} %>">
                                El Cliente no tiene pedidos pendientes de devolución.       
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
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%for(Pedido p:enviados){%>
                                        <tr>
                                            <td><%= p.getIdPedido()%></td>
                                            <td><%= p.getFechaDesde() %></td>
                                            <td><%= p.getFechaHasta() %></td>
                                            <td>
                                            <% for(LineaPedido lp: p.getLineas()){%>           
                                                <%=lp.getPelicula().getNombre()%><br>
                                            <%}%> 
                                            </td>
                                            <td><%= p.getRecargo()%></td>
                                            <td>
                                                <form action="Controlador" method="post">
                                                    <input type="hidden"  name="form" value="RegistrarDevolucionComando"/>
                                                    <input type="hidden" name="idPedido" value="<%= p.getIdPedido()%>">
                                                    <input type="submit" value="Registrar Devolución">
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
