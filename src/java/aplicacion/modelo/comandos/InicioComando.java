/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.comandos;

import aplicacion.modelo.entidades.Genero;
import aplicacion.modelo.entidades.Parametro;
import aplicacion.modelo.entidades.Pedido;
import aplicacion.modelo.entidades.Pelicula;
import aplicacion.modelo.entidades.Usuario;
import aplicacion.modelo.negocio.CatalogoDeGeneros;
import aplicacion.modelo.negocio.CatalogoDeParametros;
import aplicacion.modelo.negocio.CatalogoDePeliculas;
import aplicacion.modelo.negocio.CatalogoDeUsuarios;
import aplicacion.utilidades.AefilepException;
import java.sql.Array;
import java.util.ArrayList;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ligia
 */
public class InicioComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response)
    {        
         //crea pedido inicial
        Pedido pedido= new Pedido(); 
        request.getSession().setAttribute("pedido", pedido);
        request.getSession().setAttribute("cantidadDias", 1);
                
        // carga de peliculas del home, carrusel y tablita de abajo
        CatalogoDePeliculas CdP= new CatalogoDePeliculas();
        ArrayList<ArrayList<Pelicula>> listaPeliculas=new ArrayList<ArrayList<Pelicula>>();
        ArrayList<Pelicula>pelisCarrusel= new ArrayList<Pelicula>();

        try
        {       
            listaPeliculas.add(CdP.obtenerGenero(6, 0, 4));
            listaPeliculas.add(CdP.obtenerGenero(3, 0, 4));
            listaPeliculas.add(CdP.obtenerGenero(1, 0, 4));
            listaPeliculas.add(CdP.obtenerGenero(2, 0, 4));
            listaPeliculas.add(CdP.obtenerGenero(5, 0, 4));
            pelisCarrusel= CdP.obtenerEstrenos(3);    
        }
        catch(AefilepException ex)
        {
            request.getSession().setAttribute("ex", ex.getMessage());
            return "/home.jsp";
        }
        request.getSession().setAttribute("listaPeliculas", listaPeliculas);
        request.getSession().setAttribute("pelisCarrusel", pelisCarrusel);
        
        //carga de géneros
        CatalogoDeGeneros cDeGen = new CatalogoDeGeneros();
        try
        {
            ArrayList<Genero> generos = cDeGen.obtenerGeneros();
            request.getSession().setAttribute("generos", generos);
        }
        catch(AefilepException ex)
        {
            request.setAttribute("ex", ex.getMessage());
            return "/home.jsp";
        }
               
        //mantenerme conectado
        String nomUsu = null;
        String contra = null;
        if( request.getCookies()!=null && request.getSession().getAttribute("usuario")==null )
        {
            Cookie[] cookies = request.getCookies();
            for(Cookie c:cookies)
            {
                if(c.getName().equals("nomUsuarioAefilep"))
                    nomUsu=c.getValue();
                if(c.getName().equals("contraAefilep"))
                    contra=c.getValue();
            }
            if(nomUsu!=null && contra!=null)
            {
                CatalogoDeUsuarios cDeUsus = new CatalogoDeUsuarios();
                Usuario usu;
                try
                {
                    usu = cDeUsus.buscarUsuario(nomUsu, contra);
                }
                catch(AefilepException ex)
                {
                    request.getSession().setAttribute("ex", ex.getMessage());
                    return "/home.jsp";
                }
                
                if(usu!=null)
                {
                    request.getSession().setAttribute("usuario", usu);
                    request.getSession().setAttribute("exitoLogin", true);
                }
            }
        }
        
        //carga de parámetros desde la BD
        CatalogoDeParametros cDePar = new CatalogoDeParametros();
        Parametro parametros = new Parametro();
        try
        {
            parametros = cDePar.obtenerParametros();
        } 
        catch (AefilepException ex) 
        {
            request.getSession().setAttribute("ex", ex.getMessage());
            return "/home.jsp";
        }
        request.getSession().setAttribute("parametros", parametros);
        
        return "/home.jsp";
    }
    
}
