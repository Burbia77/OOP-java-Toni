package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.config.ReposConfig;
import com.banana.bananawhatsapp.config.SpringConfig;
import com.banana.bananawhatsapp.exceptions.MensajeException;
import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.servicios.ServicioMensajeria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})

//Pruebas MensajeDBRepository (se activa el uso del bean prod de ReposConfig)
@ActiveProfiles("prod")
class MensajeRepositoryTest {

    @Autowired
    IMensajeRepository repoMen;

    @Autowired
    IUsuarioRepository repoUsu;

    @Test
    void testBeans() {
        assertThat(repoMen, notNullValue());
        assertThat(repoUsu, notNullValue());
    }

    @Test
    void dadoUnMensajeValido_cuandoCrear_entoncesMensajeValido() throws UsuarioException, MensajeException, SQLException {

        Usuario usuRem = new Usuario(null, "Juan", "ppp@gmail.com", LocalDate.now(), true);
        repoUsu.crear(usuRem);

        Usuario usuDes = new Usuario(null, "Sara", "ttt@gmail.com", LocalDate.now(), true);
        repoUsu.crear(usuDes);

        Mensaje nuevoMensaje = new Mensaje(null, usuRem, usuDes, "Que tal Sara", LocalDate.now());

        repoMen.crear(nuevoMensaje);
        assertThat(nuevoMensaje, notNullValue());
        assertThat(nuevoMensaje.getId(), greaterThan(0));
    }

    @Test
    void dadoUnMensajeNOValido_cuandoCrear_entoncesExcepcion() {
    }

    @Test
    void dadoUnUsuarioValido_cuandoObtener_entoncesListaMensajes() {
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoObtener_entoncesExcepcion() {
    }

    @Test
    void dadoUnUsuarioValido_cuandoBorrarTodos_entoncesOK() {
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoBorrarTodos_entoncesExcepcion() {
    }

}