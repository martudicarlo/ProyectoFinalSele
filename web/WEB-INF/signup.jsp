<%@page import="aplicacion.modelo.entidades.Usuario"%>
<%
    Usuario usuPorRegistrar = new Usuario();
    if(request.getAttribute("usuarioPorRegistrar")==null)
    {          
        usuPorRegistrar.setNombreUsuario(request.getParameter("nombreUsuario"));
        usuPorRegistrar.setMail(request.getParameter("email"));
        usuPorRegistrar.setContrasena(request.getParameter("contra1"));        
    }
    else
    {      
        usuPorRegistrar = (Usuario)request.getAttribute("usuarioPorRegistrar");
    }
%>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="head.jsp"/>
    <body>
        <jsp:include page="header.jsp"/>
        <section id="form"><!--form-->
            <div class="container">
                <div class="login-form"><!--login form-->
                    <form action="Controlador" method="post" onsubmit="return validarPass();">
                        <div class="row">
                            <div class="col-lg-10 col-lg-offset-1">
                                <h1>Ya casi terminamos, solo algunos datos mas...</h1>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-5 col-lg-offset-1">
                                <h2>información sobre ti</h2> 
                                <input class="control form-control" type="text" placeholder="Nombre*" maxlength="15" name="Nombre" required <%if(request.getAttribute("usuarioPorRegistrar")!=null){%>value="<%=usuPorRegistrar.getNombre()%>"<%}%>>
                                <input class="control form-control" type="text" placeholder="Apellido*" name="Apellido" maxlength="15" required <%if(request.getAttribute("usuarioPorRegistrar")!=null){%>value="<%=usuPorRegistrar.getApellido()%>"<%}%>>
                                <input class="control form-control" type="text" placeholder="DNI*" name="Dni" pattern="[0-9]{7,9}" maxlength="9" required <%if(request.getAttribute("usuarioPorRegistrar")!=null){%>value="<%=usuPorRegistrar.getDni()%>"<%}%>>
                                <h5>Fecha de Nacimiento</h5>
                                <div class="row">
                                    <div class="col-sm-12 col-sm-offset-0">
                                        <input class="control form-control" type="date" name="fechaNacimiento" required <%if(request.getAttribute("usuarioPorRegistrar")!=null){%>value="<%=usuPorRegistrar.getFechaNacimiento()%>"<%}%>>
                                    </div>                                   
                                </div> 
                                <div class="row">
                                    <div class="col-sm-12 col-sm-offset-0">
                                        <input class="control form-control" type="text" placeholder="Calle*" maxlength="20" name="Calle" required <%if(request.getAttribute("usuarioPorRegistrar")!=null){%>value="<%=request.getAttribute("calle")%>"<%}%>>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-4 col-sm-offset-0">
                                        <input class="control form-control" type="text" placeholder="Número*"  maxlength="6" pattern="^[0-9]*$" name="Num" required <%if(request.getAttribute("usuarioPorRegistrar")!=null){%>value="<%=request.getAttribute("numero")%>"<%}%>>
                                    </div>
                                    <div class="col-sm-4 col-sm-offset-0">
                                        <input class="control form-control" type="text" placeholder="Piso"  maxlength="2" pattern="^[0-9]*$" name="Piso" <%if(request.getAttribute("usuarioPorRegistrar")!=null){%>value="<%=request.getAttribute("piso")%>"<%}%>>
                                    </div>
                                    <div class="col-sm-4 col-sm-offset-0">
                                        <input class="control form-control" type="text" placeholder="Depto."  maxlength="1" pattern="[A-Za-z]" title="Una letra" name="Depto" <%if(request.getAttribute("usuarioPorRegistrar")!=null){%>value="<%=request.getAttribute("depto")%>"<%}%>>
                                    </div>
                                </div>                              
                                <input class="control form-control" type="text" placeholder="Teléfono*" name="Tel" pattern="^[0-9]*$" maxlength="10" required <%if(request.getAttribute("usuarioPorRegistrar")!=null){%>value="<%=usuPorRegistrar.getTelefono()%>"<%}%>>
                                <input class="control form-control" type="text" placeholder="Email*" name="Email" maxlength="30" required value="<%=usuPorRegistrar.getMail()%>">
                            </div>
                            <div class="col-lg-5 col-lg-offset-0">
                                <h2>información sobre tu cuenta</h2> 
                                <input class="control form-control" type="text" placeholder="Nombre de Usuario*" name="Usu" maxlength="10" required value="<%=usuPorRegistrar.getNombreUsuario() %>">  
                                <input id="passA" class="control form-control" type="password"  placeholder="Contraseña*"  pattern="(^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d$@$!%*?&]{8,15})" title="Al menos una minuscula, una mayuscula, un digito y longitud entre 8 y 15" required value="<% if(request.getAttribute("exitoRegistro")== null){%><%=usuPorRegistrar.getContrasena()%><%}%>">
                                <input id="passB" class="control form-control" type="password" placeholder="Confirmar contraseña*"  pattern="(^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d$@$!%*?&]{8,15})" title="Al menos una minuscula, una mayuscula, un digito y longitud entre 8 y 15"  required >
                                <input type="hidden" id="pass1" name="Contra1">
                                <input type="hidden" id="pass2" name="Contra2">
                                
                                <%if(request.getAttribute("ex") != null){%>                                           
                                <div class="alert alert-danger">
                                    <%=request.getAttribute("ex")%>
                                </div>
                                <%}if(request.getAttribute("exitoRegistro") != null){%> 
                                <div class="alert alert-danger">
                                    <%=request.getAttribute("exitoRegistro")%>
                                </div>
                                <%}%>
                            </div>
                        </div><!--/row-->                       
                        <div class="row">
                            <div class="col-lg-10 col-lg-offset-1">
                                <input type="hidden" name="form" value="RegistroComando">
                                <button type="submit" class="btn btn-default">Completar Registro</button>
                                <h5>Los campos marcados con * son obligatorios</h5>
                            </div>
                        </div>
                    </form>
                </div><!--/loggin form-->
            </div><!--/container-->
        </section>
    </body>
    <jsp:include page="footer.jsp"/>
</html>
