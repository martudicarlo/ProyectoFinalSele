/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.negocio;

import aplicacion.modelo.entidades.Genero;
import aplicacion.modelo.datos.GeneroBD;
import aplicacion.utilidades.AefilepException;
import java.util.ArrayList;

/**
 *
 * @author Alumno
 */

public class CatalogoDeGeneros 
{
    GeneroBD generosBD = new GeneroBD();  
    
    public ArrayList<Genero> obtenerGeneros() throws AefilepException
    {
        return generosBD.obtenerGeneros();
    }    
}
