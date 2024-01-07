package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.config.SpringConfig;
import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.exceptions.MensajeException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class ServicioMensajeriaTest {

    @Autowired
    IServicioUsuarios serviUsu;

    @Autowired
    IServicioMensajeria serviMen;

    @Test
    void testBeans() {
        assertThat(serviMen, notNullValue());
        assertThat(serviUsu, notNullValue());
    }

    @Test
    void dadoRemitenteYDestinatarioYTextoValido_cuandoEnviarMensaje_entoncesMensajeValido() {

        Usuario usuRem = new Usuario(null, "Juan", "ppp@gmail.com", LocalDate.now(), true);
        serviUsu.crearUsuario(usuRem);

        Usuario usuDes = new Usuario(null, "Sara", "ttt@gmail.com", LocalDate.now(), true);
        serviUsu.crearUsuario(usuDes);

        /*serviMen.enviarMensaje(usuRem, usuDes, "Parece que va a llover");*/

        Mensaje men = serviMen.enviarMensaje(usuRem, usuDes, "Hoy saldras?");

        assertThat(men.getId(), greaterThan(0));
        assertThat(usuRem.valido(), is(true));
        assertThat(usuDes.valido(), is(true));
    }

    @Test
    void dadoRemitenteYDestinatarioYTextoNOValido_cuandoEnviarMensaje_entoncesExcepcion() {
    }

    @Test
    void dadoRemitenteYDestinatarioValido_cuandoMostrarChatConUsuario_entoncesListaMensajes() {
    }

    @Test
    void dadoRemitenteYDestinatarioNOValido_cuandoMostrarChatConUsuario_entoncesExcepcion() {
    }

    @Test
    void dadoRemitenteYDestinatarioValido_cuandoBorrarChatConUsuario_entoncesOK() {
    }

    @Test
    void dadoRemitenteYDestinatarioNOValido_cuandoBorrarChatConUsuario_entoncesExcepcion() {
    }
}