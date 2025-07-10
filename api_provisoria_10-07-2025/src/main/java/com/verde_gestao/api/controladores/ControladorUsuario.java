package com.verde_gestao.api.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorUsuario {

    @GetMapping("/getUsuarios")
    public void getUsuarios() {
    }

}