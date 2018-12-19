/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.comandos;

import aplicacion.modelo.entidades.Pedido;
import aplicacion.modelo.negocio.CatalogoDePedidos;
import aplicacion.utilidades.AefilepException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ligia
 */
public class EnviosComando extends Comando
{
    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) 
    {
        CatalogoDePedidos cdp= new CatalogoDePedidos();
        
        ArrayList<Pedido> pendientes= null;
        
        try
        {
            pendientes= cdp.obtenerPedidosPendientes();
        }
        catch(AefilepException ex)
        {
            request.setAttribute("ex",ex.getMessage());
            return("/envios.jsp");
        }
        
        request.getSession().setAttribute("pendientes", pendientes);

        return "/envios.jsp" ;
    }
}
