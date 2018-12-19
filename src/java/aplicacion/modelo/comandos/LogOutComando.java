/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.comandos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

/**
 *
 * @author JP
 */
public class LogOutComando extends Comando
{
    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response)
    {
        request.getSession().invalidate();
        Cookie[] cookies = request.getCookies();
        
        for(Cookie c:cookies)
        {
            if(c.getName().equals("nomUsuarioAefilep"))
            {
                c.setValue(null);
                c.setMaxAge(-1);
                c.setPath("/");
                response.addCookie(c);
            }
            if(c.getName().equals("contraAefilep"))
            {
                c.setValue(null);
                c.setMaxAge(-1);
                c.setPath("/");
                response.addCookie(c);
            }
        }
        
        return "/index.jsp";
    }
}
