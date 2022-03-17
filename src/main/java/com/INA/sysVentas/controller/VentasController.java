/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.INA.sysVentas.controller;

import com.INA.sysVentas.Services.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Progra
 */
@Controller
public class VentasController {
    @Autowired
    private IVentaService ventaService;
    
    
}
