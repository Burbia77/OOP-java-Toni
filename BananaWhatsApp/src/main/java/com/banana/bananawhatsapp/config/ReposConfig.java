package com.banana.bananawhatsapp.config;

import com.banana.bananawhatsapp.persistencia.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ReposConfig {

    @Value("${db_url}")
    String connUrl;

    //Bean asociado al perfil produccion (Usuario)
    @Bean
    @Profile("prod")
    /*public IUsuarioRepository createIUsuarioRepository() {*/
    IUsuarioRepository createUsuarioRepository() {
        System.out.println("usando persistencia.UsuarioDBRepository (prod)");
        UsuarioDBRepository repo = new UsuarioDBRepository();
        repo.setDb_url(connUrl);
        return repo;
    }

    //Bean asociado al perfil desarrollo (Usuario)
    @Bean
    @Profile("dev")
    /*public IUsuarioRepository createInMemUsuarioRepository() {*/
    IUsuarioRepository createInMemUsuarioRepository() {
        System.out.println("usando persistencia.UsuarioInMemoryRepository (dev)");
        UsuarioInMemoryRepository repo = new UsuarioInMemoryRepository();
        return repo;
    }

    //Bean asociado al perfil produccion (Mensaje)
    @Bean
    @Profile("prod")
    /*public IMensajeRepository createIMensajeRepository() {*/
    IMensajeRepository createMensajeRepository() {
        System.out.println("usando persistencia.MensajeDBRepository (prod)");
        MensajeDBRepository repoMen = new MensajeDBRepository();
        repoMen.setDb_url(connUrl);
        return repoMen;
    }
}