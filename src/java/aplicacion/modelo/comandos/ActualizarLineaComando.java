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
 * @author JP
 */
public class ActualizarLineaComando extends Comando
{
    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response)
    {    
        boolean tipoL = Boolean.parseBoolean(request.getParameter("tipoLinea"));
        int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
        String cantStr=request.getParameter("cantidad");
        
        if(cantStr.matches("[1-9][0-9]*"))
        {
            int cant = Integer.parseInt(cantStr);
            Pedido pedido = (Pedido)request.getSession().getAttribute("pedido");
            for(LineaPedido lp: pedido.getLineas())
            {
                if(lp.getPelicula().getIdPelicula()==idPelicula && (lp.isEsAlquiler()==tipoL))
                    lp.setCantidad(cant);
            }
        }
        else
            request.getSession().setAttribute("cantidadInvalida", true);

        return("/carro.jsp");
    }  
}
