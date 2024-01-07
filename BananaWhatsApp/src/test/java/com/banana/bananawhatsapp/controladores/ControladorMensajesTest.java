package com.banana.bananawhatsapp.controladores;

import com.banana.bananawhatsapp.config.SpringConfig;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class ControladorMensajesTest {

    @Autowired
    ControladorMensajes controlMen;

    @Autowired
    ControladorUsuarios controlUsu;

    @Test
    void testBeans() {
        assertThat(controlMen, notNullValue());
        assertThat(controlUsu, notNullValue());
    }

    @Test
    void dadoRemitenteYDestinatarioYTextoValidos_cuandoEnviarMensaje_entoncesOK() throws Exception {
        Usuario usuRem = new Usuario(null, "Bea", "ppp@gmail.com", LocalDate.now(), true);
        controlUsu.alta(usuRem);

        Usuario usuDes = new Usuario(null, "Pol", "ttt@gmail.com", LocalDate.now(), true);
        controlUsu.alta(usuDes);

        //Vemos que imprime correctamente el ID de los dos ususarios creados.
        System.out.println(usuRem.getId());
        System.out.println(usuDes.getId());

        //Esto no veo el motivo de porqué provoca una excepcion por mensaje no valido, ambas formas en las que lo tengo implementado
        /*controlMen.enviarMensaje(usuRem.getId(), usuDes.getId(), "Ande iras");*/
        boolean enviado = controlMen.enviarMensaje(usuRem.getId(), usuDes.getId(), "Ande iras");
        //No llega aqui, peta antes en ControladorMensajes
        System.out.println(enviado);
    }

    @Test
    void dadoRemitenteYDestinatarioYTextoNOValidos_cuandoEnviarMensaje_entoncesExcepcion() {
    }

    @Test
    void dadoRemitenteYDestinatarioValidos_cuandoMostrarChat_entoncesOK() {
    }

    @Test
    void dadoRemitenteYDestinatarioNOValidos_cuandoMostrarChat_entoncesExcepcion() {
    }

    @Test
    void dadoRemitenteYDestinatarioValidos_cuandoEliminarChatConUsuario_entoncesOK() {
    }

    @Test
    void dadoRemitenteYDestinatarioNOValidos_cuandoEliminarChatConUsuario_entoncesExcepcion() {
    }
}