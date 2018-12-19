/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.comandos;

import aplicacion.modelo.entidades.LineaPedido;
import aplicacion.modelo.entidades.Pedido;
import aplicacion.modelo.entidades.Usuario;
import aplicacion.modelo.negocio.CatalogoDePedidos;
import aplicacion.modelo.negocio.CatalogoDePeliculas;
import aplicacion.utilidades.AefilepException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
public class FinalizarPedidoComando extends Comando
{
    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response)
    {
        Pedido p=(Pedido)request.getSession().getAttribute("pedido");
        //int contAlq=0;

        if(p.getLineas().size()>0)
        {
            if(request.getSession().getAttribute("usuario")==null)
            { 
                request.getSession().setAttribute("usuarioNoLogueado", true);
                return("/login.jsp");
            }
            else 
            {  
                int iLinea=0;
                
                for(LineaPedido lp: p.getLineas())
                {
                    int stock;
                    
                    if(lp.isEsAlquiler())
                        stock=lp.getPelicula().getStockAlquiler();                   
                    else 
                        stock=lp.getPelicula().getStockVenta();

                    if(stock<lp.getCantidad())
                    {   
                        request.getSession().setAttribute("errorStock", iLinea);
                        return "/carro.jsp";  
                    }  
                    iLinea++;
                }
                
                for(LineaPedido lp: p.getLineas())
                {   
                    if(lp.isEsAlquiler())
                    {
                        //contAlq++;
                        lp.getPelicula().setStockAlquiler(lp.getPelicula().getStockAlquiler()-lp.getCantidad());
                    }
                    else
                    {
                        lp.getPelicula().setStockVenta(lp.getPelicula().getStockVenta()-lp.getCantidad());
                    }
                    
                    for(LineaPedido lp2: p.getLineas())
                    {
                        if(lp.getPelicula().getIdPelicula()==lp2.getPelicula().getIdPelicula())
                        {
                            lp2.getPelicula().setStockAlquiler(lp.getPelicula().getStockAlquiler());
                            lp2.getPelicula().setStockVenta(lp.getPelicula().getStockVenta());
                        }
                    }
                   
                    CatalogoDePeliculas CdPeli=new CatalogoDePeliculas();
                    try
                    {
                        CdPeli.actualizarStock(lp.getPelicula());
                    }
                    catch(AefilepException ex)
                    {
                       request.setAttribute("ex", ex.getMessage());
                       return "/carro.jsp";
                    }
                }
                 
                Usuario u= (Usuario)request.getSession().getAttribute("usuario");
                CatalogoDePedidos CdP= new CatalogoDePedidos();
                p.setUsuario(u);
                p.setDias((Integer)request.getSession().getAttribute("cantidadDias"));
                //if(contAlq>0)
                    p.setEstado("Pendiente");
                //else
                //    p.setEstado("Cerrado");
                
                try 
                {
                    CdP.registrarPedido(p);
                } 
                catch (AefilepException ex) 
                {
                    request.setAttribute("ex", ex.getMessage());
                    return "/carro.jsp";
                }

                request.getSession().setAttribute("exitoPedido", true);
                Pedido pedido = new Pedido();
                request.getSession().setAttribute("pedido", pedido);
            }
        }
        else
        {
            request.getSession().setAttribute("pedidoVacio", true);
        }
        
        return "/carro.jsp";
    }
}
    

