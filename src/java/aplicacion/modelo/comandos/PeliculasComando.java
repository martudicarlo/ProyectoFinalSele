/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.comandos;

import aplicacion.modelo.entidades.Pelicula;
import aplicacion.modelo.negocio.CatalogoDePeliculas;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marti_000
 */
public class PeliculasComando extends Comando
{
    private CatalogoDePeliculas cDp;
    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response)
    {
        int paginaActual = 1;
        
        if(request.getParameter("tipo")!=null)
        {  
            switch(request.getParameter("tipo"))
            {
                case "estreno": request.getSession().setAttribute("tipo","estreno");
                    break;
                case "buscador":request.getSession().setAttribute("tipo","buscador");
                    break;
                case "todas":request.getSession().setAttribute("tipo","todas");
                    break;
                default:request.getSession().setAttribute("tipo",Integer.parseInt(request.getParameter("tipo")));
                    break;
            }
        }
        
        if(request.getParameter("paginacionActual")==null)
        {
            paginaActual = 1; 
            if(request.getParameter("tipo").equals("buscador"))
                request.getSession().setAttribute("nombrePelicula", request.getParameter("nombrePelicula"));
        }
        else
        {
            paginaActual = Integer.parseInt(request.getParameter("paginacionActual"));
        }
        request.getSession().setAttribute("pActual", paginaActual);
        
        cDp = new CatalogoDePeliculas();
        int cantidadDePeliculas = 0;
        ArrayList<Pelicula> listaPeliculas = null;
        
        try
        {
            if(request.getSession().getAttribute("tipo")!=null)
            {
                if(request.getSession().getAttribute("tipo").equals("estreno"))             
                {
                    listaPeliculas = cDp.obtenerEstrenos((paginaActual-1)*9,9);
                    cantidadDePeliculas=cDp.cantidadEstrenosActivos();
                }
                else if(request.getSession().getAttribute("tipo").equals("buscador"))
                {                     
                    listaPeliculas = cDp.obtenerPeliculas(request.getSession().getAttribute("nombrePelicula").toString(),(paginaActual-1)*9,9);
                    cantidadDePeliculas=cDp.cantidadBuscadorActivos(request.getSession().getAttribute("nombrePelicula").toString());
                    
                    if(listaPeliculas.isEmpty())
                        request.getSession().setAttribute("errorNoEncontradas",true);
                    request.getSession().setAttribute("generoObtenido",true);
                }
                else if(request.getSession().getAttribute("tipo").equals("todas"))
                { 
                    listaPeliculas = cDp.buscarPeliculas((paginaActual-1)*9,9);
                    cantidadDePeliculas = cDp.cantidadPeliculasActivas();
                }
                else
                {
                    listaPeliculas = cDp.obtenerGenero((Integer)request.getSession().getAttribute("tipo"),(paginaActual-1)*9,9);
                    request.getSession().setAttribute("generoObtenido",true);
                    cantidadDePeliculas=cDp.cantidadGenerosActivos((Integer)request.getSession().getAttribute("tipo"));
                }
            }
        }
        catch(Exception ex)
        {
            request.setAttribute("ex",ex.getMessage());
            return "/cartelera.jsp";
        }
     
        request.getSession().setAttribute("listaCartelera", listaPeliculas);
        request.getSession().setAttribute("cantidadPeliculas",cantidadDePeliculas);
        request.getSession().setAttribute("generoObtenido", null);
        
        return "/cartelera.jsp";       
    }
}
