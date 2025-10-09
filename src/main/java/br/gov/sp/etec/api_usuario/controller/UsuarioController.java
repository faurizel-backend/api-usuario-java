package br.gov.sp.etec.api_usuario.controller;

import br.gov.sp.etec.api_usuario.entity.UsuarioEntity;
import br.gov.sp.etec.api_usuario.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public UsuarioEntity salvar(@RequestBody UsuarioEntity usuario) {
        log.debug("Cadatrando.. ", usuario );
        return  service.salvarUsuario(usuario);
    }

    @GetMapping("autenticar")
    public boolean autenticar(@RequestParam String email, @RequestParam String senha) {
        log.info("Usuario logando com " + email);
        Boolean resultado = service.autenticarUsuario(email, senha);
        if (resultado) {
            log.info("Autenticado com " + email + " sucesso");
        }
        return  service.autenticarUsuario(email, senha);
    }
}
