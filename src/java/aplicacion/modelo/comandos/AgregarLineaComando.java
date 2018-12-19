/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.comandos;
import aplicacion.modelo.entidades.LineaPedido;
import aplicacion.modelo.entidades.Pedido;
import aplicacion.modelo.entidades.Pelicula;
import aplicacion.modelo.negocio.CatalogoDePeliculas;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JP
 */
public class AgregarLineaComando extends Comando
{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response)
    {
        int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
        boolean alquiler = request.getParameter("tipoLinea").equals("Alquilar");
        Pedido pedido = (Pedido)request.getSession().getAttribute("pedido");
        int lineaExiste=0;
        
        for(LineaPedido lp: pedido.getLineas())
        {
            if(lp.getPelicula().getIdPelicula()==idPelicula && (lp.isEsAlquiler()==alquiler))
            {
                if(alquiler==false)//Porque solo la compra admite un aumento de cantidad
                {
                   lp.setCantidad(lp.getCantidad()+1);
                   request.getSession().setAttribute("exitoPeliculaAgregada", true);
                }
                
                lineaExiste=1;
            }        
        }
        
        if(lineaExiste==0)
        {
            CatalogoDePeliculas cdp = new CatalogoDePeliculas();
            LineaPedido linea = new LineaPedido();
            linea.setEsAlquiler(alquiler);
            try
            {
                Pelicula peli = cdp.obtenerPelicula(idPelicula);
                linea.setCantidad(1);
                linea.setPelicula(peli);
                pedido.setLinea(linea);
                request.getSession().setAttribute("exitoPeliculaAgregada", true);
            }
            catch(Exception ex)
            {
                request.getSession().setAttribute("exitoPeliculaAgregada", false);
            }   
        }
        
        request.getSession().setAttribute("pedido",pedido);
        
        if(request.getParameter("provieneDePelicula")!=null)
           return "/pelicula.jsp";
        
        return "/cartelera.jsp";
    }
    
}
