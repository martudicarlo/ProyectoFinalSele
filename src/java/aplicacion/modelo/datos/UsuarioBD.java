/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.datos;
import aplicacion.modelo.entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import aplicacion.utilidades.AefilepException;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author JP
 */
public class UsuarioBD
{
    Conexion conec = new Conexion();
    
    public Usuario buscarUsuario(String nom, String contra) throws AefilepException
    {
        Usuario usu=null;
        String sql = "select * from usuarios where nombre_usuario=? and contrasena=? and activo=1;";
        Connection con =null; 
        
        try
        {
            con = conec.getConexion();

            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, nom);
            pr.setString(2, contra);
            ResultSet res = pr.executeQuery();
            
            if(res.next())
            {
                usu = new Usuario();
                usu.setActivo(res.getBoolean(10));
                usu.setApellido(res.getString(3));
                usu.setBloqueado(res.getBoolean(11));
                usu.setContrasena(res.getString(4));
                usu.setDireccion(res.getString(6));
                usu.setDni(res.getString(8));
                usu.setEsAdmin(res.getBoolean(5));
                usu.setFechaNacimiento(res.getDate(9));
                usu.setIdUsuario(res.getInt(1));
                usu.setMail(res.getString(12));
                usu.setNombre(res.getString(2));
                usu.setNombreUsuario(res.getString(13));
                usu.setTelefono(res.getString(7));  
            }
            con.close();
        }
        catch(SQLException ex)
        {
            throw new AefilepException("Error al recuperar usuario",ex);
        }
               
        return usu;
    }
       
    public boolean existeUsuario(String nombreUsuario) throws AefilepException
    {        
        String sql = "select count(*) from usuarios where nombre_usuario=? ;";
        Connection con =null; 
        int cantidad = 0;
        try
        {
            con = conec.getConexion();

            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, nombreUsuario);
            
            ResultSet res = pr.executeQuery();
                           
            if(res.next())
            {
                cantidad = res.getInt(1);
                con.close();
            }
            
            con.close();
        }
        catch(SQLException ex)
        {
            throw new AefilepException("Error al recuperar usuario",ex);
        }
                 
        return cantidad > 0;
    }
       
    public ArrayList<Usuario> buscarUsuarios(Usuario usu) throws AefilepException
    {
        ArrayList<Usuario> resultado = new ArrayList<Usuario>();
                        
        String apell = usu.getApellido();
        String sql = "select * from aefilep.usuarios where activo=1 and apellido like '"+apell+"%'";
        int id = usu.getIdUsuario();
        String nombre = usu.getNombre();
        String dni = usu.getDni();
        if(id!=0)
            sql = sql + " and id_usuario="+id;
        if(!nombre.equals(""))
            sql = sql + " and nombre like '"+nombre+"%'";
        if(!dni.equals(""))
            sql = sql + " and dni like '%"+dni+"%'";
        sql = sql+";";
        
        try
        {
            Connection con = conec.getConexion();
            PreparedStatement pr = con.prepareStatement(sql);
            ResultSet res = pr.executeQuery();
            
            while(res.next())
            {
                usu = new Usuario();
                usu.setActivo(res.getBoolean(10));
                usu.setApellido(res.getString(3));
                usu.setBloqueado(res.getBoolean(11));
                usu.setContrasena(res.getString(4));
                usu.setDireccion(res.getString(6));
                usu.setDni(res.getString(8));
                usu.setEsAdmin(res.getBoolean(5));
                usu.setFechaNacimiento(res.getDate(9));
                usu.setIdUsuario(res.getInt(1));
                usu.setMail(res.getString(12));
                usu.setNombre(res.getString(2));
                usu.setNombreUsuario(res.getString(13));
                usu.setTelefono(res.getString(7));
                resultado.add(usu);
            }
            con.close();
            
        }
        catch(SQLException ex)
        {
            throw new AefilepException("Error al recuperar usuarios", ex);
        }
        return resultado;
    }
    
    public ArrayList<Usuario> obtenerUsuarios() throws AefilepException
    {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
      
        String sql = "select * from usuarios;";
        try
        {   
            Connection con = conec.getConexion();
            PreparedStatement pr = con.prepareStatement(sql);
            ResultSet res = pr.executeQuery();
            
            while(res.next())
            {
                Usuario usu = new Usuario();
                usu.setActivo(res.getBoolean(10));
                usu.setApellido(res.getString(3));
                usu.setBloqueado(res.getBoolean(11));
                usu.setContrasena(res.getString(4));
                usu.setDireccion(res.getString(6));
                usu.setDni(res.getString(8));
                usu.setEsAdmin(res.getBoolean(5));
                usu.setFechaNacimiento(res.getDate(9));
                usu.setIdUsuario(res.getInt(1));
                usu.setMail(res.getString(12));
                usu.setNombre(res.getString(2));
                usu.setNombreUsuario(res.getString(13));
                usu.setTelefono(res.getString(7));
                
                usuarios.add(usu);
            }
            con.close();
            
        }
        catch(Exception ex)
        {
            throw new AefilepException("Error al recuperar usuarios", ex);
        }
        return usuarios;
    }
    
    public void editarUsuario(Usuario usu) throws AefilepException
    {        
        String sql = "update usuarios set nombre=? , apellido=? , direccion=? ,telefono=? , mail=?, "+
                "dni=?,fecha_de_nacimiento=?,bloqueado=?,activo=?,es_admin=?,nombre_usuario=?, "+
                "contrasena=? where id_usuario=?";
        try
        {
            Connection con = conec.getConexion();
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, usu.getNombre());
            pr.setString(2, usu.getApellido());
            pr.setString(3, usu.getDireccion());
            pr.setString(4, usu.getTelefono());
            pr.setString(5, usu.getMail());           
            pr.setString(6,usu.getDni());
            pr.setDate(7, new java.sql.Date(usu.getFechaNacimiento().getTime()));
            pr.setBoolean(8, usu.isBloqueado());
            pr.setBoolean(9, usu.isActivo());
            pr.setBoolean(10, usu.isEsAdmin());
            pr.setString(11, usu.getNombreUsuario());
            pr.setString(12, usu.getContrasena());
            pr.setInt(13,usu.getIdUsuario());
            pr.executeUpdate();
            con.close();
        }
        catch(AefilepException | SQLException ex)
        {
            throw new AefilepException("Error al modificar datos del usuario", ex);
        }
    }
            
    public void registrarUsuario(Usuario usu) throws AefilepException
    {
        PreparedStatement prpstmt;
        String transac1 = "insert into aefilep.usuarios values (?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try
        {
            Connection con = conec.getConexion();
            prpstmt = con.prepareStatement(transac1,Statement.RETURN_GENERATED_KEYS);
            prpstmt.setNull(1,0);
            prpstmt.setString(2, usu.getNombre());
            prpstmt.setString(3, usu.getApellido());
            prpstmt.setString(4, usu.getContrasena());
            prpstmt.setBoolean(5, usu.isEsAdmin());
            prpstmt.setString(6, usu.getDireccion());
            prpstmt.setString(7, usu.getTelefono());
            prpstmt.setString(8, usu.getDni());
            prpstmt.setDate(9, new java.sql.Date(usu.getFechaNacimiento().getTime()));
            prpstmt.setBoolean(10, usu.isActivo());
            prpstmt.setBoolean(11, usu.isBloqueado());
            prpstmt.setString(12, usu.getMail());
            prpstmt.setString(13, usu.getNombreUsuario());
            prpstmt.executeUpdate();
            
            ResultSet rs = prpstmt.getGeneratedKeys();
            
            if(rs.next())
            {
                int id = rs.getInt(1);
                usu.setIdUsuario(id);
            }
            
            con.close();
        }
        catch(Exception ex)
        {
            throw new AefilepException("Error al crear un nuevo usuario", ex);           
        }
    }
    
}
