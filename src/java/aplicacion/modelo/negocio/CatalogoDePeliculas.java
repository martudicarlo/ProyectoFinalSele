/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.negocio;

import aplicacion.modelo.datos.PeliculaDB;
import aplicacion.modelo.entidades.Pelicula;
import aplicacion.utilidades.AefilepException;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class CatalogoDePeliculas 
{  
    PeliculaDB peliculas = new PeliculaDB();
    
    public void agregarPelicula(Pelicula pelicula) throws AefilepException
    {
        peliculas.agregarPelicula(pelicula);
    }
    
    public byte[] buscarImagen(int id) throws AefilepException
    {
        return peliculas.buscarImagen(id);
    }
    
    public void actualizarPelicula(Pelicula p) throws AefilepException
    {
        peliculas.actualizarPelicula(p);
    }
    
    public void actualizarStock(Pelicula p) throws AefilepException
    {
        peliculas.actualizarStock(p);
    }
    
    public Pelicula obtenerPelicula(int idPel) throws AefilepException
    {
        return peliculas.obtenerPelicula(idPel);
    }
    
    public boolean existePelicula(String nombrePelicula) throws AefilepException
    {
        return peliculas.existePelicula(nombrePelicula);
    }
    
    public ArrayList<Pelicula> obtenerPeliculas() throws AefilepException
    {
        return peliculas.obtenerPeliculas();
    }
    
    public ArrayList<Pelicula> obtenerPeliculas(String nombre,int inferior,int cantidad) throws AefilepException
    {
        return peliculas.obtenerPeliculas(nombre,inferior,cantidad);
    }
    
    public ArrayList<Pelicula> buscarPeliculas(int inferior, int cantidad) throws AefilepException
    {
        return peliculas.buscarPeliculas(inferior,cantidad);
    }
    
    public int cantidadPeliculasActivas() throws AefilepException
    {
        return peliculas.cantidadPeliculasActivas();
    }
    
    public int cantidadEstrenosActivos() throws AefilepException
    {
        return peliculas.cantidadEstrenosActivos();
    }
    
    public int cantidadBuscadorActivos(String nombre) throws AefilepException
    {
        return peliculas.cantidadBuscadorActivos(nombre);
    }
    
    public int cantidadGenerosActivos(int id) throws AefilepException
    {
        return peliculas.cantidadGenerosActivos(id);
    }
    
    public ArrayList<Pelicula> obtenerEstrenos(int inferior,int cantidad) throws AefilepException
    {
        return peliculas.obtenerEstrenos(inferior,cantidad);
    }
    
    public ArrayList<Pelicula> obtenerEstrenos(int cant) throws AefilepException
    {
        return peliculas.obtenerEstrenos(cant);
    }
    
    public ArrayList<Pelicula> obtenerGenero(int idGenero, int inferior, int cantidad) throws AefilepException
    {
        return peliculas.obtenerGenero(idGenero,inferior,cantidad);
    }
}
