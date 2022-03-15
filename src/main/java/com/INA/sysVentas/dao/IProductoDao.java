/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.INA.sysVentas.dao;

import com.INA.sysVentas.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HP
 */
public interface IProductoDao 
    extends JpaRepository<Producto, Long>{
    
    //Método personalizado Spring DATA
    public Iterable<Producto> findByDescripcionContains(String descripcion);
    
    @Query(value = "Select p from Producto p Where existencia = ?1")
    public Iterable<Producto> buscarPorExistencias(int existencias);
    
    @Transactional
    @Procedure(name="ELIMINAR_PRODUCTO",outputParameterName = "res")
    public Integer eliminar_producto(@Param("@ID") Long id);
}
