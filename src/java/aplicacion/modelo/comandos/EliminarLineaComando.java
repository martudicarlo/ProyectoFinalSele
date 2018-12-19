/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.comandos;

import aplicacion.modelo.entidades.LineaPedido;
import aplicacion.modelo.entidades.Pedido;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
public class EliminarLineaComando extends Comando
{
    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response)
    {
        Pedido ped= (Pedido)request.getSession().getAttribute("pedido");
        boolean alquiler= Boolean.valueOf(request.getParameter("tipoLinea"));
        Integer idPelicula= Integer.parseInt(request.getParameter("idPelicula"));
        LineaPedido L = null;
        
        for(LineaPedido lp: ped.getLineas())
        {
            if(lp.getPelicula().getIdPelicula()==idPelicula && lp.isEsAlquiler()==alquiler)
            {  
                L=lp;
                break;
            }
        }
        
        ped.getLineas().remove(L);
      
        request.getSession().setAttribute("pedido", ped);
        return("/carro.jsp");
    }
}
