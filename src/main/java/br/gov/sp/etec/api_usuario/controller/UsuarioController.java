package br.gov.sp.etec.api_usuario.controller;

import br.gov.sp.etec.api_usuario.entity.UsuarioEntity;
import br.gov.sp.etec.api_usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public UsuarioEntity salvar(@RequestBody UsuarioEntity usuario) {
        return  service.salvarUsuario(usuario);
    }

    @GetMapping("autenticar")
    public boolean autenticar(@RequestParam String email, @RequestParam String senha) {
        return  service.autenticarUsuario(email, senha);
    }
}
