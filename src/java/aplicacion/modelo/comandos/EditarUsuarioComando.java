/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.comandos;

import aplicacion.modelo.entidades.Usuario;
import aplicacion.modelo.negocio.CatalogoDeUsuarios;
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
public class EditarUsuarioComando extends Comando
{
    CatalogoDeUsuarios cDeUsu;
    
    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response)
    {
        cDeUsu = new CatalogoDeUsuarios();
        Usuario usEditado = (Usuario)request.getSession().getAttribute("UsuarioEdit");
        SimpleDateFormat formato =  new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        
        try
        {
            fecha = formato.parse(request.getParameter("fechaNacimiento"));              
            usEditado.setFechaNacimiento(new java.sql.Date(fecha.getTime()));
        }
        catch(ParseException e)
        {
            request.setAttribute("ex", "Ha ocurrido un error");
        }
        
        usEditado.setIdUsuario( Integer.parseInt(request.getParameter("ID")));
        usEditado.setNombre((String)request.getParameter("Nombre"));
        usEditado.setApellido((String)request.getParameter("Apellido"));
        usEditado.setDireccion((String)request.getParameter("Calle"));
        usEditado.setTelefono((String)request.getParameter("Tel"));
        usEditado.setDni((String)request.getParameter("Dni")); 
        usEditado.setMail((String)request.getParameter("Email"));
        usEditado.setNombreUsuario((String)request.getParameter("Usu"));
        Boolean esAdmin = (request.getParameter("Admin")!=null);
        Boolean esActivo = (request.getParameter("Activo")!=null);
        Boolean esBloq = (request.getParameter("Bloqueado")!=null);
        usEditado.setActivo(esActivo);
        usEditado.setEsAdmin(esAdmin);
        usEditado.setBloqueado(esBloq);
        ArrayList<Usuario> usuarios=null;
        
        try
        {
            cDeUsu.editarUsuario(usEditado);
            usuarios = cDeUsu.obtenerUsuarios();       
        }
        catch(Exception ex)
        {
            request.setAttribute("ex",ex.getMessage());
            return"/ABMUsuarios.jsp";
        }
        
        request.getSession().setAttribute("ListaUsuarios", usuarios);
        request.getSession().setAttribute("UsuarioEdit", usEditado);
        request.getSession().setAttribute("Scroll",true);
        request.setAttribute("ExitoUsu", true);
        
        return"/ABMUsuarios.jsp";
    }  
}
