package com.verde_gestao.api.repositorios;

import com.verde_gestao.api.dto.ResponseCardAviso;
import com.verde_gestao.api.objetos.Aviso;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RepositorioAviso {

    private final JdbcTemplate jdbcTemplate;

    public RepositorioAviso(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Aviso> buscarTodosAvisos() {
        String sql = """
            SELECT 
                avisoid, 
                autorusuarioid, 
                texto, 
                datainicio, 
                datafim 
            FROM aviso 
            ORDER BY datainicio DESC
            """;
        return jdbcTemplate.query(sql, new AvisoRowMapper());
    }

    public Aviso buscarAvisoPorId(int avisoId) {
        String sql = """
            SELECT 
                avisoid, 
                autorusuarioid, 
                texto, 
                datainicio, 
                datafim 
            FROM aviso 
            WHERE avisoid = ?
            """;
        try {
            return jdbcTemplate.queryForObject(sql, new AvisoRowMapper(), avisoId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int inserirAviso(Aviso aviso) {
        String sql = """
            INSERT INTO aviso (
                autorusuarioid, 
                texto, 
                datainicio, 
                datafim
            ) VALUES (?, ?, ?, ?)
            """;
        return jdbcTemplate.update(sql,
                aviso.getAutorUsuarioId(),
                aviso.getTexto(),
                aviso.getDataInicio(),
                aviso.getDataFim()
        );
    }

    public int atualizarAviso(Aviso aviso) {
        String sql = """
            UPDATE aviso 
            SET autorusuarioid = ?, 
                texto = ?, 
                datainicio = ?, 
                datafim = ? 
            WHERE avisoid = ?
            """;
        return jdbcTemplate.update(sql,
                aviso.getAutorUsuarioId(),
                aviso.getTexto(),
                aviso.getDataInicio(),
                aviso.getDataFim(),
                aviso.getAvisoId()
        );
    }

    public int deletarAviso(int avisoId) {
        String sql = """
            DELETE FROM aviso 
            WHERE avisoid = ?
            """;
        return jdbcTemplate.update(sql, avisoId);
    }

    // Todo: montar o SQL que busca os avisos.
    public List<ResponseCardAviso> buscarTodosCardsAvisos() {
        String sql = """
            SELECT 
                avisoid, 
                autorusuarioid, 
                texto, 
                datainicio, 
                datafim 
            FROM aviso 
            ORDER BY datainicio DESC
            """;
        return jdbcTemplate.query(sql, new ResponseCardAvisoRowMapper());
    }

    private static class AvisoRowMapper implements RowMapper<Aviso> {
        @Override
        public Aviso mapRow(ResultSet rs, int rowNum) throws SQLException {
            Aviso aviso = new Aviso();
            aviso.setAvisoId(rs.getInt("avisoid"));
            aviso.setAutorUsuarioId(rs.getInt("autorusuarioid"));
            aviso.setTexto(rs.getString("texto"));
            aviso.setDataInicio(rs.getString("datainicio"));
            aviso.setDataFim(rs.getString("datafim"));
            return aviso;
        }
    }

    private static class ResponseCardAvisoRowMapper implements RowMapper<ResponseCardAviso> {
        @Override
        public ResponseCardAviso mapRow(ResultSet rs, int rowNum) throws SQLException {
            ResponseCardAviso responseCardAviso = new ResponseCardAviso();
            responseCardAviso.setId(rs.getInt("avisoid"));
            responseCardAviso.setUsuario(rs.getString("autorusuarioid"));
            responseCardAviso.setMensagem(rs.getString("texto"));
            responseCardAviso.setDataHora(rs.getString("datainicio"));
            return responseCardAviso;
        }
    }

}