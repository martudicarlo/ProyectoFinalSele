/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.utilidades;

/**
 *
 * @author User
 */
public class AefilepException extends Exception
{
    public AefilepException ()
    {
       super ();
    }
   
    public AefilepException (String msj, Throwable cau)
    {
       super (msj,cau);
    }
    
    public AefilepException (Throwable cau)
    {
       super (cau);
    }
}
