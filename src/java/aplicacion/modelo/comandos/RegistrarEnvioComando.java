/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.comandos;

import aplicacion.modelo.entidades.LineaPedido;
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
public class RegistrarEnvioComando extends Comando 
{
    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response)
    {        
        CatalogoDePedidos cdp= new CatalogoDePedidos();
        int idPed = Integer.parseInt(request.getParameter("idPedido"));
        ArrayList<Pedido> pendientes = (ArrayList)request.getSession().getAttribute("pendientes");
        Pedido pedAEnviar = new Pedido();
        int contAlq=0;
        
        for(Pedido p:pendientes)
        {
            if(p.getIdPedido()==idPed)
                pedAEnviar=p;      
        } 
        
        for(LineaPedido lp: pedAEnviar.getLineas())
        {
            if(lp.isEsAlquiler())
                contAlq++;
        }
        
        if(contAlq>0)
            pedAEnviar.setEstado("Enviado");
        else
            pedAEnviar.setEstado("Cerrado");
        
        try
        {
            cdp.registrarEnvio(pedAEnviar);
        }
        catch(AefilepException ex)
        {
            request.setAttribute("ex", ex.getMessage());
            return "/envios.jsp";     
        }

        try
        {
            pendientes=cdp.obtenerPedidosPendientes();
        }
        catch(AefilepException ex)
        {
            request.setAttribute("ex", ex.getMessage());
            return "/envios.jsp";    
        }
        
        request.getSession().setAttribute("pendientes",pendientes);
        request.setAttribute("ExitoEnvio", true);
        
        return "/envios.jsp";
    }   
}
