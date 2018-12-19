/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.comandos;

import aplicacion.modelo.entidades.Genero;
import aplicacion.modelo.entidades.Pelicula;
import aplicacion.modelo.negocio.CatalogoDeGeneros;
import aplicacion.modelo.negocio.CatalogoDePeliculas;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author JP
 */
public class EditarPeliculaComando extends Comando
{
    CatalogoDePeliculas CdeP;
    CatalogoDeGeneros CdeG;
    
    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response)
    {
        CdeP = new CatalogoDePeliculas();
        CdeG = new CatalogoDeGeneros();
        Pelicula PeliEditada = new Pelicula();
        ArrayList<Genero> generos = null;
        try
        {
            generos = CdeG.obtenerGeneros();
        } 
        catch (Exception ex) 
        {
            request.setAttribute("ex",ex.getMessage());
            return ("/ABMPeliculas.jsp");
        }
        
        PeliEditada.setIdPelicula(Integer.parseInt(request.getParameter("ID")));
        PeliEditada.setFormato(request.getParameter("formPel"));
        PeliEditada.setNombre(request.getParameter("nomPel"));
        PeliEditada.setDuracion(Integer.parseInt(request.getParameter("durPel")));
        PeliEditada.setPrecioVenta(Float.parseFloat(request.getParameter("pvtaPel")));
        PeliEditada.setReparto(request.getParameter("repPel"));
        PeliEditada.setSinopsis(request.getParameter("sinPel"));
        PeliEditada.setStockAlquiler(Integer.parseInt(request.getParameter("stockAlqPel")));
        PeliEditada.setStockVenta(Integer.parseInt(request.getParameter("stockVtaPel")));
        PeliEditada.setUrlTrailer(request.getParameter("urlPel"));
        PeliEditada.setAnio(Integer.parseInt(request.getParameter("anioPel")));
        
        Part imagen = null;
        try 
        {           
            if(request.getPart("imgPel").getSubmittedFileName().equals(""))
            {
                PeliEditada.setImagen(null);
            } 
            else
            {
                imagen = request.getPart("imgPel");
                InputStream inputStream = imagen.getInputStream();
                
                if(inputStream!=null)
                    PeliEditada.setImagen(inputStream);
            }            
        } 
        catch (IOException | ServletException ex)
        {       
            request.setAttribute("ex",ex.getMessage());
            return ("/ABMPeliculas.jsp");
        }
        
        SimpleDateFormat formato =  new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try
        {
            fecha = formato.parse(request.getParameter("fCargaPel"));              
            PeliEditada.setFechaCarga(new java.sql.Date(fecha.getTime()));
        }
        catch(Exception ex)
        {
            request.setAttribute("ex","Ha ocurrido un error");
            return ("/ABMPeliculas.jsp");
        }
        
        String selecc[] = request.getParameterValues("generos");
        for(Genero g: generos)
        {
            for(int i=0; i<selecc.length;i++)                  
            {
                if(g.getIdGenero()==Integer.parseInt(selecc[i]))               
                    PeliEditada.agregarGenero(g);
            }
        }
        
        Boolean esActivo = (request.getParameter("Activa")!=null);
        PeliEditada.setActivo(esActivo);
        
        ArrayList<Pelicula> peliculas;
        try
        {
            CdeP.actualizarPelicula(PeliEditada);
            peliculas = CdeP.obtenerPeliculas();          
        }
        catch(Exception ex)
        {
            request.setAttribute("ex",ex.getMessage());
            return "/ABMPeliculas.jsp";
        }
        
        request.getSession().setAttribute("ListaPeliculas", peliculas);
        request.getSession().setAttribute("PeliEdit", PeliEditada);
        request.getSession().setAttribute("Scroll",true);
        request.setAttribute("ExitoPeli", true);
         
        return "/ABMPeliculas.jsp";
    }    
}
