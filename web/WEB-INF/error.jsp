<!DOCTYPE html>
<html>
      <head>
        <jsp:include page="head.jsp"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container text-center">
            <div class="content-404">
                <img src="imagenes/404.png" class="img-responsive" alt="">
                <h1><b>OPPS!</b> Lo sentimos, no puede encontrarse la página</h1>
                <p>Ha ocurrido un error al buscar la página solicitada.</p>            
                <form action="Controlador" method="post"  class="formLogo" onclick="submit()">
                    <h2 id="f"><a>Volver al inicio</a></h2>
                    <input type="hidden"  name="form" value="RedireccionarComando"/>
                    <input type="hidden"  name="destino" value="/home.jsp"/>                  
                </form>
            </div>
            <br>      
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>