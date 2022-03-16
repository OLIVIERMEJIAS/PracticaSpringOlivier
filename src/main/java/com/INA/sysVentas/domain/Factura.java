
package com.INA.sysVentas.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.validation.constraints.*;
import lombok.Data;

@Data
public class Factura implements Serializable{
    private static final long serialVersionUID=1L;
    
    private long idVenta;
    
    private long idCliente;
    @NotEmpty(message="El Cliente es Obligatorio")
    private String nombreCliente;
    
    private long idProducto;
    @NotEmpty(message="El Producto es Obligatorio")
    private String descripcion;
    
    @NotNull(message="La Cantidad es requerida")
    @Min(value=1,message="La cantidad debe ser mayor a 0")
    private int cantidad;
    
    private String tipo;
    private Calendar fecha;
    
    
    private double precio;
    
    private int retorno;
    
    private List<DetalleVenta> detalles;
}
