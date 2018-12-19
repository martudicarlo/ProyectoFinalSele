<%-- 
    Document   : ABMUsuarios
    Created on : 30/06/2016, 19:40:43
    Author     : JP
--%>
<%@page import="aplicacion.modelo.entidades.Usuario"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp"/>  
    <body onload="scrollDiv();">
        <jsp:include page="header.jsp"/>
        <% if(request.getAttribute("ex")==null)
        {%>
        <%!ArrayList<Usuario> usuarios;%>       
        <%  usuarios = (ArrayList)session.getAttribute("ListaUsuarios");%>
        <%  Usuario usu = (Usuario)session.getAttribute("UsuarioEdit");
            if(request.getAttribute("usuarioPorAgregar")!=null)
            {          
                 usu = (Usuario)request.getAttribute("usuarioPorAgregar");
            }        
        %>
        <div class="cuenta">
            <div class="container"> 
                <div class="row">
                    <h2 class="title text-center">Lista de Usuarios</h2> 
                    <div class="col-sm-12">
                        <div class="table-responsive" style="height:400px; overflow:auto">
                            <div class="table-striped">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Usuario</th>
                                            <th>Nombre</th>
                                            <th>Apellido</th>
                                            <th>DNI</th>
                                            <th>F. Nac</th>
                                            <th>Email</th>
                                            <th>Telefono</th>
                                            <th>Activo</th>
                                            <th>Admin</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td> - </td>
                                            <td> - </td>
                                            <td> - </td>
                                            <td> - </td>
                                            <td> - </td>
                                            <td> - </td>
                                            <td> - </td>
                                            <td> - </td>
                                            <td> - </td>
                                            <td> - </td>
                                            <td>
                                                <form action="Controlador" method="post">
                                                    <input type="hidden"  name="form" value="SeleccionarUsuarioComando"/>
                                                    <input type="hidden" name="idUsuEdit" value="0">
                                                    <input type="submit" value="+ Nuevo">
                                                </form>
                                            </td>
                                        </tr>
                                        <%for(Usuario u:usuarios){%>
                                        <tr>
                                            <td><%= u.getIdUsuario()%></td>
                                            <td><%= u.getNombreUsuario() %></td>
                                            <td><%= u.getNombre()%></td>
                                            <td><%= u.getApellido()%></td>
                                            <td><%= u.getDni()%></td>
                                            <td><%= u.getFechaNacimiento()%></td>
                                            <td><%= u.getMail() %></td>
                                            <td><%= u.getTelefono() %></td>
                                            <td><%if(u.isActivo()){%><img src="./imagenes/check.png"><%}%></td>
                                            <td><%if(u.isEsAdmin()){%><img src="./imagenes/check.png"><%}%></td>
                                            <td>
                                                <form action="Controlador" method="post">
                                                    <input type="hidden"  name="form" value="SeleccionarUsuarioComando"/>
                                                    <input type="hidden" name="idUsuEdit" value="<%=u.getIdUsuario()%>">
                                                    <input type="submit" value="Editar">
                                                </form>
                                            </td>
                                        </tr>
                                         <%}%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div> 
                </div>
                <form action="Controlador" method="post" onsubmit="return validarPass()" >
                    <div <%if(session.getAttribute("Scroll")!=null){%> id="Edit" <%session.setAttribute("Scroll", null); };%>  class="row">
                    <br/>    
                    <h2 class="title text-center"><%if(usu!=null  && request.getAttribute("usuarioPorAgregar")==null) {%>EDITAR<%}else{%>AGREGAR<%}%> USUARIO</h2>
                    <div class="col-sm-6">
                        <h2>Información del Usuario</h2>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h4 class="text-left">ID</h4>
                                </div>
                                <div class="col-sm-9">
                                    <input class="control form-control"  type="text" placeholder="(Automático)" maxlength="15" name="ID" readonly="" value="<%if(usu!=null && request.getAttribute("usuarioPorAgregar")==null)%><%=usu.getIdUsuario()%>">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h4 class="text-left">Nombre</h4>
                                </div>
                                <div class="col-sm-9">
                                    <input class="control form-control" type="text" placeholder="*" maxlength="15" pattern="^[a-zA-Z ]*$" title="Letras" name="Nombre" required value="<%if(usu!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%= usu.getNombre() %>">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h4 class="text-left">Apellido</h4>
                                </div>
                                <div class="col-sm-9">
                                    <input class="control form-control" type="text" placeholder="*" name="Apellido" maxlength="15" pattern="^[a-zA-Z ]*$" title="Letras" required value="<%if(usu!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%= usu.getApellido() %>" >
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h4 class="text-left">DNI</h4>
                                </div>
                                <div class="col-sm-9">
                                    <input class="control form-control" type="text" placeholder="*" name="Dni" pattern="^[0-9]*$" minlength="7" maxlength="9" required value="<%if(usu!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%= usu.getDni() %>">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h4 class="text-left">F. Nac.</h4>
                                </div>
                                <div class="col-sm-9">
                                    <input class="control form-control" type="date" name="fechaNacimiento" required value="<%if(usu!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%= usu.getFechaNacimiento() %>">
                                </div>                                   
                            </div> 
                            <div class="row">
                                <div class="col-sm-3">
                                    <h4 class="text-left">Dirección</h4>
                                </div>
                                <div class="col-sm-9">
                                    <input class="control form-control" type="text" placeholder="*" maxlength="20" name="Calle" required value="<%if(usu!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%= usu.getDireccion() %>">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h4 class="text-left">Teléfono</h4>
                                </div>
                                <div class="col-sm-9">
                                    <input class="control form-control" type="text" placeholder="*" name="Tel" pattern="^[0-9]*$" maxlength="10" required value="<%if(usu!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%= usu.getTelefono() %>">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h4 class="text-left">E-Mail</h4>
                                </div>
                                <div class="col-sm-9">
                                    <input class="control form-control" type="text" placeholder="*" name="Email" maxlength="30" required value="<%if(usu!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%= usu.getMail() %>">
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6 ">
                            <h2>Información de la cuenta</h2>
                            <div class="row">
                                <div class="col-sm-5">
                                    <h4 class="text-left">Nombre de Usuario</h4>
                                </div>
                                <div class="col-sm-7">
                                    <input class="control form-control" type="text" placeholder="*" name="Usu" maxlength="10" required value="<%if(usu!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%= usu.getNombreUsuario() %>">
                                </div>
                            </div>
                            <% if(usu==null) {%>
                            <div class="row">
                                <div class="col-sm-5">
                                    <h4 class="text-left">Contraseña</h4>
                                </div>
                                <div class="col-sm-7">
                                    <input id="passA" class="control form-control" type="password" placeholder="*"  pattern="(^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d$@$!%*?&]{8,15})" title="Al menos una minuscula, una mayuscula, un digito y longitud entre 8 y 15"  required value="<%if(usu!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%= usu.getContrasena() %>">
                                    <input type="hidden" id="pass1" name="Contra1">                       
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-5">
                                    <h4 class="text-left">Confirmar Contraseña</h4>
                                </div>
                                <div class="col-sm-7">
                                    <input id="passB" class="control form-control" type="password" placeholder="*"  pattern="(^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d$@$!%*?&]{8,15})" title="Al menos una minuscula, una mayuscula, un digito y longitud entre 8 y 15"  required value="<%if(usu!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%= usu.getContrasena() %>">
                                    <input type="hidden" id="pass2" name="Contra2">
                                </div>
                            </div>
                            <%}%>
                            <h2>Estado de la cuenta</h2>
                            <div class="row">
                                <div class="col-sm-12">
                                    <label class="puntero"><input class="enLinea" type="checkbox" name="Admin" value="true" <%if((usu!=null || request.getAttribute("usuarioPorAgregar")!=null) && usu.isEsAdmin())%>checked<%;%>><h4 class="enLinea">Administrador</h4></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12">                                                           
                                    <label class="puntero"><input class="enLinea" type="checkbox" name="Activo" value="true" <% if((usu!=null || request.getAttribute("usuarioPorAgregar")!=null) && usu.isActivo())%>checked<%;%>><h4 class="enLinea">Activo</h4></label>
                                </div>
                            </div>    
                            <div class="row">
                                <div class="col-sm-12">                                                                  
                                    <label class="puntero"><input class="enLinea" type="checkbox" name="Bloqueado" value="true" <%if((usu!=null || request.getAttribute("usuarioPorAgregar")!=null) && usu.isBloqueado())%>checked<%;%>><h4 class="enLinea">Bloqueado</h4> </label>
                                </div>
                            </div>
                            <%if(request.getAttribute("ExitoUsu")!=null){
                            if((Boolean)request.getAttribute("ExitoUsu")){%>
                                <div class="alert alert-success">
                                    <p class="text-center">Usuario <%if(usu==null){ %>agregado<% }else{%>editado<%}%> con éxito.</p>      
                                </div>
                            <%} else if(!(Boolean)request.getAttribute("ExitoUsu")){%>
                                <div class="alert alert-danger">
                                    <p class="text-center">El nombre de usuario ya existe.</p>        
                                </div>  
                          
                            <%}} if(request.getAttribute("ex")!=null){%>
                                <div class="alert alert-danger">
                                    <p class="text-center"><%=request.getAttribute("ex")%></p>
                               </div>
                             <%}%>
                        </div>
                    </div>          
                    <div class="col-sm-12">                      
                        <input type="hidden" name="form" value="<%if(usu!=null && request.getAttribute("usuarioPorAgregar")==null) {%>EditarUsuarioComando<%}else{%>AgregarUsuarioComando<%}%>">
                        <button type="submit" class="btn btn-default"><%if(usu!=null && request.getAttribute("usuarioPorAgregar")==null) {%>Guardar Cambios<%}else{%>Crear Usuario<%}%></button>
                        <h5 class="text-left">Los campos marcados con * son obligatorios</h5>   
                    </div>        
                </form>
            </div>
        </div>              
    <%}else{%>
        <div class="alert alert-danger">
            <%=request.getAttribute("ex")%>
        </div>
    <%}%>
    </body>
    <jsp:include page="footer.jsp"/>
</html>
