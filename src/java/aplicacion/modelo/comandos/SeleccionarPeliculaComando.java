/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.comandos;

import aplicacion.modelo.entidades.Pelicula;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JP
 */
public class SeleccionarPeliculaComando extends Comando
{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response)
    {
        int idPeliEdit = Integer.parseInt( request.getParameter("idPeliEdit"));
        
        if(idPeliEdit!=0)
        {
            ArrayList<Pelicula> peliculas = (ArrayList<Pelicula>)request.getSession().getAttribute("ListaPeliculas");
            Pelicula peliEdit=null;
            for(Pelicula pel:peliculas)
            {
                if(idPeliEdit==pel.getIdPelicula())
                    peliEdit=pel;         
            }
            request.getSession().setAttribute("PeliEdit", peliEdit);
        }
        else
        {
            request.getSession().setAttribute("PeliEdit", null);
        }
        
        request.getSession().setAttribute("Scroll",true);
        
        return "/ABMPeliculas.jsp";
    }    
}
