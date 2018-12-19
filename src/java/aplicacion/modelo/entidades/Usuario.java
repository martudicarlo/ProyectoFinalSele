/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author JP
 */
public class Usuario implements Serializable
{
    private int idUsuario;
    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String contrasena;
    private Date fechaNacimiento;
    private String mail;
    private String dni;
    private boolean activo; 
    private boolean bloqueado;
    private boolean esAdmin;
    private ArrayList<Pedido> pedidos;
    
    public Usuario()
    {
        pedidos = new ArrayList<Pedido>();
    }
    
    /**
     * @return the idUsuario
     */
    public int getIdUsuario()
    {
        return idUsuario;
    }

    public ArrayList<Pedido> getPedidos() 
    {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos)
    {
        this.pedidos = pedidos;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario)
    {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() 
    {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) 
    {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the nombre
     */
    public String getNombre() 
    {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido()
    {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    /**
     * @return the direccion
     */
    public String getDireccion()
    {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() 
    {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) 
    {
        this.telefono = telefono;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena()
    {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena)
    {
        this.contrasena = contrasena;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento()
    {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento)
    {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the mail
     */
    public String getMail() 
    {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) 
    {
        this.mail = mail;
    }

    /**
     * @return the dni
     */
    public String getDni() 
    {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni)
    {
        this.dni = dni;       
    }

    /**
     * @return the activo
     */
    public boolean isActivo()
    {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo)
    {
        this.activo = activo;
    }

    /**
     * @return the bloqueado
     */
    public boolean isBloqueado()
    {
        return bloqueado;
    }

    /**
     * @param bloqueado the bloqueado to set
     */
    public void setBloqueado(boolean bloqueado)
    {
        this.bloqueado = bloqueado;
    }

    /**
     * @return the esAdmin
     */
    public boolean isEsAdmin()
    {
        return esAdmin;
    }

    /**
     * @param esAdmin the esAdmin to set
     */
    public void setEsAdmin(boolean esAdmin) 
    {
        this.esAdmin = esAdmin;
    }
}
