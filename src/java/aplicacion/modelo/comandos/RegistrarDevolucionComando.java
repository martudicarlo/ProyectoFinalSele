/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.comandos;

import aplicacion.modelo.entidades.LineaPedido;
import aplicacion.modelo.entidades.Pedido;
import aplicacion.modelo.negocio.CatalogoDePedidos;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JP
 */
public class RegistrarDevolucionComando extends Comando
{
    CatalogoDePedidos cDeP;
    
    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response)
    {
        cDeP = new CatalogoDePedidos();
        int idPed = Integer.parseInt(request.getParameter("idPedido"));
        ArrayList<Pedido> enviados = (ArrayList)request.getSession().getAttribute("ListaEnviados");
        Pedido pedACerrar = new Pedido();
        for(Pedido p:enviados)
        {
            if(p.getIdPedido()==idPed)
            {
                pedACerrar=p;
            }
        }
        
        pedACerrar.setFechaDevolucion(new Date());
        pedACerrar.setEstado("Cerrado");
        for(LineaPedido lp:pedACerrar.getLineas())
        {
            if(lp.isEsAlquiler())
            {
                lp.getPelicula().setStockAlquiler((lp.getPelicula().getStockAlquiler()+1));
            }
        }
        
        try 
        {
            cDeP.cerrarPedido(pedACerrar);
        } 
        catch (Exception ex) 
        {
            request.setAttribute("ex",ex.getMessage());
            return "/Devoluciones.jsp";
        }
        
        request.getSession().setAttribute("ExitoCierre", 1);
        request.getSession().setAttribute("ListaEnviados", null);
        
        return "/Devoluciones.jsp";
    }
    
}
