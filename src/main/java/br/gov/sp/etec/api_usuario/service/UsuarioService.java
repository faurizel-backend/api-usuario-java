package br.gov.sp.etec.api_usuario.service;

import br.gov.sp.etec.api_usuario.entity.UsuarioEntity;
import br.gov.sp.etec.api_usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioEntity salvarUsuario(UsuarioEntity usuarioEntity){
        if(validarDados(usuarioEntity)){
           return usuarioRepository.save(usuarioEntity);
        }
      return  null;
    }

    public UsuarioEntity buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public boolean autenticarUsuario(String email, String senha){
        UsuarioEntity user  = buscarPorEmail(email);
        if(user!=null && user.getSenha().equals(senha)){
            return true;
        }
        return false;
    }

    private boolean validarDados(UsuarioEntity usuarioEntity){
        try {
            if(usuarioEntity.getNome() == null || usuarioEntity.getEmail()==null || usuarioEntity.getEmail().isEmpty()){
              throw new RuntimeException("Nome ou Email Invalido");
            }
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
