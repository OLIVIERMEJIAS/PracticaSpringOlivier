/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.INA.sysVentas.controller;

import com.INA.sysVentas.Services.IClienteService;
import com.INA.sysVentas.domain.Cliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteController {
    @Autowired
    private IClienteService servicioCliente;
    @GetMapping("/")
    public String inicio(){
        
        return "index";
    }
    @GetMapping("/clientes")
    public String listaClientes(Model model){
        List<Cliente> lista = servicioCliente.listar();
        model.addAttribute("clientes",lista);
        return "listaClientes";
    }
}
