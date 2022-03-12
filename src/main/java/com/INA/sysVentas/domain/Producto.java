/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.INA.sysVentas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author HP
 */
@Data
@Entity
@Table(name="Producto")
public class Producto {
    private static final long serialVersionUID=2L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Long idProducto;
    
    @Column(name="descripcion",unique = true,length = 45)
    @Size(max = 45,message="Estás excediendo el límite de carácteres en la descripción")
    @NotEmpty(message = "La Descripción es Obligatoria")
    private String descripcion;

   @NotNull(message = "El Precio es Obligatorio")
   @Min(value=0,message="El precio debe ser superior o igual a Cero")
   //En caso de que algún producto sea una regalía, 
    //entonces el precio es de cero hacia arriba
    private double precio;

    @Min(value=0,message="Las existencias deben ser superior o igual a Cero")
    @Column(name="existencia")
    @NotNull(message="El número de existencias es Obligatorio")
    private double existencias;
}
