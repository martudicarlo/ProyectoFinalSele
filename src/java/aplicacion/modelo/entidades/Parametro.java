/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.entidades;

import java.util.Date;

/**
 *
 * @author marti_000
 */
public class Parametro 
{
    private Date fechaActualizacion;
    private String razonSocial;
    private float precioAlquiler;
    private float precioAlquilerEstreno;
    private String direccion;
    private String telefono;
    private String mail;
    private float recargoDiario;

    public Date getFechaActualizacion()
    {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion)
    {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getRazonSocial()
    {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial)
    {
        this.razonSocial = razonSocial;
    }

    public float getPrecioAlquiler()
    {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(float precioAlquiler)
    {
        this.precioAlquiler = precioAlquiler;
    }

    public float getPrecioAlquilerEstreno() 
    {
        return precioAlquilerEstreno;
    }

    public void setPrecioAlquilerEstreno(float precioAlquilerEstreno)
    {
        this.precioAlquilerEstreno = precioAlquilerEstreno;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public void setDireccion(String direccion) 
    {
        this.direccion = direccion;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail) 
    {
        this.mail = mail;
    }

    public float getRecargoDiario()
    {
        return recargoDiario;
    }

    public void setRecargoDiario(float recargoDiario)
    {
        this.recargoDiario = recargoDiario;
    }  
}
