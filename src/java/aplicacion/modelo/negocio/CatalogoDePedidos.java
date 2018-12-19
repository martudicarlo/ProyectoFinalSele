/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.negocio;

import aplicacion.modelo.datos.PedidoBD;
import aplicacion.modelo.entidades.Pedido;
import aplicacion.utilidades.AefilepException;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class CatalogoDePedidos 
{
    PedidoBD pedidos=new PedidoBD();
    
    public void registrarPedido(Pedido p) throws AefilepException
    {
        pedidos.registrarPedido(p);    
    }
    
    public void cerrarPedido(Pedido p) throws AefilepException
    {
        pedidos.cerrarPedido(p);
    }
    
    public ArrayList<Pedido> obtenerPedidosEnviados(int idUsu) throws AefilepException
    {
        return pedidos.obtenerPedidosEnviados(idUsu);
    }
    
    public ArrayList<Pedido> obtenerPedidos(int idUsu) throws AefilepException
    {
        return pedidos.obtenerPedidos(idUsu);
    }
      public ArrayList<Pedido> obtenerPedidosPendientes() throws AefilepException
    {
        return pedidos.obtenerPedidosPendientes();
    }
     public void registrarEnvio(Pedido p) throws AefilepException
    {
        pedidos.registrarEnvio(p);
    }     
}
