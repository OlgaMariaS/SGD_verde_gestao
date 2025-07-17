package com.verde_gestao.api.repositorios;

import com.verde_gestao.api.objetos.modelo.Secao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RepositorioSecao {

    private final JdbcTemplate jdbcTemplate;

    public RepositorioSecao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Secao> buscarTodasSecoes() {
        String sql = """
            SELECT 
                secaoid, 
                descricao 
            FROM secao 
            ORDER BY descricao
            """;
        return jdbcTemplate.query(sql, new SecaoRowMapper());
    }

    public Secao buscarSecaoPorId(int secaoId) {
        String sql = """
            SELECT 
                secaoid, 
                descricao 
            FROM secao 
            WHERE secaoid = ?
            """;
        try {
            return jdbcTemplate.queryForObject(sql, new SecaoRowMapper(), secaoId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Secao buscarSecaoPorDescricao(String descricao) {
        String sql = """
            SELECT 
                secaoid, 
                descricao 
            FROM secao 
            WHERE descricao = ?
            """;
        try {
            return jdbcTemplate.queryForObject(sql, new SecaoRowMapper(), descricao);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean secaoExiste(String descricao) {
        String sql = """
            SELECT 
                COUNT(*) 
            FROM secao 
            WHERE descricao = ?
            """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, descricao);
        return count != null && count > 0;
    }

    public int inserirSecao(Secao secao) {
        String sql = """
            INSERT INTO secao (
                descricao
            ) VALUES (?)
            """;
        return jdbcTemplate.update(sql, secao.getDescricao());
    }

    public int atualizarSecao(Secao secao) {
        String sql = """
            UPDATE secao 
            SET descricao = ? 
            WHERE secaoid = ?
            """;
        return jdbcTemplate.update(sql, secao.getDescricao(), secao.getSecaoid());
    }

    public int deletarSecao(int secaoId) {
        String sql = """
            DELETE FROM secao 
            WHERE secaoid = ?
            """;
        return jdbcTemplate.update(sql, secaoId);
    }

    private static class SecaoRowMapper implements RowMapper<Secao> {
        @Override
        public Secao mapRow(ResultSet rs, int rowNum) throws SQLException {
            Secao secao = new Secao();
            secao.setSecaoid(rs.getInt("secaoid"));
            secao.setDescricao(rs.getString("descricao"));
            return secao;
        }

    }

}