package com.banana.bananawhatsapp.config;

import com.banana.bananawhatsapp.persistencia.IMensajeRepository;
import com.banana.bananawhatsapp.persistencia.IUsuarioRepository;
import com.banana.bananawhatsapp.persistencia.MensajeDBRepository;
import com.banana.bananawhatsapp.persistencia.UsuarioDBRepository;
import com.banana.bananawhatsapp.servicios.IServicioUsuarios;
import com.banana.bananawhatsapp.servicios.ServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
public class ServicesConfig {
    @Value("${db_url}")
    String connUrl;

    //En los servicios, no tenemos un perfil definido y siempre ejecutaran default, por tanto tenemos que crear un bean de repo de usuarios para el perfil default
    @Bean
    @Profile("default")
    /*public IUsuarioRepository createIUsuarioRepository() {*/
    IUsuarioRepository createUsuarioRepository() {
        System.out.println("usando persistencia.UsuarioDBRepository (default)");
        UsuarioDBRepository repo = new UsuarioDBRepository();
        repo.setDb_url(connUrl);
        return repo;
    }

    //En los servicios, no tenemos un perfil definido y siempre ejecutaran default, por tanto tenemos que crear un bean de repo de mensajes para el perfil default
    @Bean
    @Profile("default")
    /*public IMensajeRepository createIMensajeRepository() {*/
    IMensajeRepository createMensajeRepository() {
        System.out.println("usando persistencia.MensajeDBRepository (default)");
        MensajeDBRepository repoMen = new MensajeDBRepository();
        repoMen.setDb_url(connUrl);
        return repoMen;
    }
}
