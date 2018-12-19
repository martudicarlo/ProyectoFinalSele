/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.comandos;

import aplicacion.modelo.entidades.Usuario;
import aplicacion.modelo.negocio.CatalogoDeUsuarios;
import aplicacion.utilidades.AefilepException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JP
 */
public class BuscarUsuarioComando extends Comando
{
    CatalogoDeUsuarios cDeU;
            
    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response)
    {
        Usuario buscado = new Usuario();
        cDeU = new CatalogoDeUsuarios();
        int id;
        
        if( !request.getParameter("ID").equals("") )
        {
            id = Integer.parseInt(request.getParameter("ID"));
        }
        else
        {
            id=0;
        }
        
        buscado.setIdUsuario(id);
        buscado.setNombre(request.getParameter("Nombre"));
        buscado.setApellido(request.getParameter("Apellido"));
        buscado.setDni(request.getParameter("Dni"));
        
        try
        {
            ArrayList<Usuario> encontrados = cDeU.buscarUsuarios(buscado);
            request.getSession().setAttribute("ListaEncontrados", encontrados);
            request.getSession().setAttribute("ListaPendientes", null);      
        }
        catch(AefilepException ex)
        {
            request.setAttribute("ex",ex.getMessage());
            return "/Devoluciones.jsp";
        }
       
        return "/Devoluciones.jsp";
    }   
}
