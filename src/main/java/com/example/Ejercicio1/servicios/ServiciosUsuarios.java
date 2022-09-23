package com.example.Ejercicio1.servicios;

import com.example.Ejercicio1.entidades.Usuario;
import com.example.Ejercicio1.repositorios.repositorioUsuarios;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ServiciosUsuarios {
    private repositorioUsuarios repositorioUs;

    //constructor

    public ServiciosUsuarios(repositorioUsuarios repositorioUs) {
        this.repositorioUs = repositorioUs;
    }
    public repositorioUsuarios getRepositorioUs() {
        return repositorioUs;
    }
    public void setRepositorioUs(repositorioUsuarios repositorioUs) {
        this.repositorioUs = repositorioUs;
    }


    //Metodos funcionales para el logeo
    private Usuario crearUsuario(Usuario nuevoUsuario) {
        return this.repositorioUs.save(nuevoUsuario);
    }
    private Usuario buscarPorEmail(String email) {
        return this.repositorioUs.findByEmail(email);
    }

    public Usuario getOrCreateUsuario(Map<String,Object> DatosUsuario) {
        String email = (String) DatosUsuario.get("email");
        Usuario usuario = buscarPorEmail(email);
        //Validamos si el usuario exsite o no
        if (usuario == null) {
            String alias = (String) DatosUsuario.get("nickname");
            String imagen = (String) DatosUsuario.get("picture");
            String auth0Id = (String) DatosUsuario.get("sub");
            Usuario nuevoUsuario = new Usuario(email = email, imagen = imagen, auth0Id = auth0Id);
            return crearUsuario(nuevoUsuario);
        }
        System.out.println(usuario.getEmail());
        return usuario;

    }
}



