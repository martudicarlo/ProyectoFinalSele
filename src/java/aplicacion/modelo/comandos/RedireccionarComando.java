/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.comandos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ligia
 */
public class RedireccionarComando extends Comando
{
    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) 
    {
        return request.getParameter("destino");
    }  
}
