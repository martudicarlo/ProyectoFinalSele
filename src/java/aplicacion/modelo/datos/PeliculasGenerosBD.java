/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.datos;

import aplicacion.modelo.entidades.Genero;
import aplicacion.modelo.entidades.Pelicula;
import aplicacion.utilidades.AefilepException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author marti_000
 */
public class PeliculasGenerosBD
{
    Conexion conec = new Conexion();

    /**
     * agrega los generos a una pelicula
     * @param p
     * @throws AefilepException 
     */
    public void agregarPeliculaGeneros(Pelicula p) throws AefilepException
    {
        Connection con = conec.getConexion();
        String transac = "insert into aefilep.peliculas_generos values ";
        
        for(int i=0; i<p.getGeneros().size(); i++)
        {
            transac = transac +"("+p.getIdPelicula()+","+p.getGeneros().get(i).getIdGenero()+")";
            
            if(i==p.getGeneros().size()-1)            
                transac = transac+";";
            else            
                transac = transac+",";            
        }
        
        try
        {   
            PreparedStatement pr = con.prepareStatement(transac);
            pr.executeUpdate();
            con.close(); 
        }
        catch(SQLException ex)
        {
            throw(new AefilepException ("Error al insertar género en la película",ex));
        }  
    }
    
    /**
     * elimina y agrega los generos de una pelicula
     * @param p pelicula a editar
     * @throws AefilepException 
     */ 
    public void actualizarPeliculasGeneros(Pelicula p) throws AefilepException
    {
        Connection con = conec.getConexion();
        String transac = "delete from aefilep.peliculas_generos where id_pelicula=?;";
        
        try
        {   
            PreparedStatement pr = con.prepareStatement(transac);
            pr.setInt(1, p.getIdPelicula());
            pr.executeUpdate();
            
            con.close();
            
            this.agregarPeliculaGeneros(p);
        }
        catch(SQLException ex)
        {
            throw new AefilepException("Error al actualizar datos de la película",ex);
        }
    }
     
    public ArrayList<Genero> obtenerGenerosPelicula(int id) throws AefilepException
    {
        ArrayList<Genero> generos = new ArrayList<Genero>();
        Connection con = conec.getConexion();
        String transac = "SELECT aefilep.generos.id_genero, descripcion FROM aefilep.peliculas_generos inner join aefilep.generos " +
                         "on aefilep.peliculas_generos.id_genero=aefilep.generos.id_genero " +
                         "where id_pelicula=?;";
        try
        {   
           
            PreparedStatement pr = con.prepareStatement(transac);
            pr.setInt(1, id);
            ResultSet res = pr.executeQuery();
            while(res.next())
            {
                Genero gen = new Genero();
                gen.setIdGenero(res.getInt(1));
                gen.setDescripcion(res.getString(2));
                generos.add(gen);
            }
            con.close(); 
        }
        catch(SQLException ex)
        {
            throw(new AefilepException ("Error al obtener géneros de la película",ex));
        }
        
        return generos;
    }
}
