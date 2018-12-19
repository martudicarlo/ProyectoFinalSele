/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.datos;

import aplicacion.modelo.entidades.LineaPedido;
import aplicacion.utilidades.AefilepException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class LineaBD 
{
    Conexion conec = new Conexion();
    
    public ArrayList<LineaPedido> obtenerLineaAlq (int idPedido) throws AefilepException 
    {
        String sql = "select * from aefilep.pedidos_peliculas where id_pedido =? and es_alquiler=1;";
        Connection con = null;      
        ArrayList<LineaPedido> lineas = new ArrayList<>();
        
        try
        {
            con = conec.getConexion();

            PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, idPedido);
            ResultSet res = pr.executeQuery();
              
            while(res.next())
            {
                LineaPedido lp = new LineaPedido();
                
                lp.setPelicula(new PeliculaDB().obtenerPelicula(res.getInt(1)));
                lp.setCantidad(res.getInt(3));
                lp.setEsAlquiler(res.getBoolean(4));
                lp.setSubtotal(res.getFloat(5));
                
                lineas.add(lp);
            }             
        }  
        catch (SQLException ex)
        {
            throw new AefilepException("Error al recuperar la linea",ex);
        }
        
        return lineas;         
    }
    
    public ArrayList<LineaPedido> obtenerLineas (int idPedido) throws AefilepException 
    {
        String sql = "select * from aefilep.pedidos_peliculas where id_pedido =?;";
        Connection con = null;
        ArrayList<LineaPedido> lineas = new ArrayList<>();
       
        try
        {
            con = conec.getConexion();

            PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, idPedido);
            ResultSet res = pr.executeQuery();

            while(res.next())
            {
                LineaPedido lp = new LineaPedido();
                
                lp.setPelicula(new PeliculaDB().obtenerPelicula(res.getInt(1)));
                lp.setCantidad(res.getInt(3));
                lp.setEsAlquiler(res.getBoolean(4));
                lp.setSubtotal(res.getFloat(5));
                
                lineas.add(lp);
            }              
        }           
        catch (SQLException ex)
        {
            throw new AefilepException("Error al recuperar las lineas",ex);
        }
        
        return lineas;         
    }
    
    public void registrarLineas(ArrayList<LineaPedido> lineas, int idPedido, int dias) throws AefilepException, SQLException 
    {
        
        for(LineaPedido lp: lineas ) 
        {   
            String transac1 = "insert into aefilep.pedidos_peliculas values (?,?,?,?,?);";
            Connection con;
            try
            {
                con = conec.getConexion();
                
                PreparedStatement pr = con.prepareStatement(transac1);
                
                pr.setInt(1, lp.getPelicula().getIdPelicula());
                pr.setInt(2, idPedido);
                pr.setInt(3, lp.getCantidad());
                pr.setBoolean(4, lp.isEsAlquiler());
                pr.setFloat(5, lp.getSubtotal(dias));
                pr.executeUpdate();
                con.close();   
            }
            catch (SQLException ex)
            {
                throw new AefilepException("Error al recuperar el genero",ex);
            }              
        }      
    }      
}
