/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.comandos;

import aplicacion.modelo.entidades.Pelicula;
import aplicacion.modelo.negocio.CatalogoDePeliculas;
import aplicacion.utilidades.AefilepException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
public class ObtenerPeliculaComando extends Comando
{
    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response)
    {
        CatalogoDePeliculas CdP= new CatalogoDePeliculas();
        try
        {
            Pelicula peliActual= CdP.obtenerPelicula(Integer.parseInt(request.getParameter("idPelicula")));
            request.getSession().setAttribute("peliActual", peliActual);  
        }
        catch(AefilepException ex)
        {
            request.getSession().setAttribute("peliActual", null);
            request.setAttribute("ex", ex.getMessage());
        }
        
        return "/pelicula.jsp";            
    }    
}
