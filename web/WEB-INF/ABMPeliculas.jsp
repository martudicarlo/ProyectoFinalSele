<%-- 
    Document   : ABMPeliculas
    Created on : 04/07/2016, 11:20:39
    Author     : JP
--%>
<%@page import="aplicacion.modelo.entidades.Parametro"%>
<%@page import="aplicacion.modelo.entidades.Genero"%>
<%@page import="aplicacion.modelo.entidades.Pelicula"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp"/>
    <body onload="scrollDiv()">
        <jsp:include page="header.jsp"/>
        <%!ArrayList<Pelicula> peliculas;%>
        <%!ArrayList<Genero> generos;%>
        <%!Parametro param;%>
        <%!Pelicula peli;%>
        <% if(session.getAttribute("parametros")!=null){ param = (Parametro) session.getAttribute("parametros"); }%>
        <% if(session.getAttribute("ListaPeliculas")!=null) { peliculas = (ArrayList)session.getAttribute("ListaPeliculas");}%>
        <% if(session.getAttribute("ListaGeneros")!=null) { generos = (ArrayList)session.getAttribute("ListaGeneros");}%>
        <% peli = (Pelicula)session.getAttribute("PeliEdit"); 
            if(request.getAttribute("peliculaPorAgregar")!=null)        
                peli = (Pelicula)request.getAttribute("peliculaPorAgregar");  
        %>
        <div class="cuenta">
            <div class="container"> 
                <%if(request.getAttribute("ex")!=null && peliculas==null ){ %>
                <div class="row">
                    <div class="alert alert-success fade in">
                        <%= request.getAttribute("ex")%>
                    </div>
                </div>
                <%}%>
                <% if(peliculas!=null) { %>
                <div class="row">
                    <h2 class="title text-center">Lista de Peliculas</h2> 
                    <div class="col-sm-12">
                        <div class="table-responsive" style="height:400px; overflow:auto">
                            <div class="table-striped">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Nombre</th>
                                            <th>Duración</th>
                                            <th>Stock Alqui.</th>
                                            <th>Stock Venta</th>
                                            <th>F. Alta</th>
                                            <th>Año</th>
                                            <th>Activa</th>
                                            <th>Estreno</th>
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
                                            <td>
                                                <form action="Controlador" method="post">
                                                    <input type="hidden"  name="form" value="SeleccionarPeliculaComando"/>
                                                    <input type="hidden" name="idPeliEdit" value="0">
                                                    <input type="submit" value="+ Nuevo">
                                                </form>
                                            </td>
                                        </tr>
                                        <%for(Pelicula pel:peliculas){%>
                                        <tr>
                                            <td><%= pel.getIdPelicula()%></td>
                                            <td><%= pel.getNombre()%></td>
                                            <td><%= pel.getDuracion()%></td>
                                            <td><%= pel.getStockAlquiler()%></td>
                                            <td><%= pel.getStockVenta()%></td>
                                            <td><%= pel.getFechaCarga()%></td>
                                            <td><%= pel.getAnio()%></td>
                                            <td><% if(pel.isActivo()){%><img src="./imagenes/check.png"><%}%></td>
                                            <td><% if(pel.isEstreno()){%><img src="./imagenes/check.png"><%}%></td>
                                            <td>
                                                <form action="Controlador" method="post">
                                                    <input type="hidden"  name="form" value="SeleccionarPeliculaComando"/>
                                                    <input type="hidden" name="idPeliEdit" value="<%= pel.getIdPelicula()%>">
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
                <div <%if(session.getAttribute("Scroll")!=null){%> id="Edit" <%session.setAttribute("Scroll", null); }%> class="row">
                    <br/>         
                    <h2 class="title text-center"><%if(peli!=null && request.getAttribute("peliculaPorAgregar")==null){%>EDITAR<%} else{%>AGREGAR<%}%> PELICULA</h2>
                    <br/>
                    <form name="DatosPelicula" class="peliculas" action="Controlador" method="post" enctype="multipart/form-data" onsubmit="return validarChecks()">  
                        <div class="col-sm-6 ">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h4 class="text-left">ID</h4>
                                </div>
                                <div class="col-sm-9">
                                    <input class="control form-control"  type="text" placeholder="ID (Automático)" maxlength="15" name="ID" readonly="" value="<%if(peli!=null && request.getAttribute("peliculaPorAgregar")==null )%><%=peli.getIdPelicula()%>">
                                </div>
                            </div>
                            <%if(peli!=null && request.getAttribute("peliculaPorAgregar")==null){%>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h4 class="text-left">F. Alta</h4>
                                </div>
                                <div class="col-sm-9">
                                    <input type="date" class="control form-control" name="fCargaPel" readonly="" value="<%if(peli!=null || request.getAttribute("peliculaPorAgregar")!=null)%><%= peli.getFechaCarga()%>">
                                </div>                           
                            </div>
                            <%}%>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h4 class="text-left">Título</h4>
                                </div>
                                <div class="col-sm-9">
                                    <input type="text" class="control form-control" name="nomPel" placeholder="*"  required value="<%if(peli!=null || request.getAttribute("peliculaPorAgregar")!=null)%><%=peli.getNombre()%>">
                                </div>
                            </div>
                            <div class="row">
                            <div class="col-sm-3">
                                    <h4 class="text-left">Año</h4>
                                </div>
                                <div class="col-sm-9">
                                    <input type="text" class="control form-control" name="anioPel" placeholder="* (Año de lanzamiento)" pattern="^[0-9]*$" title="Numero" required value="<%if(peli!=null || request.getAttribute("peliculaPorAgregar")!=null)%><%= peli.getAnio() %>">
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-sm-6">
                                    <h4 class="text-left">Duración</h4>
                                    <input class="control form-control"  type="text" placeholder="* (En min.)" name="durPel" pattern="^[0-9]*$" title="Numero" value="<%if(peli!=null || request.getAttribute("peliculaPorAgregar")!=null)%><%=peli.getDuracion()%>">
                                </div>
                                <div class="col-sm-6">
                                    <h4 class="text-left">Formato</h4>
                                    <input type="text" class="control form-control" name="formPel" placeholder="*"  required value="<%if(peli!=null || request.getAttribute("peliculaPorAgregar")!=null)%><%=peli.getFormato()%>">
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-sm-12">
                                    <h4 class="text-left">Sinopsis</h4>
                                    <textarea rows="5" class="form-control" maxlength="400" placeholder="*" name="sinPel"><%if(peli!=null || request.getAttribute("peliculaPorAgregar")!=null)%><%=peli.getSinopsis()%></textarea> 
                                </div>    
                            </div>                              
                            <div class="row">
                                <div class="col-sm-12">
                                    <h4 class="text-left">Reparto</h4>                       
                                    <textarea rows="4" class="form-control" maxlength="200" placeholder="*" name="repPel"><%if(peli!=null || request.getAttribute("peliculaPorAgregar")!=null)%><%= peli.getReparto()%></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6 ">
                            <div class="row">
                                <div class="col-sm-6">
                                    <h4 class="text-left">Stock. Alq.</h4>
                                    <input type="text" class="control form-control" name="stockAlqPel" placeholder="*" pattern="^[0-9]*$" title="Numero" required value="<%if(peli!=null || request.getAttribute("peliculaPorAgregar")!=null)%><%=peli.getStockAlquiler()%>">
                                </div>
                                <div class="col-sm-6">
                                    <h4 class="text-left">Stock. Vta.</h4>
                                    <input type="text" class="control form-control" name="stockVtaPel" placeholder="*" pattern="^[0-9]*$" title="Numero" required value="<%if(peli!=null || request.getAttribute("peliculaPorAgregar")!=null)%><%=peli.getStockVenta()%>">
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-sm-6">
                                    <h4 class="text-left">Precio. Alq.</h4>
                                    <input type="text" class="control form-control" name="palqPel" placeholder="Precio Alquiler" pattern="^[0-9]*$" title="Numero" readonly required value="<%if(peli!=null || request.getAttribute("peliculaPorAgregar")!=null){%><%= peli.getPrecioAlquiler()%><%}else if(peli==null){%><%= param.getPrecioAlquilerEstreno()%><%}%>">
                                </div>
                                <div class="col-sm-6">
                                    <h4 class="text-left">Precio. Vta.</h4>
                                    <input type="text" class="control form-control" name="pvtaPel" placeholder="* (En $)" pattern="^[0-9]+(\.[0-9]+)?$" title="Numero" required value="<%if(peli!=null || request.getAttribute("peliculaPorAgregar")!=null)%><%= peli.getPrecioVenta() %>">
                                </div>
                            </div>                        
                            <div class="row">
                                <div class="col-sm-3">
                                    <h4 class="text-left">Portada</h4>
                                </div>
                                <div class="col-sm-9">
                                    <input type="file" class="control form-control" name="imgPel">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h4 class="text-left">Link Trailer</h4>
                                </div>
                                <div class="col-sm-9">
                                    <input type="text" class="control form-control" name="urlPel" placeholder="*" required value="<%if(peli!=null || request.getAttribute("peliculaPorAgregar")!=null)%><%= peli.getUrlTrailer() %>">                               
                                </div>
                            </div>
                            <div class="row">
                                 <div class="col-sm-12">
                                     <label class="puntero"><input class="enLinea" type="checkbox" name="Activa" value="true" <% if((peli!=null || request.getAttribute("peliculaPorAgregar")!=null) && peli.isActivo())%>checked<%;%>><h4 class="enLinea">Activa</h4></label>
                                </div>                        
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                <h4 class="text-left">Géneros</h4>

                                    <div class="table-responsive" style="height:120px; overflow:auto;">
                                        <table class="table-striped col-lg-12">
                                            <tbody>
                                            <% for(int i=0;i<generos.size();i++){%>
                                                <tr>
                                                    <td>
                                                        <label class="puntero"><input class="check" type="checkbox" name="generos" value="<%=generos.get(i).getIdGenero()%>" <%if((peli!=null || request.getAttribute("peliculaPorAgregar")!=null) && peli.contieneGenero(generos.get(i)))%>checked<%;%>><%=generos.get(i).getDescripcion()%></label>
                                                    </td>
                                                </tr>
                                                <%}%>
                                           </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <br/>
                            <div class="row"> 
                                <div class="col-sm-12">
                                    <%if(request.getAttribute("ex")!=null){ %>                           
                                        <div class="alert alert-danger">
                                            <p class="text-center"><%= request.getAttribute("ex")%></p>
                                        </div>
                                    <%}%>
                                    <%if(request.getAttribute("ExitoPeli")!=null){
                                        if((Boolean)request.getAttribute("ExitoPeli")){%>
                                       <div class="alert alert-success">
                                           <p class="text-center">Pelicula <%if(peli==null && request.getAttribute("peliculaPorAgregar")==null){ %>agregada<% }else{%>editada<%}%> con éxito.</p>        
                                        </div>
                                    <% }else if(!(Boolean)request.getAttribute("ExitoPeli")){ %>
                                        <div class="alert alert-danger ">
                                            <p class="text-center">Ya existe una pelicula con el mismo nombre</p>        
                                        </div>           
                                    <% }}%>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-12">
                            <input type="hidden" name="form" value="<%if(peli!=null && request.getAttribute("peliculaPorAgregar")==null) {%>EditarPeliculaComando<%}else{%>AgregarPeliculaComando<%}%>">
                            <button type="submit" class="btn btn-default"><%if(peli!=null && request.getAttribute("peliculaPorAgregar")==null) {%>Guardar Cambios<%}else{%>Agregar Pelicula<%}%></button>
                            <h5>Los campos marcados con * son obligatorios</h5>
                        </div>
                    </form>
                </div>
                <% }%>
            </div>
        </div>
    </body>
    <jsp:include page="footer.jsp"/>
</html>
