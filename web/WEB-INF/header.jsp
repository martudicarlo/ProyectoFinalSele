<%@page import="java.util.ArrayList"%>
<%@page import="aplicacion.modelo.negocio.CatalogoDeUsuarios"%>
<%@page import="aplicacion.modelo.entidades.Pedido"%>
<%@page import="aplicacion.modelo.entidades.Usuario"%>
<%!int cantidadAEnviar = 0;%>
<%    
    if(request.getSession().getAttribute("pendientes")!=null)
    {
        cantidadAEnviar = ((ArrayList)request.getSession().getAttribute("pendientes")).size();
    }
    Pedido pedido = (Pedido)session.getAttribute("pedido");
%>
 <header id="header">
    <div class="header_top"><!--header_top-->
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <div class="social-icons pull-right">
                        <ul class="nav navbar-nav">
                            <li><a href="https://www.facebook.com/Aefilep-1323307154365122/?fref=ts"><i class="fa fa-facebook"></i></a></li>
                            <li><a href="#"><i class="fa fa-twitter"></i></a></li>                                            
                            <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                            <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                            <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/header_top-->
            
    <div class="header-middle"><!--header-middle-->
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <div class="logo pull-left">
                        <form action="Controlador" method="post" class="formLogo">
                            <input type="hidden"  name="form" value="RedireccionarComando"/>
                            <input type="hidden"  name="destino" value="/home.jsp"/>
                            <img src="./imagenes/logo.jpg" onclick="submit()">
                        </form>
                   </div>               
                </div>
                <div class="col-sm-9">
                    <div class="shop-menu pull-right">
                        <ul class="nav navbar-nav" id="ajusteBarra">
                            <li>
                                <div class='cartel'>
                                <% if(session.getAttribute("exitoPeliculaAgregada")!=null){ %>
                                    <div class="popover fade left <%if(session.getAttribute("exitoPeliculaAgregada")!=null){ %> in <%session.setAttribute("exitoPeliculaAgregada", null); } %>">
                                        <div class="arrow"></div>
                                        <div class="popover-content">
                                            Se ha agregado una película al carro.
                                        </div>    
                                    </div> 
                                <%}else if(session.getAttribute("exitoLogin")!=null){%>                              
                                    <div class="popover fade left <%if(session.getAttribute("exitoLogin")!=null){ %> in <%session.setAttribute("exitoLogin", null); } %>">
                                        <div class="popover-content">
                                            Usuario logueado, bienvenid@
                                            <%if(session.getAttribute("usuario")!=null)
                                            {
                                                Usuario usu= (Usuario)session.getAttribute("usuario");
                                            %>
                                            <%=usu.getNombre()%>
                                            <%}%>
                                        </div>                                                     
                                    </div>
                                <%}%>   
                                </div>                                    
                            </li>                         
                            <li>
                                <form action="Controlador" method="post" class="formNav">
                                    <input type="hidden"  name="form" value="RedireccionarComando"/>
                                    <input type="hidden"  name="destino" value="/carro.jsp"/>
                                    <i class="fa fa-shopping-cart <%if(request.getRequestURI().contains("/carro.jsp")){%>active<%}%>"></i><input class="<%if(request.getRequestURI().contains("/carro.jsp")){%>active<%}%>" type="submit" name="pagina" value=" Carrito (<%=pedido.getLineas().size()%>)" >
                                </form>
                            </li>
                            <%if(session.getAttribute("usuario")==null){%>
                            <li>
                                <form action="Controlador" method="post" class="formNav">
                                    <input type="hidden"  name="form" value="RedireccionarComando"/>
                                    <input type="hidden"  name="destino" value="/login.jsp"/>
                                    <i class="fa fa-lock <%if(request.getRequestURI().contains("/login.jsp")){%>active<%}%>"></i><input class="<%if(request.getRequestURI().contains("/login.jsp")){%>active<%}%>" type="submit" name="pagina" value=" Ingresar" >
                                </form>                     
                            <%}else{
                                Usuario usu = (Usuario)session.getAttribute("usuario");
                                if(!usu.isEsAdmin()){%>
                            <li>
                                <form action="Controlador" method="post" class="formNav">
                                    <input type="hidden"  name="form" value="MisPedidosComando"/>
                                    <i class="fa fa-archive <%if(request.getRequestURI().contains("/pedidos.jsp")){%>active<%}%>"></i><input class="<%if(request.getRequestURI().contains("/pedidos.jsp")){%>active<%}%>" type="submit" name="pagina" value=" Mis Pedidos" >
                                </form>
                            </li>
                            <%} else{%>
                            <li>
                                <form action="Controlador" method="post" class="formNav">
                                    <input type="hidden"  name="form" value="EnviosComando"/>                                       
                                    <i class="fa fa-truck <%if(request.getRequestURI().contains("/envios.jsp")){%>active<%}%>"></i><input class="<%if(request.getRequestURI().contains("/envios.jsp")){%>active<%}%>" type="submit" name="pagina" value=" Envíos pendientes (<%=cantidadAEnviar%>)" >
                                </form>
                            </li>
                            <%}%>
                            <li>
                                <form action="Controlador" method="post" class="formNav">
                                    <input type="hidden"  name="form" value="RedireccionarComando"/>
                                    <input type="hidden"  name="destino" value="/cuenta.jsp"/>
                                    <i class="fa fa-user <%if(request.getRequestURI().contains("/cuenta.jsp")){%>active<%}%>"></i><input class="<%if(request.getRequestURI().contains("/cuenta.jsp")){%>active<%}%>" type="submit" name="pagina" value=" Cuenta" >
                                </form> 
                            <li>
                                <form action="Controlador" method="post" class="formNav">
                                    <input type="hidden"  name="form" value="LogOutComando"/>
                                    <i class="fa fa-lock"></i><input type="submit" name="pagina" value=" Salir" >
                                </form>
                            </li>
                            <%}%>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/header-middle-->            
    <div class="header-bottom"><!--header-bottom-->
        <div class="container">
            <div class="row">
                <div class="col-sm-9">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>                  
                    <div class="mainmenu pull-left"> 
                        <ul class="nav navbar-nav collapse navbar-collapse">                                                                           
                            <li>
                                <a>
                                    <form action="Controlador" method="post">                                
                                        <input class="<%if(request.getRequestURI().contains("/home.jsp")){%>active<%}%>" type="submit" name="pagina" value="Inicio" >
                                        <input type="hidden"  name="destino" value="/home.jsp"/>
                                        <input type="hidden"  name="form" value="RedireccionarComando"/>
                                    </form>
                                </a>                                
                            <li>
                                <a>
                                    <form action="Controlador" method="post">                                
                                        <input class="<%if(request.getRequestURI().contains("/cartelera.jsp")){%>active<%}%>" type="submit" name="pagina" value="Peliculas" >
                                        <input type="hidden"  name="tipo" value="todas"/>
                                        <input type="hidden"  name="form" value="PeliculasComando"/>
                                    </form>
                                </a>
                            </li>
                            <li>
                                <a>
                                    <form action="Controlador" method="post">
                                        <input type="hidden"  name="form" value="RedireccionarComando"/>
                                        <input type="hidden"  name="destino" value="/nosotros.jsp"/>
                                        <input class="<%if(request.getRequestURI().contains("/nosotros.jsp")){%>active<%}%>" type="submit" name="pagina" value="Nosotros" >
                                    </form>
                                </a> 
                            </li>
                            <li>
                                <a>
                                    <form action="Controlador" method="post">
                                        <input type="hidden"  name="form" value="RedireccionarComando"/>
                                        <input class="<%if(request.getRequestURI().contains("/contacto.jsp")){%>active<%}%>" type="submit" name="pagina" value="Contacto" >
                                        <input type="hidden"  name="destino" value="/contacto.jsp"/>

                                    </form>
                                </a>
                            </li>                                   
                            <%Usuario usu = (Usuario)session.getAttribute("usuario");
                            if(usu!=null && usu.isEsAdmin())
                            {%>
                            <li class="dropdown"><a href="#" class="<%if(request.getRequestURI().contains("/ABMUsuarios.jsp") ||request.getRequestURI().contains("/ABMPeliculas.jsp") || request.getRequestURI().contains("/Devoluciones.jsp") ){%>active<%}%>" >Administrador<i class="fa fa-angle-down"></i></a>
                                <ul role="menu" class="sub-menu">
                                    <li>
                                        <a>
                                            <form action="Controlador" method="post">
                                                <input type="hidden"  name="desdeIndex" value="desdeIndex"/>
                                                <input type="hidden"  name="form" value="AdminPeliculasComando"/>
                                                <input class="<%if(request.getRequestURI().contains("/ABMPeliculas.jsp") ){%>active<%}%>" type="submit" name="pagina" value="Peliculas">
                                            </form>
                                        </a>
                                    </li>
                                    <li>
                                        <a>
                                            <form action="Controlador" method="post">
                                                <input type="hidden"  name="form" value="AdminUsuariosComando"/>
                                                <input type="submit" name="pagina" value="Usuarios" class="<%if(request.getRequestURI().contains("/ABMUsuarios.jsp") ){%>active<%}%>">
                                            </form>
                                        </a>
                                    </li>                                    
                                    <li>
                                        <a>
                                            <form action="Controlador" method="post">                                
                                                <input class="<%if(request.getRequestURI().contains("/Devoluciones.jsp")){%>active<%}%>" type="submit" name="pagina" value="Devoluciones" >
                                                <input type="hidden"  name="destino" value="/Devoluciones.jsp"/>
                                                <input type="hidden"  name="form" value="RedireccionarComando"/>
                                            </form>
                                        </a>
                                    </li>
                                </ul>
                            </li> 
                            <%}%>                                                         
                        </ul>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="search_box pull-right">
                        <form action="Controlador" method="post">
                            <input type="text" name="nombrePelicula" placeholder="Nombre pelicula">
                            <input type="hidden" name="tipo" value="buscador">
                            <input type="hidden" name="form" value="PeliculasComando" onchange="submit()">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/header-bottom-->     
</header>
