<%@page import="aplicacion.modelo.entidades.Pedido"%>
<html lang="en">
    <jsp:include page="head.jsp"/>
    <body>
        <jsp:include page="header.jsp"/>     
        <section id="form"><!--form-->
            <div class="container">
                <div class="row">
                    <div class="col-sm-4 col-sm-offset-1">
                        <%if(session.getAttribute("pedido")!=null){
                            if(session.getAttribute("usuarioNoLogueado")!=null){
                        %> 
                        <br/>
                        <div class="alert alert-danger">
                            Necesita loguearse para poder finalizar un pedido 
                        </div>
                        <%}}%>
                        <div class="login-form"><!--login form-->
                            <h2>Ingresá con tu usuario</h2>
                            <form action="Controlador" method="post" onsubmit="calcHash()">
                                <input type="hidden" name="form" value="LogInComando">
                                <input type="text" class="control form-control" name="nomUsu" placeholder="Nombre de usuario" required>
                                <input id="passA" type="password" class="control form-control" placeholder="Constraseña" pattern="(^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d$@$!%*?&]{8,15})" title="Al menos una minuscula, una mayuscula, un digito y longitud entre 8 y 15"  required>
                                <input type="hidden" id="pass1" name="contra">
                                <span>
                                    <label>
                                        <input type="checkbox" class="checkbox" value="true" name="recordarUsu"> 
                                        Mantenerme conectado
                                    </label>
                                </span>
                                <button type="submit" class="btn btn-default">Iniciar sesion</button>
                            </form>
                            <%if(request.getParameter("nomUsu")!= null && request.getParameter("contra")!=null){
                                if(session.getAttribute("usuario") == null){
                                    if(request.getAttribute("ex") != null){
                            %> 
                            <br/>
                            <div class="alert alert-danger">
                                <%=request.getAttribute("ex")%>
                            </div>
                            <%}else{%>
                            <br/>
                            <div class="alert alert-danger">
                                Usuario / Contraseña incorrecto  
                            </div>
                            <%}}}%>
                        </div><!--/login form-->
                    </div>
                    <div class="col-sm-1">
                    </div>
                    <div class="col-sm-4">
                        <div class="signup-form"><!--sign up form <div class="chose_area">-->
                            <h2>Sos nuevo? Registrate!</h2>
                            <form action="Controlador" method="post">
                                <input type="hidden" name="form" value="RedireccionarComando" />
                                <input type="hidden" name="destino" value="/signup.jsp" />
                                <input class="control form-control" type="text" name="nombreUsuario" placeholder="Nombre de Usuario" maxlenght="10"  required>
                                <input class="control form-control" type="email" name="email" placeholder="Email" maxlenght="30" required>
                                <input class="control form-control" type="password" name="contra1" placeholder="Contraseña" pattern="(^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d$@$!%*?&]{8,15})" title="Al menos una minuscula, una mayuscula, un digito y longitud entre 8 y 15" required>
                                <button type="submit" class="btn btn-default">Continuar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section><!--/form-->      
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>

