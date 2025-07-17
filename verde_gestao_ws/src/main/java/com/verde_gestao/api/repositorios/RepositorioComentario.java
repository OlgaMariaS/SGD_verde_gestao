package com.verde_gestao.api.repositorios;

import com.verde_gestao.api.objetos.modelo.Comentario;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RepositorioComentario {

    private final JdbcTemplate jdbcTemplate;

    public RepositorioComentario(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Comentario> buscarComentariosPorSolicitacao(int solicitacaoId) {
        String sql = """
            SELECT 
                comentarioid, 
                solicitacaoid, 
                autorusuarioid, 
                texto 
            FROM comentario 
            WHERE solicitacaoid = ?
            ORDER BY comentarioid
            """;
        return jdbcTemplate.query(sql, new ComentarioRowMapper(), solicitacaoId);
    }

    public Comentario buscarComentarioPorId(int comentarioId) {
        String sql = """
            SELECT 
                comentarioid, 
                solicitacaoid, 
                autorusuarioid, 
                texto 
            FROM comentario 
            WHERE comentarioid = ?
            """;
        try {
            return jdbcTemplate.queryForObject(sql, new ComentarioRowMapper(), comentarioId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int inserirComentario(Comentario comentario) {
        String sql = """
            INSERT INTO comentario (
                solicitacaoid, 
                autorusuarioid, 
                texto
            ) VALUES (?, ?, ?)
            """;
        return jdbcTemplate.update(sql,
                comentario.getSolicitacaoId(),
                comentario.getAutorUsuarioId(),
                comentario.getTexto()
        );
    }

    public int atualizarComentario(Comentario comentario) {
        String sql = """
            UPDATE comentario 
            SET texto = ? 
            WHERE comentarioid = ?
            """;
        return jdbcTemplate.update(sql,
                comentario.getTexto(),
                comentario.getComentarioId()
        );
    }

    public int deletarComentario(int comentarioId) {
        String sql = """
            DELETE FROM comentario 
            WHERE comentarioid = ?
            """;
        return jdbcTemplate.update(sql, comentarioId);
    }

    private static class ComentarioRowMapper implements RowMapper<Comentario> {
        @Override
        public Comentario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Comentario comentario = new Comentario();
            comentario.setComentarioId(rs.getInt("comentarioid"));
            comentario.setSolicitacaoId(rs.getInt("solicitacaoid"));
            comentario.setAutorUsuarioId(rs.getInt("autorusuarioid"));
            comentario.setTexto(rs.getString("texto"));
            return comentario;
        }
    }

}