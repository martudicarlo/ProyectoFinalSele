/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.datos;

import aplicacion.modelo.entidades.Pelicula;
import aplicacion.utilidades.AefilepException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class PeliculaDB
{
    Conexion conec = new Conexion();
    Connection con = null;
    
    /**
     * agrega la pelicula a la base de datos
     * @param p pelicula a agregar
     * @throws AefilepException 
     */
    
    public void agregarPelicula(Pelicula p) throws AefilepException
    {
        String transac = "insert into aefilep.peliculas values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try
        {
            con = conec.getConexion();
            PreparedStatement pr = con.prepareStatement(transac,Statement.RETURN_GENERATED_KEYS);    
            pr.setNull(1,0);
            pr.setString(2, p.getNombre());
            pr.setInt(3, p.getDuracion());
            pr.setString(4, p.getFormato());
            pr.setInt(5, p.getStockAlquiler());
            pr.setInt(6, p.getStockVenta());
            pr.setBlob(7,p.getImagen());
            pr.setString(8, p.getReparto());
            pr.setDate(9, new java.sql.Date(p.getFechaCarga().getTime()));
            pr.setBoolean(10,p.isActivo());
            pr.setString(11, p.getUrlTrailer());
            pr.setFloat(12, p.getPrecioVenta());
            pr.setString(13, p.getSinopsis());
            pr.setInt(14, p.getAnio());
            pr.executeUpdate();
            ResultSet rs = pr.getGeneratedKeys();
            
            if(rs.next())
            {
                int id = rs.getInt(1);
                p.setIdPelicula(id);
            }
            
            new PeliculasGenerosBD().agregarPeliculaGeneros(p);
            con.close();
        }
        catch(SQLException ex)
        {
            throw new AefilepException("Error al registrar película.",ex);
        }
    }
    
    public void actualizarStock(Pelicula p) throws AefilepException
    {         
        String sql = "update peliculas set stock_alquiler=?, stock_compra=? where id_pelicula=?;";
            
        try
        {
            con = conec.getConexion();
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, p.getStockAlquiler());
            pr.setInt(2, p.getStockVenta());            
            pr.setInt(3, p.getIdPelicula());
            pr.executeUpdate();
            con.close();
        }
        catch(SQLException ex)
        {
            throw new AefilepException("Error al actualizar stock de la película",ex);
        }    
    }
    
    /**
     * actualiza una pelicula
     * @param p pelicula a editar
     * @throws AefilepException 
     */
    public void actualizarPelicula(Pelicula p) throws AefilepException
    {      
        if(p.getImagen()!=null)
        { 
            String sql = "update peliculas set nombre=? , duracion=? , formato=? ,"
                + " stock_alquiler=? ,stock_compra=?, reparto=?, activo=?,url_trailer=? ,"
                + " precio_venta=?, sinopsis=?, anio=?, imagen=? where id_pelicula=?";            
            try
            {
                con = conec.getConexion();
                PreparedStatement pr = con.prepareStatement(sql);
                pr.setString(1, p.getNombre());
                pr.setInt(2, p.getDuracion());
                pr.setString(3, p.getFormato());
                pr.setInt(4, p.getStockAlquiler());
                pr.setInt(5, p.getStockVenta());           
                pr.setString(6, p.getReparto());
                pr.setBoolean(7, p.isActivo());            
                pr.setString(8, p.getUrlTrailer());
                pr.setFloat(9, p.getPrecioVenta());
                pr.setString(10, p.getSinopsis());
                pr.setInt(11, p.getAnio());           
                pr.setBlob(12, p.getImagen());            
                pr.setInt(13, p.getIdPelicula());
                
                new PeliculasGenerosBD().actualizarPeliculasGeneros(p);
                pr.executeUpdate();

                con.close();
            }
            catch(SQLException ex)
            {
                throw new AefilepException("Error al actualizar datos de la película",ex);
            }        
        }
        else            
        {
            String sql = "update peliculas set nombre=? , duracion=? , formato=? ,"
                + " stock_alquiler=? ,stock_compra=?, reparto=?, activo=?,url_trailer=? ,"
                + " precio_venta=?, sinopsis=?, anio=? where id_pelicula=?";
            try
            {
                con = conec.getConexion();
                PreparedStatement pr = con.prepareStatement(sql);
                pr.setString(1, p.getNombre());
                pr.setInt(2, p.getDuracion());
                pr.setString(3, p.getFormato());
                pr.setInt(4, p.getStockAlquiler());
                pr.setInt(5, p.getStockVenta());           
                pr.setString(6, p.getReparto());
                pr.setBoolean(7, p.isActivo());            
                pr.setString(8, p.getUrlTrailer());
                pr.setFloat(9, p.getPrecioVenta());
                pr.setString(10, p.getSinopsis());
                pr.setInt(11, p.getAnio());           
                pr.setInt(12, p.getIdPelicula());
                
                new PeliculasGenerosBD().actualizarPeliculasGeneros(p);
                pr.executeUpdate();
           
                con.close();
            }
            catch(SQLException ex)
            {
                throw new AefilepException("Error al actualizar datos de la película",ex);
            }  
        }    
    }
     
    public byte[] buscarImagen(int id) throws AefilepException
    {
        String transac = "select imagen from peliculas where id_pelicula=?;";
        byte[] imgData=null;
        
        try
        {
            con = conec.getConexion();
            PreparedStatement pr = con.prepareStatement(transac);
            pr.setInt(1, id);
            ResultSet res = pr.executeQuery();      
            if(res.next())
            {
                imgData = res.getBytes("imagen");
            }
            con.close();
        } 
        catch (SQLException ex)
        {
            throw new AefilepException("Error al recuperar imagen.", ex);
        }
        return imgData;
    }
     
    public ArrayList<Pelicula> obtenerPeliculas() throws AefilepException
    {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        String transac = "select * from peliculas";
        try
        {
            con = conec.getConexion();
            PreparedStatement pr = con.prepareStatement(transac);
            ResultSet res = pr.executeQuery();
                   
            while(res.next())
            {
                Pelicula p = new Pelicula();

                p.setIdPelicula(res.getInt(1));
                p.setNombre(res.getString(2));
                p.setDuracion(res.getInt(3));
                p.setFormato(res.getString(4));
                p.setStockAlquiler(res.getInt(5));
                p.setStockVenta(res.getInt(6));
                p.setReparto(res.getString(8));
                p.setFechaCarga(new java.sql.Date(res.getDate(9).getTime()));
                p.setActivo(res.getBoolean(10));
                p.setUrlTrailer(res.getString(11));
                p.setPrecioVenta(res.getFloat(12));
                p.setSinopsis(res.getString(13));
                p.setAnio(res.getInt(14));

                if(p.isEstreno())
                {
                    p.setPrecioAlquiler(new ParametroBD().obtenerParametros().getPrecioAlquilerEstreno());
                }
                else
                {
                    p.setPrecioAlquiler(new ParametroBD().obtenerParametros().getPrecioAlquiler());
                }

                p.setGeneros(new PeliculasGenerosBD().obtenerGenerosPelicula(p.getIdPelicula()));
                listaPeliculas.add(p);
            }
            con.close();
        }
        catch(SQLException Ex)
        {
            throw new AefilepException("Error el recuperar datos de las películas",Ex);
        }
         
        return listaPeliculas;
    }
    
    public ArrayList<Pelicula> obtenerPeliculas(String nombre, int inferior, int cantidad) throws AefilepException
    {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        String transac = "select * from peliculas where nombre like '%"+nombre+"%'and activo=1 limit ?,?;";
        try
        {
            con = conec.getConexion();
            PreparedStatement pr = con.prepareStatement(transac);
            pr.setInt(1, inferior);
            pr.setInt(2, cantidad);

            ResultSet res = pr.executeQuery();
            while(res.next())
            {
                Pelicula p = new Pelicula();

                p.setIdPelicula(res.getInt(1));
                p.setNombre(res.getString(2));
                p.setDuracion(res.getInt(3));
                p.setFormato(res.getString(4));
                p.setStockAlquiler(res.getInt(5));
                p.setStockVenta(res.getInt(6));
                p.setReparto(res.getString(8));
                p.setFechaCarga(new java.sql.Date(res.getDate(9).getTime()));
                p.setActivo(res.getBoolean(10));
                p.setUrlTrailer(res.getString(11));
                p.setPrecioVenta(res.getFloat(12));
                p.setSinopsis(res.getString(13));
                p.setAnio(res.getInt(14));

                if(p.isEstreno())
                {
                    p.setPrecioAlquiler(new ParametroBD().obtenerParametros().getPrecioAlquilerEstreno());
                }
                else
                {
                    p.setPrecioAlquiler(new ParametroBD().obtenerParametros().getPrecioAlquiler());
                }
                p.setGeneros(new PeliculasGenerosBD().obtenerGenerosPelicula(p.getIdPelicula()));

                listaPeliculas.add(p);
            }
            con.close();
        }
        catch(SQLException Ex)
        {
            throw new AefilepException("Error el recuperar datos de las películas",Ex);
        }
        
        return listaPeliculas;
    }
     
    public ArrayList<Pelicula> buscarPeliculas(int inferior,int cantidad) throws AefilepException
    {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        String transac = "select * from peliculas where activo=1 limit ?,?;";
        
        try
        {
            con = conec.getConexion();
            PreparedStatement pr = con.prepareStatement(transac);
            
            pr.setInt(1, inferior);
            pr.setInt(2, cantidad);
            
            ResultSet res = pr.executeQuery();
                   
            while(res.next())
            {
                Pelicula p = new Pelicula();
                
                p.setIdPelicula(res.getInt(1));
                p.setNombre(res.getString(2));
                p.setDuracion(res.getInt(3));
                p.setFormato(res.getString(4));
                p.setStockAlquiler(res.getInt(5));
                p.setStockVenta(res.getInt(6));
                p.setReparto(res.getString(8));
                p.setFechaCarga(new java.sql.Date(res.getDate(9).getTime()));
                p.setActivo(res.getBoolean(10));
                p.setUrlTrailer(res.getString(11));
                p.setPrecioVenta(res.getFloat(12));
                p.setSinopsis(res.getString(13));
                p.setAnio(res.getInt(14));
                
                if(p.isEstreno())
                    p.setPrecioAlquiler(new ParametroBD().obtenerParametros().getPrecioAlquilerEstreno());
                else
                    p.setPrecioAlquiler(new ParametroBD().obtenerParametros().getPrecioAlquiler());
          
                listaPeliculas.add(p);
            }
            con.close();
        }
        catch(SQLException ex)
        {
            throw new AefilepException("Error al recuperar películas",ex);
        }
        return listaPeliculas;
    }
     
    public Pelicula obtenerPelicula(int idPeli) throws AefilepException
    {
        Pelicula p = null;
        String transac = "select * from peliculas where activo=1 and id_pelicula=?";
        
        try
        {
            con = conec.getConexion();
            PreparedStatement pr = con.prepareStatement(transac);
            pr.setInt(1, idPeli);
            ResultSet res = pr.executeQuery();
                   
            if(res.next())
            {   
                p=new Pelicula();
                p.setIdPelicula(res.getInt(1));
                p.setNombre(res.getString(2));
                p.setDuracion(res.getInt(3));
                p.setFormato(res.getString(4));
                p.setStockAlquiler(res.getInt(5));
                p.setStockVenta(res.getInt(6));
                p.setReparto(res.getString(8));
                p.setFechaCarga(new java.sql.Date(res.getDate(9).getTime()));
                p.setActivo(res.getBoolean(10));
                p.setUrlTrailer(res.getString(11));
                p.setPrecioVenta(res.getFloat(12));
                p.setSinopsis(res.getString(13));
                p.setAnio(res.getInt(14));  
            
                if(p.isEstreno())
                    p.setPrecioAlquiler(new ParametroBD().obtenerParametros().getPrecioAlquilerEstreno());
                else
                    p.setPrecioAlquiler(new ParametroBD().obtenerParametros().getPrecioAlquiler());
            }
            con.close();
        }
        catch(SQLException ex)
        {
            throw new AefilepException("Error al recuperar los datos de la película",ex);
        }
        
        return p;
    }
    /**
     * valida que la pelicula no exista en la base de datos comparando por nombre
     * @param nombrePelicula nombre de la pelicula
     * @return true si existe pelicula
     * @throws AefilepException 
     */
    
    public boolean existePelicula(String nombrePelicula) throws AefilepException
    {      
        String transac = "select count(*) from peliculas where nombre=?";        
        
        int cantidad=0;
        
        try
        {
            con = conec.getConexion();
            PreparedStatement pr = con.prepareStatement(transac);
            pr.setString(1, nombrePelicula);
            ResultSet res = pr.executeQuery();
                   
            if(res.next())              
                cantidad = res.getInt(1);
                       
            con.close();
        }
        catch(SQLException ex)
        {
            throw new AefilepException("Error al recuperar los datos de la película",ex);
        }
        
        return cantidad > 0;
    }
    
    public int cantidadPeliculas() throws AefilepException
    {
        int i=0;
        String transac = "select count(*) from peliculas;";
        
        try
        {
            con = conec.getConexion();
            PreparedStatement pr = con.prepareStatement(transac);
            ResultSet res = pr.executeQuery();
            
             if(res.next())
            {
                i = res.getInt(1);
                con.close();   
            }
        }
        catch(SQLException ex)
        {
            throw new AefilepException("Error al contar películas",ex);
        }
        
        return i;
    }
     
    public int cantidadPeliculasActivas() throws AefilepException
    {
        int i=0;
        String transac = "select count(*) from peliculas where activo=1;";
        
        try
        {
            con = conec.getConexion();
            PreparedStatement pr = con.prepareStatement(transac);
            ResultSet res = pr.executeQuery();
            
             if(res.next())
            {
                i = res.getInt(1);
                con.close();   
            }
        }
        catch(SQLException ex)
        {
            throw new AefilepException("Error al contar películas",ex);
        }
        
        return i;
    }
          
     public int cantidadEstrenosActivos() throws AefilepException
     {
        int i=0;
        String transac = "select count(*) from peliculas where activo=1 and (`fecha_carga` +7)>CURRENT_DATE();";
        
        try
        {
            con = conec.getConexion();
            PreparedStatement pr = con.prepareStatement(transac);
            ResultSet res = pr.executeQuery();
            
            if(res.next())
            {
                i = res.getInt(1);
                con.close();
            }
        }
        catch(SQLException ex)
        {
            throw new AefilepException("Error al contar estrenos",ex);
        }
        
        return i;
    }
     
    public int cantidadGenerosActivos(int id) throws AefilepException
    {
        int i=0;
        String transac = "select count(*) from peliculas p inner join peliculas_generos pg on p.id_pelicula=pg.id_pelicula where id_genero=? and activo=1;";
        
        try
        {
            con = conec.getConexion();
            PreparedStatement pr = con.prepareStatement(transac);
            pr.setInt(1,id);
            ResultSet res = pr.executeQuery();
            
            if(res.next())
            {
                i = res.getInt(1);
                con.close();
            }
        }
        catch(SQLException ex)
        {
            throw new AefilepException("Error al contar géneros.", ex);
        }
        
        return i;
    }
    
    public int cantidadBuscadorActivos(String nombre) throws AefilepException
    {
        int i=0;
        String transac = "select count(*) from peliculas where  nombre like '%"+nombre+"%'and activo=1 ;";
        
        try
        {
            con = conec.getConexion();
            PreparedStatement pr = con.prepareStatement(transac);
            ResultSet res = pr.executeQuery();
            if(res.next())
            {
                i = res.getInt(1);
                con.close();
            }
        }
        catch(SQLException ex)
        {
            throw new AefilepException("Error con los datos de las películas",ex);
        }
             
        return i;
    }
    
    public ArrayList<Pelicula> obtenerEstrenos(int inferior,int cantidad) throws AefilepException
    {
        ArrayList<Pelicula> listaEstrenos = new ArrayList<>();
        String transac = "select * from peliculas where(`fecha_carga` +7)>CURRENT_DATE() and activo=1 limit ?,?;";
        try
        {
            con = conec.getConexion();
            PreparedStatement pr = con.prepareStatement(transac);
            pr.setInt(1, inferior);
            pr.setInt(2, cantidad);
            ResultSet res = pr.executeQuery();
                   
            while(res.next())
            {
                Pelicula p = new Pelicula();
                
                p.setIdPelicula(res.getInt(1));
                p.setNombre(res.getString(2));
                p.setDuracion(res.getInt(3));
                p.setFormato(res.getString(4));
                p.setStockAlquiler(res.getInt(5));
                p.setStockVenta(res.getInt(6));
                p.setReparto(res.getString(8));
                p.setFechaCarga(new java.sql.Date(res.getDate(9).getTime()));
                p.setActivo(res.getBoolean(10));
                p.setUrlTrailer(res.getString(11));
                p.setPrecioVenta(res.getFloat(12));
                p.setSinopsis(res.getString(13));
                p.setAnio(res.getInt(14));
                
                if(p.isEstreno())
                    p.setPrecioAlquiler(new ParametroBD().obtenerParametros().getPrecioAlquilerEstreno());
                else
                    p.setPrecioAlquiler(new ParametroBD().obtenerParametros().getPrecioAlquiler());

                
                p.setGeneros(new PeliculasGenerosBD().obtenerGenerosPelicula(p.getIdPelicula()));
                
                listaEstrenos.add(p);
            }
            con.close();
        }
        catch(SQLException ex)
        {
            throw new AefilepException("Error al recuperar datos de las películas",ex);
        }
        
        return listaEstrenos;
    }
    
    public ArrayList<Pelicula> obtenerEstrenos(int cant) throws AefilepException
    {
        ArrayList<Pelicula> listaEstrenos = new ArrayList<>();
        String transac = "select * from peliculas where(`fecha_carga` +7)>CURRENT_DATE() and activo=1 order by `fecha_carga` desc limit 0,?;";
        
        try
        {
            con = conec.getConexion();
            PreparedStatement pr = con.prepareStatement(transac);
            pr.setInt(1, cant);
            ResultSet res = pr.executeQuery();
                   
            while(res.next())
            {
                Pelicula p = new Pelicula();
                
                p.setIdPelicula(res.getInt(1));
                p.setNombre(res.getString(2));
                p.setDuracion(res.getInt(3));
                p.setFormato(res.getString(4));
                p.setStockAlquiler(res.getInt(5));
                p.setStockVenta(res.getInt(6));
                p.setReparto(res.getString(8));
                p.setFechaCarga(new java.sql.Date(res.getDate(9).getTime()));
                p.setActivo(res.getBoolean(10));
                p.setUrlTrailer(res.getString(11));
                p.setPrecioVenta(res.getFloat(12));
                p.setSinopsis(res.getString(13));
                p.setAnio(res.getInt(14));
                
                if(p.isEstreno())
                    p.setPrecioAlquiler(new ParametroBD().obtenerParametros().getPrecioAlquilerEstreno());
                else
                    p.setPrecioAlquiler(new ParametroBD().obtenerParametros().getPrecioAlquiler());
                
                p.setGeneros(new PeliculasGenerosBD().obtenerGenerosPelicula(p.getIdPelicula()));
                
                listaEstrenos.add(p);
            }
            con.close();
        }
        catch(SQLException ex)
        {
            throw new AefilepException("Error al obtener estrenos",ex);
        }  
        
        if (listaEstrenos.size()<3)
        {    
            int limit= cant-listaEstrenos.size();
            String transac2 = "select * from peliculas where(`fecha_carga` +7)<CURRENT_DATE() and activo=1 limit 0,?;";
            try
            {
                con = conec.getConexion();
                PreparedStatement pr2 = con.prepareStatement(transac2);
                pr2.setInt(1,limit);
                ResultSet res2 = pr2.executeQuery();

                while(res2.next())
                {
                    Pelicula p = new Pelicula();

                    p.setIdPelicula(res2.getInt(1));
                    p.setNombre(res2.getString(2));
                    p.setDuracion(res2.getInt(3));
                    p.setFormato(res2.getString(4));
                    p.setStockAlquiler(res2.getInt(5));
                    p.setStockVenta(res2.getInt(6));
                    p.setReparto(res2.getString(8));
                    p.setFechaCarga(new java.sql.Date(res2.getDate(9).getTime()));
                    p.setActivo(res2.getBoolean(10));
                    p.setUrlTrailer(res2.getString(11));
                    p.setPrecioVenta(res2.getFloat(12));
                    p.setSinopsis(res2.getString(13));
                    p.setAnio(res2.getInt(14));
                     
                    if(p.isEstreno())
                        p.setPrecioAlquiler(new ParametroBD().obtenerParametros().getPrecioAlquilerEstreno());
                    else
                        p.setPrecioAlquiler(new ParametroBD().obtenerParametros().getPrecioAlquiler());

                    p.setGeneros(new PeliculasGenerosBD().obtenerGenerosPelicula(p.getIdPelicula()));
                    listaEstrenos.add(p);                  
                }
                con.close();
            }
            catch(SQLException ex)
            {
                throw new AefilepException("Error al recuperar estrenos",ex);
            }
        }
                    
        return listaEstrenos;
    }
    
    public ArrayList<Pelicula> obtenerGenero(int idGenero, int inferior, int cantidad) throws AefilepException
    {
        ArrayList<Pelicula> listaGenero = new ArrayList<>();
        String transac = "select p.id_pelicula from peliculas_generos pg inner join peliculas p on pg.id_pelicula=p.id_pelicula where pg.id_genero=? and p.activo=1 limit ?,?";
        
        try
        {
            con = conec.getConexion();
            PreparedStatement pr = con.prepareStatement(transac);
            pr.setInt(1, idGenero);
            pr.setInt(2, inferior);
            pr.setInt(3,cantidad);
            ResultSet res = pr.executeQuery();
                   
            while(res.next())
            {
                Pelicula p = obtenerPelicula(res.getInt(1));
                if(p!=null)
                {               
                    listaGenero.add(p);
                }
            }
            con.close();
        }
        catch(SQLException ex)
        {
            throw new AefilepException("Error al recuperar películas",ex);
        }         
        return listaGenero;
    }
}
