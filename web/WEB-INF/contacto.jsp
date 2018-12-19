<html>
    <head>
        <jsp:include page="head.jsp"/>
    </head>
    <body>       
        <jsp:include page="header.jsp"/>
        <%@page import="aplicacion.modelo.entidades.Parametro"%>        
        <section class ="seccion">
            <h2 class="title text-center">Contacto </h2>  
            <div id="contact-page" class="container">
                <div class="bg">
                    <div class="row">  	
                        <div class="col-sm-8">
                            <div class="contact-form">
                                <h2 class="title text-center">Mensaje</h2>
                                <div class="status alert alert-success" style="display: none"></div>
                                <form id="main-contact-form" class="contact-form row" name="Controlador" method="post">
                                    <div class="form-group col-md-6">
                                        <input type="text" name="name" class="form-control" required="required" pattern="^[a-zA-Z ]*$" title="Letras" placeholder="Nombre">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <input type="email" name="email" class="form-control" required="required" placeholder="Email">
                                    </div>
                                    <div class="form-group col-md-12">
                                        <input type="text" name="subject" class="form-control" pattern="^[a-zA-Z ]*$" title="Letras" required="required" placeholder="Asunto">
                                    </div>
                                    <div class="form-group col-md-12">
                                        <textarea name="message" id="message" required="required" class="form-control" rows="8" placeholder="Escribir mensaje"></textarea>
                                    </div>                        
                                    <div class="form-group col-md-12">
                                        <div class="row">
                                            <div class="alert alert-success popover fade left <%if(session.getAttribute("ExitoMensajeEnviado")!=null){ %> in <% session.setAttribute("ExitoMensajeEnviado", null);}  %>">
                                                Mensaje enviado con éxito. Gracias por contactarse.
                                            </div> 
                                            <input type="hidden" name="form" value="EnviarMensajeComando" >
                                            <input type="submit" name="submit" class="btn btn-primary pull-right" value="Enviar">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="contact-info">
                                <h2 class="title text-center">Información Nuestra</h2>
                                <address>
                                    <%if(session.getAttribute("parametros")!=null){
                                        Parametro par =(Parametro)session.getAttribute("parametros");
                                    %>
                                    <p><%= par.getRazonSocial()%> </p>
                                    <p> <%= par.getDireccion() %></p>
                                    <p>Argentina- Rosario</p>
                                    <p>Telefono: <%= par.getTelefono()%> </p>
                                    <p>Email: <%= par.getMail()%> </p>
                                    <%}else if(request.getAttribute("ex")!=null){%>
                                    <div class="alert alert-danger">
                                        <%=request.getAttribute("ex")%>
                                    </div>
                                    <%}%>
                                </address>
                            </div>
                        </div>    			
                    </div>  
                </div>	
            </div>
        </section>
        <jsp:include page="footer.jsp"/>                                        
    </body>
 </html>