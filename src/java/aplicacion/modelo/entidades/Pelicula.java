/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.entidades;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.Part;

/**
 *
 * @author JP
 */
public class Pelicula 
{
    private int idPelicula;
    private String sinopsis;
    private String nombre;
    private int duracion;
    private String formato;
    private String reparto;
    private String urlTrailer;
    private int stockVenta;
    private int stockAlquiler;
    private Date fechaCarga;
    private boolean activo;
    private float precioVenta;
    private float precioAlquiler;
    private int anio;
    private boolean estreno;
    private ArrayList<Genero> generos;
    private InputStream imagen;
    
    public Pelicula()
    {
        this.generos = new ArrayList<Genero>();
    }
    
    public InputStream getImagen() 
    {
        return imagen;
    }
   
    public void setImagen(InputStream imagen)
    {
        this.imagen = imagen;
    }
   
    public ArrayList<Genero> getGeneros()
    {
        return generos;
    }

    public void setGeneros(ArrayList<Genero> generos)
    {
        this.generos = generos;
    }
    
    public boolean contieneGenero(Genero gen)
    {
        for(Genero g:generos)
        {
            if(g.getIdGenero()==gen.getIdGenero())
                return true;
        }
        return false;
    }
    
    public void agregarGenero(Genero genero)
    {
        this.generos.add(genero);
    }
    
    //private String imagen;

    /**
     * @return the idPelicula
     */
    public int getIdPelicula()
    {
        return idPelicula;
    }
    
    public boolean isEstreno()
    {
       Date fechaHoy= new Date();
       long mshoy = fechaHoy.getTime();
       long mscarga = fechaCarga.getTime();
       long dif = mshoy - mscarga;
       long dias = dif / (1000 * 60 * 60 * 24);
       return(dias<=7);
    }
    
    public void setEstreno(boolean e)
    {
        estreno=e;
    }
    /**
     * @param idPelicula the idPelicula to set
     */
    public void setIdPelicula(int idPelicula)
    {
        this.idPelicula = idPelicula;
    }
    
    public int getAnio()
    {
        return anio;
    }
    
    public void setAnio(int a)
    {
        anio=a;
    }

    /**
     * @return the sinopsis
     */
    public String getSinopsis() 
    {
        return sinopsis;
    }
     
    public String getSinopsis(int cant)
    {        
        if(sinopsis.length()<150)
            return sinopsis;         
        return sinopsis.substring(0, cant);      
    }

    /**
     * @param sinopsis the sinopsis to set
     */
    public void setSinopsis(String sinopsis) 
    {
        this.sinopsis = sinopsis;
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
     * @return the duracion
     */
    public int getDuracion()
    {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(int duracion)
    {
        this.duracion = duracion;
    }

    /**
     * @return the formato
     */
    public String getFormato()
    {
        return formato;
    }

    /**
     * @param formato the formato to set
     */
    public void setFormato(String formato) 
    {
        this.formato = formato;
    }
    
    /**
     * @return the reparto
     */
    public String getReparto()
    {
        return reparto;
    }

    /**
     * @param reparto the reparto to set
     */
    public void setReparto(String reparto)
    {
        this.reparto = reparto;
    }

    /**
     * @return the urlTrailer
     */
    public String getUrlTrailer()
    {
        return urlTrailer;
    }

    /**
     * @param urlTrailer the urlTrailer to set
     */
    public void setUrlTrailer(String urlTrailer)
    {
        this.urlTrailer = urlTrailer;
    }

    /**
     * @return the stockVenta
     */
    public int getStockVenta() 
    {
        return stockVenta;
    }

    /**
     * @param stockVenta the stockVenta to set
     */
    public void setStockVenta(int stockVenta) 
    {
        this.stockVenta = stockVenta;
    }

    /**
     * @return the stockAlquiler
     */
    public int getStockAlquiler()
    {
        return stockAlquiler;
    }

    /**
     * @param stockAlquiler the stockAlquiler to set
     */
    public void setStockAlquiler(int stockAlquiler)
    {
        this.stockAlquiler = stockAlquiler;
    }

    /**
     * @return the fechaCarga
     */
    public Date getFechaCarga()
    {
        return fechaCarga;
    }

    /**
     * @param fechaCarga the fechaCarga to set
     */
    public void setFechaCarga(Date fechaCarga)
    {
        this.fechaCarga = fechaCarga;
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
     * @return the precioVenta
     */
    public float getPrecioVenta()
    {
        return precioVenta;
    }

    /**
     * @param precioVenta the precioVenta to set
     */
    public void setPrecioVenta(float precioVenta)
    {
    
        this.precioVenta = precioVenta;
    }

    /**
     * @return the precioAlquiler
     */
    public float getPrecioAlquiler()
    {
        return precioAlquiler;
    }

    /**
     * @param precioAlquiler the precioAlquiler to set
     */
    public void setPrecioAlquiler(float precioAlquiler) 
    {
        this.precioAlquiler = precioAlquiler;
    }
}
