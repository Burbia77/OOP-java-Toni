package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.config.ReposConfig;
import com.banana.bananawhatsapp.config.SpringConfig;
import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;
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

/*
//Pruebas UsuarioInMemoryRepository
@ActiveProfiles("dev")
*/

//Pruebas UsuarioDBRepository
@ActiveProfiles("prod")

class UsuarioRepositoryTest {

    @Autowired
    IUsuarioRepository repo;

    @Test
    void testBeans() {
        assertThat(repo, notNullValue());
    }

    @Test
    void dadoUnUsuarioValido_cuandoCrear_entoncesUsuarioValido() throws Exception {
        Usuario nuevo = new Usuario(null, "Antonio", "alr@gmail.com", LocalDate.now(), true);
        repo.crear(nuevo);
        System.out.println(nuevo);
        assertThat(nuevo, notNullValue());
        assertThat(nuevo.getId(), greaterThan(0));
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoCrear_entoncesExcepcion() throws Exception {
        Usuario nuevo = new Usuario(null, "Antonio", "alrgmail.com", LocalDate.now(), true);

        assertThrows(Exception.class, () -> {
            repo.crear(nuevo);
        });
    }

    @Test
    void dadoUnUsuarioValido_cuandoActualizar_entoncesUsuarioValido() {
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoActualizar_entoncesExcepcion() {
    }

    @Test
    void dadoUnUsuarioValido_cuandoBorrar_entoncesOK() throws SQLException {
        Usuario usuario = new Usuario(6, "Antonio", "alr@gmail.com", LocalDate.now(), true);
        boolean ok = repo.borrar(usuario);

        assertThat(ok, is(true));
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoBorrar_entoncesExcepcion() throws SQLException {
        Usuario usuario = new Usuario(4, "Antunio", "alr@gmail.com", LocalDate.now(), true);

        assertThrows(UsuarioException.class, () -> {
            boolean ok = repo.borrar(usuario);
        });
    }

    @Test
    void dadoUnUsuarioValido_cuandoObtenerPosiblesDestinatarios_entoncesLista() {
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoObtenerPosiblesDestinatarios_entoncesExcepcion() {
    }

}