package aplicacion.modelo.comandos;

import java.util.HashMap;
/**
 * La clase contiene los metodos necesarios para buscar el comando a ejecutar
 * en la aplicacion, cuenta con un hash map el cual contiene los pares
 * clave-valor para la obtencion de la clase correspondiente
 * @author JP
 */
public class FactoriaDeComandos
{
    public static FactoriaDeComandos instancia; 
    private final HashMap<String, Class<?>> mapa;
    
    //constructor singleton
    private FactoriaDeComandos()
    { 
        mapa=new HashMap<>();
        mapa.put("LogInComando",LogInComando.class);
        mapa.put("CuentaComando",CuentaComando.class);
        mapa.put("RegistroComando", RegistroComando.class);
        mapa.put("PeliculasComando", PeliculasComando.class);
        mapa.put("AdminPeliculasComando", AdminPeliculasComando.class);
        mapa.put("SeleccionarPeliculaComando", SeleccionarPeliculaComando.class);
        mapa.put("EditarPeliculaComando", EditarPeliculaComando.class);
        mapa.put("AgregarPeliculaComando", AgregarPeliculaComando.class);
        mapa.put("LogOutComando", LogOutComando.class);
        mapa.put("AgregarLineaComando", AgregarLineaComando.class);
        mapa.put("ActualizarLineaComando",ActualizarLineaComando.class);
        mapa.put("AdminUsuariosComando",AdminUsuariosComando.class);
        mapa.put("SeleccionarUsuarioComando", SeleccionarUsuarioComando.class);
        mapa.put("FinalizarPedidoComando", FinalizarPedidoComando.class);
        mapa.put("EditarUsuarioComando", EditarUsuarioComando.class);
        mapa.put("AgregarUsuarioComando", AgregarUsuarioComando.class);
        mapa.put("SetearFechaPedidoComando", SetearFechaPedidoComando.class);
        mapa.put("EliminarLineaComando", EliminarLineaComando.class);
        mapa.put("ObtenerPeliculaComando", ObtenerPeliculaComando.class);   
        mapa.put("BuscarUsuarioComando", BuscarUsuarioComando.class);
        mapa.put("VerPedidosComando", VerPedidosComando.class);
        mapa.put("RegistrarDevolucionComando",RegistrarDevolucionComando.class);
        mapa.put("EnviarMensajeComando", EnviarMensajeComando.class);
        mapa.put("MisPedidosComando", MisPedidosComando.class);
        mapa.put("EnviosComando", EnviosComando.class);
        mapa.put("RegistrarEnvioComando", RegistrarEnvioComando.class);
        mapa.put("InicioComando", InicioComando.class);
        mapa.put("RedireccionarComando", RedireccionarComando.class);      
    }
    /**
     * Metodo de clase devuelve la instancia de FactoriaDeComandos
     * @return Devuelve la instancia de FactoriaDeComandos
     */
    public static FactoriaDeComandos getInstancia()
    {
        if( instancia ==null)
            instancia=new FactoriaDeComandos();     
        return instancia;
    }
    
    /**
     * Llega el nombre del comando por parametro, busca el correspondiente en
     * el mapa e instancia la clase que corresponde, devuelve una instancia
     * de comando
     * @param nom {@code String} nombre del comando
     * @return Si la clase es instanciada devuelve un {@code Comando} si ocurre 
     * una excepcion devuelve {@code "null"}
     */
    public Comando buscarComando(String nom) throws IllegalAccessException, InstantiationException
    { 
        Comando c=null;
        try
        {
            c =(Comando)mapa.get(nom).newInstance();
        }
        catch(InstantiationException | IllegalAccessException ex)
        {
            throw ex;
        }     
        return c;       
    }   
}
