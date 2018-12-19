/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.comandos;

import aplicacion.modelo.entidades.Usuario;
import aplicacion.modelo.negocio.CatalogoDeUsuarios;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JP
 */
public class SeleccionarUsuarioComando extends Comando
{
    CatalogoDeUsuarios CdeUsu;
    
    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response)
    {
        int idUsuEdit = Integer.parseInt( request.getParameter("idUsuEdit"));
        if(idUsuEdit!=0)
        {
            ArrayList<Usuario> usuarios = (ArrayList<Usuario>)request.getSession().getAttribute("ListaUsuarios");
            Usuario usuarioEdit=null;
            for(Usuario u:usuarios)
            {
                if(idUsuEdit==u.getIdUsuario())
                {
                    usuarioEdit=u;
                }
            }
            request.getSession().setAttribute("UsuarioEdit", usuarioEdit);
        }
        else
        {
            request.getSession().setAttribute("UsuarioEdit", null);
        }
        request.getSession().setAttribute("Scroll",true);
        
        return "/ABMUsuarios.jsp";
    }
}
