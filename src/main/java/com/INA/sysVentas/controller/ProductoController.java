/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.INA.sysVentas.controller;
import com.INA.sysVentas.Services.IClienteService;
import com.INA.sysVentas.Services.IProductoService;
import com.INA.sysVentas.domain.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class ProductoController {
    @Autowired
    private IProductoService servicioProducto;

    @GetMapping("/productos")
    public String listar(Model model){
        List<Producto> lista = servicioProducto.listar();
        model.addAttribute("productos",lista);
        return "listaProductos";
    }
}
