/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.INA.sysVentas.dao;

import com.INA.sysVentas.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Progra
 */
public interface IClienteDao extends JpaRepository<Cliente, Long>{
    
    //Método personalizado Spring DATA
    public Iterable<Cliente> findByNombreContainsOrApellidoContains(String nombre, String apellido);
    
    @Query(value = "Select c from Cliente c Where limite_credito >= ?1")
    public Iterable<Cliente> buscarPorLimites(double limite);
}
