/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.negocio;
import aplicacion.modelo.datos.UsuarioBD;
import aplicacion.modelo.entidades.Usuario;
import java.util.ArrayList;
import aplicacion.utilidades.AefilepException;

/**
 *
 * @author JP
 */
public class CatalogoDeUsuarios
{
    UsuarioBD usuarios = new UsuarioBD();
    
    public Usuario buscarUsuario(String nombre, String contra) throws AefilepException
    {        
        return usuarios.buscarUsuario(nombre, contra);
    }
           
    public boolean existeUsuario(String nombreUsuario) throws AefilepException
    {        
        return usuarios.existeUsuario(nombreUsuario);
    }
    
    public ArrayList<Usuario> buscarUsuarios(Usuario usu) throws AefilepException
    {
        return usuarios.buscarUsuarios(usu);
    }
    
    public ArrayList<Usuario> obtenerUsuarios() throws AefilepException
    {
        return usuarios.obtenerUsuarios();
    }
    
    public void registrarUsuario(Usuario usu) throws AefilepException
    {
        usuarios.registrarUsuario(usu);   
    }
    
    public void editarUsuario(Usuario usu) throws AefilepException
    {
        usuarios.editarUsuario(usu);
    }
}
