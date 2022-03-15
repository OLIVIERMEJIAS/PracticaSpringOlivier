/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.INA.sysVentas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Progra
 */
@Controller
public class AppController {
     @GetMapping("/")
    public String inicio(){
        
        return "index";
    }
}
