/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.comandos;

import aplicacion.modelo.entidades.Pedido;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
public class SetearFechaPedidoComando extends Comando
{
    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response)
    {
        String diasStr=request.getParameter("cantDias");
        if(diasStr.matches("[1-9][0-9]*"))
        {
            Integer dias=Integer.parseInt(diasStr);
            
            if(dias<=15 && dias>0)
            {
                Pedido p= (Pedido) request.getSession().getAttribute("pedido");
                p.setFechaDesde(p.getFechaRealizacion());
                p.setFechaHasta(devolverFecha(dias, p.getFechaDesde()));
                request.getSession().setAttribute("cantidadDias", dias);
            }
            else
                request.getSession().setAttribute("errorDias",true);  
        }
        else
            request.getSession().setAttribute("errorDias",true);  
        
        return "/carro.jsp";
    }
    
    
    private Date devolverFecha(int dias, Date fecha)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  	
      
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }
}
