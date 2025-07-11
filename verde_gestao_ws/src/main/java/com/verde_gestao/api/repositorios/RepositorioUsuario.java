package com.verde_gestao.api.repositorios;

import com.verde_gestao.api.objetos.Usuario;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RepositorioUsuario {

    private final JdbcTemplate jdbcTemplate;

    public RepositorioUsuario(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Usuario> buscarTodosUsuarios() {
        String sql = """
            SELECT 
                usuarioid, 
                administrador, 
                nome, 
                senha, 
                tipousuarioid, 
                secaoid 
            FROM usuario 
            ORDER BY nome
            """;
        return jdbcTemplate.query(sql, new UsuarioRowMapper());
    }

    public Usuario buscarUsuarioPorId(int usuarioId) {
        String sql = """
            SELECT 
                usuarioid, 
                administrador, 
                nome, 
                senha, 
                tipousuarioid, 
                secaoid 
            FROM usuario 
            WHERE usuarioid = ?
            """;
        try {
            return jdbcTemplate.queryForObject(sql, new UsuarioRowMapper(), usuarioId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Usuario buscarUsuarioPorNome(String nome) {
        String sql = """
            SELECT 
                usuarioid, 
                administrador, 
                nome, 
                senha, 
                tipousuarioid, 
                secaoid 
            FROM usuario 
            WHERE nome = ?
            """;
        try {
            return jdbcTemplate.queryForObject(sql, new UsuarioRowMapper(), nome);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean usuarioExiste(String nome) {
        String sql = """
            SELECT 
                COUNT(*) 
            FROM usuario 
            WHERE nome = ?
            """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, nome);
        return count != null && count > 0;
    }

    public boolean verificarLogin(String nome, String senha) {
        String sql = """
            SELECT 
                COUNT(*) 
            FROM usuario 
            WHERE nome = ? 
              AND senha = ?
            """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, nome, senha);
        return count != null && count > 0;
    }

    public int inserirUsuario(Usuario usuario) {
        String sql = """
            INSERT INTO usuario (
                administrador, 
                nome, 
                senha, 
                tipousuarioid, 
                secaoid
            ) VALUES (?, ?, ?, ?, ?)
            """;
        return jdbcTemplate.update(sql,
                usuario.isAdministrador(),
                usuario.getNome(),
                usuario.getSenha(),
                usuario.getTipoUsuarioId(),
                usuario.getSecaoId()
        );
    }

    public int atualizarUsuario(Usuario usuario) {
        String sql = """
            UPDATE usuario 
            SET administrador = ?, 
                nome = ?, 
                senha = ?, 
                tipousuarioid = ?, 
                secaoid = ? 
            WHERE usuarioid = ?
            """;
        return jdbcTemplate.update(sql,
                usuario.isAdministrador(),
                usuario.getNome(),
                usuario.getSenha(),
                usuario.getTipoUsuarioId(),
                usuario.getSecaoId(),
                usuario.getUsuarioId()
        );
    }

    public int deletarUsuario(int usuarioId) {
        String sql = """
            DELETE FROM usuario 
            WHERE usuarioid = ?
            """;
        return jdbcTemplate.update(sql, usuarioId);
    }

    public boolean existeAdministrador() {
        String sql = """
            SELECT 
                COUNT(*) 
            FROM usuario 
            WHERE administrador = true
            """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null && count > 0;
    }

    private static class UsuarioRowMapper implements RowMapper<Usuario> {
        @Override
        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Usuario usuario = new Usuario();
            usuario.setUsuarioId(rs.getInt("usuarioid"));
            usuario.setAdministrador(rs.getBoolean("administrador"));
            usuario.setNome(rs.getString("nome"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setTipoUsuarioId(rs.getInt("tipousuarioid"));
            usuario.setSecaoId(rs.getInt("secaoid"));
            return usuario;
        }
    }

}