<%@page import="aplicacion.modelo.negocio.CatalogoDePeliculas"%>
<%@page import="aplicacion.modelo.entidades.Pedido"%>
<%@page import="aplicacion.modelo.entidades.Usuario"%>
<%@page import="aplicacion.modelo.entidades.Parametro"%>
<%@page import="aplicacion.modelo.datos.ParametroBD"%>
<%@page import="aplicacion.modelo.entidades.Pelicula"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="head.jsp"/>
    <body>
        <jsp:include page="header.jsp"/>
        <% String [] listaNombres = {"comedia","drama","terror","accion","thriller"};
        if(session.getAttribute("ex")!=null){   %>
        <div class="container">
            <div class="row">
                <div class="col-sm-4">            
                    <div class="alert alert-danger fade in">
                        <%= session.getAttribute("ex")%>
                    </div>                    
                </div>
            </div>
        </div>
        <%}else{
         session.setAttribute("ex", null);
         ArrayList<Pelicula> pelisCarrusel = (ArrayList)session.getAttribute("pelisCarrusel");
         ArrayList<ArrayList<Pelicula>> listaPeliculas = (ArrayList)session.getAttribute("listaPeliculas"); %>
        <section id="slider"><!--slider-->
            <div class="container">                            
                <div class="row">
                    <div class="col-sm-12">
                        <div id="slider-carousel" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#slider-carousel" data-slide-to="0" class=""></li>
                                <li data-target="#slider-carousel" data-slide-to="1" class="active"></li>
                                <li data-target="#slider-carousel" data-slide-to="2" class=""></li>
                            </ol>	                      
                            <div class="carousel-inner">
                            <% int indice=0;
                            for(Pelicula pc: pelisCarrusel){ %>
                                <div class="item <%if(indice==1){%>active<%}%>">
                                    <div class="col-sm-6">
                                        <h1><span>A</span>efilep - Video Club</h1>
                                        <h2><%=pc.getNombre()%></h2>
                                        <p><%=pc.getSinopsis(150)%>...</p>
                                        <form action="Controlador" method="post">
                                            <button type="submit" class="btn btn-default get">Obtener ahora</button>
                                            <input type="hidden"  name="form" value="ObtenerPeliculaComando"/>
                                            <input type="hidden"  name="idPelicula" value="<%=pc.getIdPelicula()%>"/>
                                        </form>
                                    </div>
                                    <div class="col-sm-6">
                                        <img src="ProcesadorImagenes?id=<%=pc.getIdPelicula()%>" class="imagenCarrusel img-responsive" alt="">
                                        <img src="./imagenes/pricing.png" class="pricing" alt="">
                                    </div>
                                </div>
                            <%indice++;}%>                                    
                            </div>
                            <a href="/#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
                                <i class="fa fa-angle-left"></i>
                            </a>
                            <a href="/#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </section><!--/slider-->
        <section>
            <div class="container">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="left-sidebar">
                            <h2>Publicidad</h2>
                            <div class="shipping text-center"><!--shipping-->
                                <img src="./imagenes/popcorn-time.png" alt="publicidad" class="publicidad">
                            </div><!--/shipping-->
                        </div>
                    </div>
                    <div class="col-sm-9 padding-right">
                        <div class="category-tab"><!--category-tab-->
                            <div class="col-sm-12">
                                <ul class="nav nav-tabs">
                                    <li class="active"><a href="#comedia" data-toggle="tab">Comedia</a></li>
                                    <li><a href="#drama" data-toggle="tab">Drama</a></li>
                                    <li><a href="#terror" data-toggle="tab">Terror</a></li>
                                    <li><a href="#accion" data-toggle="tab">Acción</a></li>
                                    <li><a href="#thriller" data-toggle="tab">Thriller</a></li>
                                </ul>
                            </div>
                            <div class="tab-content">
                            <% for(int i=0; i<listaPeliculas.size();i++){%>
                                <div class="tab-pane fade <%if(i==0){%>active in<%}%>" id="<%=listaNombres[i]%>">                      
                                <%for(Pelicula p: (listaPeliculas.get(i))){%>
                                    <div class="col-sm-3">
                                        <div class="product-image-wrapper">
                                            <div class="single-products">
                                                <div class="productinfo text-center">
                                                    <img class="imgChica" src="ProcesadorImagenes?id=<%=p.getIdPelicula()%>" alt="">
                                                    <h2><%=p.getNombre()%></h2>
                                                    <form action="Controlador" method="post">
                                                        <button type="submit" class="btn btn-default get">Obtener ahora</button>
                                                        <input type="hidden"  name="form" value="ObtenerPeliculaComando"/>
                                                        <input type="hidden"  name="idPelicula" value="<%=p.getIdPelicula()%>"/>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <%}%>
                                </div>
                            <%}%>                             
                            </div>
                        </div><!--/category-tab-->	
                    </div>
                </div>
              <%}%>
            </div>
        </section>    
        <jsp:include page="footer.jsp"/>
    </body>
</html>
