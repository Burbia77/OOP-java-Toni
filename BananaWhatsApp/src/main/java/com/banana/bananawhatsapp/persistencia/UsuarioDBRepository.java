package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Repeatable;
import java.sql.*;
import java.util.Set;

@Setter
@Getter
public class UsuarioDBRepository implements IUsuarioRepository {

    private String db_url;

    @Override
    public Usuario crear(Usuario usuario) throws SQLException {

        String sql = "INSERT INTO usuario values (NULL,?,?,?,?)";

        try (
                Connection conn = DriverManager.getConnection(db_url);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            //Validamos si activo, nombre, email y fecha alta
            usuario.valido();

            stmt.setBoolean(1, usuario.isActivo());
            stmt.setDate(2, Date.valueOf(usuario.getAlta()));
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getNombre());

            int rows = stmt.executeUpdate();

            ResultSet genKeys = stmt.getGeneratedKeys();
            if (genKeys.next()) {
                usuario.setId(genKeys.getInt(1));
            } else {
                throw new SQLException("Usuario creado erroneamente!!!");
            }

        } catch (UsuarioException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
        return usuario;
    }

    @Override
    public Usuario actualizar(Usuario usuario) throws SQLException {
        return null;
    }

    @Override
    public boolean borrar(Usuario usuario) throws SQLException {

        String sql = "DELETE FROM usuario WHERE id=?";

        try (
                Connection conn = DriverManager.getConnection(db_url);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setInt(1, usuario.getId());

            int rows = stmt.executeUpdate();
            System.out.println("Filas borradas: " + rows);

            if(rows<=0) {
                throw new UsuarioException();
            }

        } catch (UsuarioException e) {
            e.printStackTrace();
            throw e;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
        return true;
    }

    @Override
    public Set<Usuario> obtenerPosiblesDestinatarios(Integer id, Integer max) throws SQLException {
        return null;
    }
}