<%@page import="aplicacion.modelo.negocio.CatalogoDeGeneros"%>
<%@page import="aplicacion.modelo.entidades.Genero"%>
<%@page import="java.util.ArrayList"%>
<%@page import="aplicacion.modelo.entidades.Pelicula"%>
<html>
    <head>
        <jsp:include page="head.jsp"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <%Pelicula peliActual=(Pelicula)session.getAttribute("peliActual"); 
          ArrayList<Genero> listaGeneros = (ArrayList)session.getAttribute("generos");%>
        <section>
            <div class="container">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="left-sidebar">
                            <h2>Géneros</h2>
                            <div class="category-products">
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
                        <div class="product-details"><!--product-details-->
                            <div class="col-sm-5">                                   
                                <div class="productinfo">
                                    <img class="imagenPelicula" src="ProcesadorImagenes?id=<%=peliActual.getIdPelicula()%>" alt="<%=peliActual.getNombre()%>">
                                </div>
                            </div>
                            <div class="col-sm-7">
                                <div class="product-information"><!--/product-information-->
                                    <h2><%=peliActual.getNombre()%></h2>

                                    <p><b>Año: </b><%=peliActual.getAnio()%></p>
                                    <p><b>Duración:</b> <%=peliActual.getDuracion()%></p>                                                                                  
                                    <p><b>Disponible para alquiler:</b><%if(peliActual.getStockAlquiler()>0){ %> Si <%}else{%> No <%}%></p>
                                    <p><b>Disponible para compra:</b><%if(peliActual.getStockVenta()>0){ %> Si <%}else{%> No <%}%></p>
                                    <p><b>Estreno: </b> <%if(peliActual.isEstreno()){%> Si <%}else{%> No <%}%></p>
                                    <p><b>Formato: </b><%=peliActual.getFormato()%></p>
                                    <p><b>Reparto: </b><%=peliActual.getReparto()%></p>
                                    <p><b>Sinopsis: </b><%=peliActual.getSinopsis()%></p>
                                    <form action="Controlador" method="post">
                                        <input type="hidden"  name="form" value="AgregarLineaComando"/>
                                        <input type="hidden" name="idPelicula" value="<%=peliActual.getIdPelicula()%>"/>
                                        <input type="hidden" name="provieneDePelicula" value="true">
                                        <span>
                                            <span class="precios">Alquiler $<%=String.format("%.2f",peliActual.getPrecioAlquiler())%></span>
                                            <button type="submit" class="btn btn-fefault cart botonPelicula"  name="tipoLinea" value="Alquilar">
                                                <i class="fa fa-shopping-cart"></i> Alquilar
                                            </button>
                                        </span>                                      
                                        <span>
                                            <span class="precios">Compra $<%=String.format("%.2f",peliActual.getPrecioVenta())%></span>
                                            <button type="submit" class="btn btn-fefault cart botonPelicula"  name="tipoLinea" value="Comprar">
                                                <i class="fa fa-shopping-cart"></i> Comprar
                                            </button>
                                        </span>                                           
                                    </form>
                                </div><!--/product-information-->
                            </div>
                            <h4>Ver Trailer</h4>
                            <div class="row">
                                <div class="embed-container">                             
                                    <iframe width="560" height="315" src="<%=peliActual.getUrlTrailer()%>" frameborder="0" allowfullscreen></iframe>
                                </div> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="footer.jsp"/>
    </body>
</html>