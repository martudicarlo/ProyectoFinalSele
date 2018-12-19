/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.comandos;
import aplicacion.modelo.entidades.Pelicula;
import aplicacion.modelo.entidades.Genero;
import aplicacion.modelo.negocio.CatalogoDePeliculas;
import aplicacion.modelo.negocio.CatalogoDeGeneros;
import aplicacion.utilidades.AefilepException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
/**
 *
 * @author User
 */
public class AgregarPeliculaComando extends Comando
{
    Pelicula pelicula;
    CatalogoDePeliculas cDp= new CatalogoDePeliculas();
    CatalogoDeGeneros cdG = new CatalogoDeGeneros();
    
    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response)
    {
        boolean existePelicula = true;
        
        //valida que la pelicula sea única
        try
        {
            existePelicula = cDp.existePelicula((String)request.getParameter("nomPel"));
        }
        catch(AefilepException ex)
        {
            request.setAttribute("ex", ex.getMessage());
            return"/ABMPeliculas.jsp";
        }
        
        pelicula = new Pelicula();
        pelicula.setActivo(true);
        pelicula.setFormato(request.getParameter("formPel"));
        pelicula.setNombre(request.getParameter("nomPel"));
        pelicula.setDuracion(Integer.parseInt(request.getParameter("durPel")));
        pelicula.setPrecioVenta(Float.parseFloat(request.getParameter("pvtaPel")));
        pelicula.setReparto(request.getParameter("repPel"));
        pelicula.setSinopsis(request.getParameter("sinPel"));
        pelicula.setStockAlquiler(Integer.parseInt(request.getParameter("stockAlqPel")));
        pelicula.setStockVenta(Integer.parseInt(request.getParameter("stockVtaPel")));
        pelicula.setUrlTrailer(request.getParameter("urlPel"));
        pelicula.setAnio(Integer.parseInt(request.getParameter("anioPel")));
        DateFormat hoyFormato = new SimpleDateFormat("yyyy/MM/dd");      
        Date hoy=new Date();
        hoyFormato.format(hoy);
        pelicula.setFechaCarga(hoy);
        //Comparo todos los generos con los seleccionados y los agrego a la película
        ArrayList<Genero> generos = (ArrayList)request.getSession().getAttribute("ListaGeneros");
        String selecc[] = request.getParameterValues("generos");
        for(Genero g: generos)
        {
            for(int i=0; i<selecc.length;i++)  
            {
                if(g.getIdGenero()==Integer.parseInt(selecc[i]))
                {
                    pelicula.agregarGenero(g);
                }
            }
        }    
        //agregamos imagen a la película 
        Part imagen = null;
        try
        {
            if(request.getPart("imgPel")!=null)
            {
                imagen = request.getPart("imgPel");
                InputStream inputStream = imagen.getInputStream();
                if(inputStream!=null)
                    pelicula.setImagen(inputStream);
            }
        }
        catch (Exception ex)
        {
            request.setAttribute("ex","Error al cargar imagen");
            return ("/ABMPeliculas.jsp");
        }
        
        if(!existePelicula)
        {
            //Agrego la película y actualizo la lista
            ArrayList<Pelicula> peliculas = new ArrayList<>();
            try
            {
                cDp.agregarPelicula(pelicula);
                peliculas = cDp.obtenerPeliculas();            
            }
            catch(AefilepException ex)
            {
                request.setAttribute("ex", ex.getMessage());
                request.getSession().setAttribute("Scroll",true);
                return "/ABMPeliculas.jsp";
            }         
            request.getSession().setAttribute("ListaPeliculas", peliculas);
            request.setAttribute("ExitoPeli", true);
            request.setAttribute("peliculaPorAgregar", null);
        }
        else
        {
            request.setAttribute("peliculaPorAgregar", pelicula);
            request.setAttribute("ExitoPeli", false); 
        }
        
        request.getSession().setAttribute("Scroll",true);
        return "/ABMPeliculas.jsp";
    }    
}
