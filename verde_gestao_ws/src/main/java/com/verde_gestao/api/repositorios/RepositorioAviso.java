package com.verde_gestao.api.repositorios;

import com.verde_gestao.api.objetos.dto.CardAvisoDTO;
import com.verde_gestao.api.objetos.modelo.Aviso;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                autor_usuarioid, 
                titulo, 
                texto, 
                data_inicio, 
                data_fim 
            FROM aviso 
            ORDER BY data_inicio DESC
            """;
        return jdbcTemplate.query(sql, new AvisoRowMapper());
    }

    public Aviso buscarAvisoPorId(int avisoId) {
        String sql = """
            SELECT 
                avisoid, 
                autor_usuarioid, 
                titulo, 
                texto, 
                data_inicio, 
                data_fim 
            FROM aviso 
            WHERE avisoid = ?
            """;
        try {
            return jdbcTemplate.queryForObject(sql, new AvisoRowMapper(), avisoId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void inserirAviso(Aviso aviso) {
        SimpleDateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date dataInicio = formatoBrasileiro.parse(aviso.getDataInicio());
            Date dataFim = formatoBrasileiro.parse(aviso.getDataFim());

            String sql = """
                INSERT INTO aviso (
                    autor_usuarioid, 
                    titulo, 
                    texto, 
                    data_inicio, 
                    data_fim
                ) VALUES (?, ?, ?, ?, ?)
                """;

            jdbcTemplate.update(sql,
                    aviso.getAutorUsuarioId(),
                    aviso.getTitulo(),
                    aviso.getTexto(),
                    dataInicio,
                    dataFim
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizarAviso(Aviso aviso) {
        String sql = """
            UPDATE aviso 
            SET autor_usuarioid = ?, 
                titulo = ?, 
                texto = ?, 
                data_inicio = ?, 
                data_fim = ? 
            WHERE avisoid = ?
            """;
        jdbcTemplate.update(sql,
                aviso.getAutorUsuarioId(),
                aviso.getTitulo(),
                aviso.getTexto(),
                aviso.getDataInicio(),
                aviso.getDataFim(),
                aviso.getAvisoId()
        );
    }

    public void deletarAviso(int avisoId) {
        String sql = """
           DELETE FROM aviso 
           WHERE avisoid = ?
           """;
        jdbcTemplate.update(sql, avisoId);
    }

    public List<CardAvisoDTO> buscarTodosCardsAvisos() {
        String sql = """
            SELECT
                avisoid,
                nome,
                titulo,
                texto,
                to_char(data_inicio, 'DD/MM/YYYY') as data_texto
            FROM aviso
            JOIN usuario ON autor_usuarioid = usuarioid
            ORDER BY avisoid
            """;
        return jdbcTemplate.query(sql, new ResponseCardAvisoRowMapper());
    }

    private static class AvisoRowMapper implements RowMapper<Aviso> {
        @Override
        public Aviso mapRow(ResultSet rs, int rowNum) throws SQLException {
            Aviso aviso = new Aviso();
            aviso.setAvisoId(rs.getInt("avisoid"));
            aviso.setAutorUsuarioId(rs.getInt("autor_usuarioid"));
            aviso.setTitulo(rs.getString("titulo"));
            aviso.setTexto(rs.getString("texto"));
            aviso.setDataInicio(rs.getString("data_inicio"));
            aviso.setDataFim(rs.getString("data_fim"));
            return aviso;
        }
    }

    private static class ResponseCardAvisoRowMapper implements RowMapper<CardAvisoDTO> {
        @Override
        public CardAvisoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            CardAvisoDTO cardAvisoDTO = new CardAvisoDTO();
            cardAvisoDTO.setId(rs.getInt("avisoid"));
            cardAvisoDTO.setUsuario(rs.getString("nome"));
            cardAvisoDTO.setTitulo(rs.getString("titulo"));
            cardAvisoDTO.setTexto(rs.getString("texto"));
            cardAvisoDTO.setData(rs.getString("data_texto"));
            return cardAvisoDTO;
        }
    }

}