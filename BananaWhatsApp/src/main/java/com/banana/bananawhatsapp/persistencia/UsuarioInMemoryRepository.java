package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UsuarioInMemoryRepository implements IUsuarioRepository {

    Set<Usuario> usuarios = new HashSet<>();
    private Integer num = 0;

    @Override
    public Usuario crear(Usuario usuario) throws SQLException {
        usuario.valido();
        usuario.setId(num + 1);
        usuarios.add(usuario);
        return usuario;
    }

    @Override
    public Usuario actualizar(Usuario unUsuario) throws UsuarioException {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == unUsuario.getId()) {
                usuarios.remove(usuario);
                usuarios.add(unUsuario);
                return unUsuario;
            }
        }
        throw new UsuarioException();
    }


    @Override
    public boolean borrar(Usuario unUsuario) throws UsuarioException {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == unUsuario.getId()) {
                usuarios.remove(usuario);
                return true;
            }
        }
        throw new UsuarioException();
    }

    @Override
    public Set<Usuario> obtenerPosiblesDestinatarios(Integer id, Integer max) throws SQLException {
        return null;
    }

}
