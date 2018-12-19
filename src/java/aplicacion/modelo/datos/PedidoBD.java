/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.datos;

import aplicacion.modelo.entidades.LineaPedido;
import aplicacion.modelo.entidades.Pelicula;
import aplicacion.modelo.entidades.Pedido;
import aplicacion.modelo.entidades.Usuario;
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
public class PedidoBD 
{
    Conexion conec = new Conexion();
     
    public void cerrarPedido(Pedido p) throws AefilepException 
    {
        Connection con;
        try
        {
            con = conec.getConexion();
        }
        catch (Exception ex) 
        {
            throw new AefilepException("Error al recuperar el pedido",ex);
        }
        String transac = "update aefilep.pedidos set estado=?, fecha_devolucion=? where id_pedido=?;";
        try
        {
            PreparedStatement pr = con.prepareStatement(transac);
            pr.setString(1, p.getEstado());
            pr.setDate(2, new java.sql.Date(p.getFechaDevolucion().getTime()));
            pr.setInt(3, p.getIdPedido());
            pr.executeUpdate();
            for(LineaPedido lp:p.getLineas())
            {
                if(lp.isEsAlquiler())
                {
                    Pelicula pel = lp.getPelicula();
                    new PeliculaDB().actualizarStock(pel);
                }   
            }
        }
        catch(SQLException ex)
        {
            throw new AefilepException("Error al recuperar el pedido",ex);
        }
    }
    
    public void registrarEnvio(Pedido p) throws AefilepException 
    {
        Connection con;
       
        try
        {
            con = conec.getConexion();  
            String transac = "update aefilep.pedidos set estado=? where id_pedido=?;";
      
            PreparedStatement pr = con.prepareStatement(transac);
            pr.setString(1, p.getEstado());
            pr.setInt(2, p.getIdPedido());
            pr.executeUpdate();
        }
        catch(SQLException ex)
        {
            throw new AefilepException("Error al recuperar el pedido",ex);
        }
    }
     
    public void registrarPedido(Pedido p) throws AefilepException 
    {        
        Connection con;
        try 
        {
            con = conec.getConexion();
        }
        catch (AefilepException ex)
        {            
            throw new AefilepException("Error al cargar el pedido",ex);
        }
    
        String transac = "insert into aefilep.pedidos values (?,?,?,?,?,?,?);";
        
        try
        {   
            PreparedStatement pr = con.prepareStatement(transac, PreparedStatement.RETURN_GENERATED_KEYS);
            pr.setDate(2, new java.sql.Date(p.getFechaRealizacion().getTime()));
            pr.setDate(3,new java.sql.Date(p.getFechaDesde().getTime()));
            pr.setDate(4,new java.sql.Date(p.getFechaHasta().getTime()));
            pr.setString(5, p.getEstado());
            pr.setDate(6,null);             
            pr.setInt(7,p.getUsuario().getIdUsuario());
            pr.setNull(1,0);
            pr.executeUpdate();
             
            ResultSet rs=pr.getGeneratedKeys(); //obtengo las ultimas llaves generadas
            
            if(rs.next())
            { 
                int clave=rs.getInt(1);
                LineaBD lbd=new LineaBD();
                lbd.registrarLineas(p.getLineas(),clave, p.getDias());
            }
                       
             con.close();      
        }
        catch(SQLException ex)
        {
             throw new AefilepException("Error al recuperar el pedido",ex);
        }
    }
     
    public ArrayList<Pedido> obtenerPedidos(int idUsuario) throws AefilepException
    {         
        ArrayList<Pedido> pedidosEncontrados = new ArrayList<>();
        Connection con;
        
        try 
        {
            con = conec.getConexion();
    
            String sql = "SELECT * FROM aefilep.pedidos where id_usuario=?;";
          
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1,idUsuario);
            ResultSet res =pr.executeQuery();
             
            while(res.next())
            { 
                Pedido p = new Pedido();
                
                p.setIdPedido(res.getInt(1));
                p.setFechaRealizacion(new java.sql.Date(res.getDate(2).getTime()));
                p.setFechaDesde(new java.sql.Date(res.getDate(3).getTime()));
                p.setFechaHasta(new java.sql.Date(res.getDate(4).getTime()));
                p.setEstado(res.getString(5));
                p.setFechaDevolucion(null);
                p.setLineas(new LineaBD().obtenerLineas(p.getIdPedido()));
                                
                pedidosEncontrados.add(p);                     
            }                   
            con.close();      
        }
        catch (SQLException ex) 
        {
            throw new AefilepException("Error al recuperar los pedidos",ex);
        }
        return pedidosEncontrados;
    } 
     
    public ArrayList<Pedido> obtenerPedidosEnviados (int idPedido) throws AefilepException
    {         
        ArrayList<Pedido> pedidosEncontrados = new ArrayList<>();
        Connection con;
           
        String sql = "SELECT * FROM aefilep.pedidos where id_usuario=? and estado like 'Enviado';";
        
        try
        {
            con = conec.getConexion();

            PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1,idPedido);
            ResultSet res =pr.executeQuery();
             
            while(res.next())
            { 
                Pedido p = new Pedido();
                
                p.setIdPedido(res.getInt(1));
                p.setFechaRealizacion(new java.sql.Date(res.getDate(2).getTime()));
                p.setFechaDesde(new java.sql.Date(res.getDate(3).getTime()));
                p.setFechaHasta(new java.sql.Date(res.getDate(4).getTime()));
                p.setEstado(res.getString(5));
                p.setFechaDevolucion(null);
                p.setLineas(new LineaBD().obtenerLineaAlq(p.getIdPedido()));
                                
                pedidosEncontrados.add(p);                     
            }                   
            con.close();      
        }
        catch(SQLException ex)
        {
            throw new AefilepException("Error al recuperar pedidos",ex);
        }
        return pedidosEncontrados;
    }
        
    public ArrayList<Pedido> obtenerPedidosPendientes () throws AefilepException
    {         
        ArrayList<Pedido> pedidosEncontrados = new ArrayList<>();
        Connection con;
        String sql = "SELECT * FROM pedidos p inner join usuarios u on p.id_usuario=u.id_usuario where p.estado like 'Pendiente';";   
        
        try
        {
            con = conec.getConexion();
        
            PreparedStatement pr = con.prepareStatement(sql);           
            ResultSet res =pr.executeQuery();
             
            while(res.next())
            { 
                Pedido p = new Pedido();
                
                p.setIdPedido(res.getInt(1));
                p.setFechaRealizacion(new java.sql.Date(res.getDate(2).getTime()));
                p.setFechaDesde(new java.sql.Date(res.getDate(3).getTime()));
                p.setFechaHasta(new java.sql.Date(res.getDate(4).getTime()));
                p.setEstado(res.getString(5));
                p.setFechaDevolucion(null);
                p.setLineas(new LineaBD().obtenerLineas(p.getIdPedido()));
                Usuario usu= new Usuario();
                usu.setNombre(res.getString(9));
                usu.setApellido(res.getString(10));
                usu.setDireccion(res.getString(13));
                p.setUsuario(usu);
                                
                pedidosEncontrados.add(p);                     
            }                   
            con.close();      
        }
        catch(SQLException ex)
        {
            throw new AefilepException("Error al recuperar los pedidos",ex);
        }
        return pedidosEncontrados;
    }         
}
