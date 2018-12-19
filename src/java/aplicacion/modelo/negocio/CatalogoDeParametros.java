/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.negocio;
import aplicacion.modelo.datos.ParametroBD;
import aplicacion.modelo.entidades.Parametro;
import aplicacion.utilidades.AefilepException;

/**
 *
 * @author JP
 */
public class CatalogoDeParametros
{
    ParametroBD parametros = new ParametroBD();
    
    public Parametro obtenerParametros() throws AefilepException
    {
        return parametros.obtenerParametros();
    }       
}
