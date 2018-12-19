/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.comandos;

import aplicacion.modelo.entidades.Usuario;
import aplicacion.modelo.negocio.CatalogoDeUsuarios;
import aplicacion.utilidades.AefilepException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JP
 */
public class AgregarUsuarioComando extends Comando
{
    CatalogoDeUsuarios cDeUsu;
    
    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response)
    {
        cDeUsu = new CatalogoDeUsuarios();
        boolean existeUsuario = true;
        try
        {
            existeUsuario = cDeUsu.existeUsuario((String)request.getParameter("Usu"));
        }
        catch(AefilepException ex)
        {
            request.setAttribute("ex", ex.getMessage());
            return"/ABMUsuarios.jsp";
        }
              
        Usuario usNuevo = new Usuario();
        SimpleDateFormat formato =  new SimpleDateFormat("yyyy-MM-dd");       

        try
        {
            Date fecha = formato.parse(request.getParameter("fechaNacimiento"));              
            usNuevo.setFechaNacimiento(new java.sql.Date(fecha.getTime()));
        }
        catch(ParseException e)
        {
            request.setAttribute("ex", "Ha ocurrido un error");
        }
        
        usNuevo.setNombre((String)request.getParameter("Nombre"));
        usNuevo.setApellido((String)request.getParameter("Apellido"));
        usNuevo.setContrasena((String)request.getParameter("Contra1"));
        usNuevo.setDireccion((String)request.getParameter("Calle"));
        usNuevo.setTelefono((String)request.getParameter("Tel"));
        usNuevo.setDni((String)request.getParameter("Dni")); 
        usNuevo.setMail((String)request.getParameter("Email"));
        usNuevo.setNombreUsuario((String)request.getParameter("Usu"));
        Boolean esAdmin = (request.getParameter("Admin")!=null);
        Boolean esActivo = (request.getParameter("Activo")!=null);
        Boolean esBloq = (request.getParameter("Bloqueado")!=null);
        usNuevo.setActivo(esActivo);
        usNuevo.setEsAdmin(esAdmin);
        usNuevo.setBloqueado(esBloq);
        ArrayList<Usuario> usuarios;
             
        if(!existeUsuario)
        {   
            try
            {
                cDeUsu.registrarUsuario(usNuevo);
                usuarios = cDeUsu.obtenerUsuarios();         
            }
            catch(AefilepException ex)
            {
                request.setAttribute("ex", ex.getMessage());
                return"/ABMUsuarios.jsp";
            }
            
            request.getSession().setAttribute("ListaUsuarios", usuarios);
            request.setAttribute("ExitoUsu", true); 
        }
        else
        {
            request.setAttribute("usuarioPorAgregar", usNuevo);
            request.setAttribute("ExitoUsu", false);    
        }
        request.getSession().setAttribute("Scroll",true);
        return "/ABMUsuarios.jsp";
    }  
}
