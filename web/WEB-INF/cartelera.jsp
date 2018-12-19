<%@page import="aplicacion.modelo.entidades.Genero"%>
<%@page import="aplicacion.modelo.entidades.Parametro"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="head.jsp"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <%@page import="aplicacion.modelo.entidades.Pelicula"%>
        <%!ArrayList<Pelicula> listaCartelera = null;%>
        <%!ArrayList<Genero> listaGeneros = null;%>
        <%listaGeneros = (ArrayList)session.getAttribute("generos");%>
        <%int cantPaginas = 0;%>
        <%if(session.getAttribute("generoObtenido")!=null)
        {
            if(session.getAttribute("pelisEncontradas")!=null)
                listaCartelera = (ArrayList)session.getAttribute("pelisEncontradas");
        }
        else
        {
            if(session.getAttribute("listaCartelera")!=null)
                listaCartelera = (ArrayList)session.getAttribute("listaCartelera");
        }
        if(session.getAttribute("cantidadPeliculas")!=null)
        {
            if((Integer)session.getAttribute("cantidadPeliculas") % 9==0)
                cantPaginas= ((Integer)session.getAttribute("cantidadPeliculas")/9);
            else
                cantPaginas= ((Integer)session.getAttribute("cantidadPeliculas")/9)+1;
        }
        %>      
        <section>
            <div class="container">
            <% if(request.getAttribute("ex")!=null){%>
                <div class="row">
                    <div class="col-sm-4">
                        <div class="alert alert-danger fade in">
                            <%= request.getAttribute("ex")%>
                        </div>
                    </div>
                </div>                                        
            <%} else {%>
                <div class="row">
                    <div class="col-sm-3">
                        <div class="left-sidebar">
                            <h2>Géneros</h2>
                            <div class="brands-name">
                                <form action="Controlador" method="post" >
                                    <input type="hidden" name="form" value="PeliculasComando" >
                                    <ul class="nav nav-pills nav-stacked">
                                        <li><label class="etiquetaGenero"><input onclick="submit()" type="radio" value="estreno" name="tipo">Estrenos</label></li>
                                        <% for(Genero g : listaGeneros){%>
                                        <li><label class="etiquetaGenero"><input onclick="submit()" type="radio" value="<%=g.getIdGenero()%>" name="tipo"><%=g.getDescripcion()%></label></li>
                                        <%}%>                                                                                        
                                    </ul>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-9 padding-right">
                        <h2 class="title text-center">Películas</h2>
                        <div class="features_items">
                            <% if(session.getAttribute("errorNoEncontradas")!=null){%>
                            <div class="alert alert-danger">
                                <h2 class='text-center'>No hay películas que coincidan con su búsqueda!!</h2>
                            </div>
                            <% session.setAttribute("errorNoEncontradas", null);}         
                             for(Pelicula p: listaCartelera){ %>
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <img class="imagen" src="ProcesadorImagenes?id=<%=p.getIdPelicula()%>" alt="">
                                            <form action="Controlador" method="post">
                                                <h2>
                                                    <button class="tituloAbajo"><%=p.getNombre()%></button>
                                                </h2>
                                                <input type="hidden"  name="form" value="ObtenerPeliculaComando"/>
                                                <input type="hidden"  name="idPelicula" value="<%=p.getIdPelicula()%>"/>
                                            </form>
                                            <p><%=p.getAnio()%></p>
                                            <p>$ <%=String.format("%.2f",p.getPrecioAlquiler())%> - $ <%=String.format("%.2f",p.getPrecioVenta())%></p>
                                            <form action="Controlador" method="post">
                                                <input type="hidden"  name="form" value="AgregarLineaComando"/>
                                                <input type="hidden" name="idPelicula" value="<%=p.getIdPelicula()%>"/>
                                                <input class="btn btn-default add-to-cart linea" type="submit" name="tipoLinea" value="Alquilar">
                                                <input class="btn btn-default add-to-cart linea" type="submit" name="tipoLinea" value="Comprar">
                                            </form>
                                        </div>
                                        <div class="product-overlay text-center">
                                            <div class="overlay-content">
                                                <form action="Controlador" method="post">
                                                    <h2>
                                                        <button><%=p.getNombre()%></button>
                                                    </h2>
                                                    <input type="hidden"  name="form" value="ObtenerPeliculaComando"/>
                                                    <input type="hidden"  name="idPelicula" value="<%=p.getIdPelicula()%>"/>
                                                </form>
                                                <p><%=p.getAnio()%></p>
                                                <p>$ <%=String.format("%.2f",p.getPrecioAlquiler())%> - $ <%=String.format("%.2f",p.getPrecioVenta())%></p>
                                                <form action="Controlador" method="post">
                                                    <input type="hidden"  name="form" value="AgregarLineaComando"/>
                                                    <input type="hidden" name="idPelicula" value="<%=p.getIdPelicula()%>"/>
                                                    <input class="btn btn-default add-to-cart linea" type="submit" name="tipoLinea" value="Alquilar">
                                                    <input class="btn btn-default add-to-cart linea" type="submit" name="tipoLinea" value="Comprar">
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                               </div>
                            </div>
                            <%}%>        
                        </div>
                        <form action="Controlador" method="post">  
                            <ul class="pagination">
                                <%for(int j=1;j<=cantPaginas;j++){%>                                                                                                
                                <li>
                                    <input type="submit" <%if((Integer)session.getAttribute("pActual")==j){%>class="active" disabled<%}%> name="paginacionActual" value="<%=j%>">
                                </li>
                                <%}%>  
                                <input type="hidden" name="form" value="PeliculasComando">                                                
                            </ul>
                        </form> 
                    </div>
                </div>
                <%}%>
            </div>
        </section>
        <jsp:include page="footer.jsp"/>
    </body>
</html>