/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.INA.sysVentas.controller;

import com.INA.sysVentas.Services.IClienteService;
import com.INA.sysVentas.Services.IProductoService;
import com.INA.sysVentas.Services.IVentaService;
import com.INA.sysVentas.domain.Cliente;
import com.INA.sysVentas.domain.DetalleVenta;
import com.INA.sysVentas.domain.Factura;
import com.INA.sysVentas.domain.Producto;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Progra
 */
@Controller
public class VentasController {
    @Autowired
    private IVentaService ventaService;
    @Autowired
    IProductoService productoService;
    @Autowired
    IClienteService clienteService;
    @PostMapping("/guardarFactura")
    public String facturar(@Valid Factura factura,
            Errors errors, Model model){
        List<DetalleVenta> detalles;
        List<Producto> productos = productoService.listar();
        double total = 0.0;
        
        if(errors.hasErrors()){
            detalles = ventaService.listarDetalles(factura.getIdVenta());
            factura.setDetalles(detalles);
            total = detalles.stream().map(
            item -> item.getCantidad() * item.getPrecio())
                    .reduce((subtotal, valor) -> subtotal + valor).orElse(0.0);
            
            model.addAttribute("productos",productos);
            model.addAttribute("factura",factura);
            model.addAttribute("total",total);
            return "facturar";
        }
        
        
        
        factura = ventaService.guardar(factura);
        factura.setCantidad(0);
        factura.setIdCliente(0);
        factura.setDescripcion("");
        
        detalles = ventaService.listarDetalles(factura.getIdVenta());
            factura.setDetalles(detalles);
            total = detalles.stream().map(
            item -> item.getCantidad() * item.getPrecio())
                    .reduce((subtotal, valor) -> subtotal + valor).orElse(0.0);
        String msg = "";
        switch(factura.getRetorno()){
            case 1:
                msg="La factura fue pagada, no se puede actualizar";
                break;
            case 2:
                msg="El stock es insuficiente";
            case 3://Éxito
                msg=null;
                break;
            default:
                msg="Error Inesperado";
                break;
                
        }
        model.addAttribute("productos",productos);
        model.addAttribute("factura",factura);
        model.addAttribute("total",total);
         model.addAttribute("msg",msg);
        return "facturar";
    }
        @GetMapping("/facturar/{idCliente}")
        public String abrirFactura(Cliente cliente,Model model){
            Factura factura = new Factura();
                cliente = clienteService.obtenerCliente(cliente.getIdCliente());
                factura.setIdCliente(cliente.getIdCliente());
                factura.setNombreCliente(cliente.getNombre() + " "
                + cliente.getApellido());
                model.addAttribute("factura",factura);
                List<Producto> lista = productoService.listar();
                model.addAttribute("productos",lista);
                
                return "facturar";
        }
    
    
}
