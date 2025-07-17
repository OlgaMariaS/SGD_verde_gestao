package com.verde_gestao.api.repositorios;

import com.verde_gestao.api.objetos.modelo.Solicitacao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RepositorioSolicitacao {

    private final JdbcTemplate jdbcTemplate;

    public RepositorioSolicitacao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Solicitacao> buscarTodasSolicitacoes() {
        String sql = """
            SELECT 
                solicitacaoid, 
                tiposolicitacaoid, 
                criador_usuarioid, 
                responsavel_usuarioid, 
                datacriacao, 
                status, 
                descricao 
            FROM solicitacao 
            ORDER BY datacriacao DESC
            """;
        return jdbcTemplate.query(sql, new SolicitacaoRowMapper());
    }

    public Solicitacao buscarSolicitacaoPorId(int solicitacaoId) {
        String sql = """
            SELECT 
                solicitacaoid, 
                tiposolicitacaoid, 
                criador_usuarioid, 
                responsavel_usuarioid, 
                datacriacao, 
                status, 
                descricao 
            FROM solicitacao 
            WHERE solicitacaoid = ?
            """;
        try {
            return jdbcTemplate.queryForObject(sql, new SolicitacaoRowMapper(), solicitacaoId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Solicitacao> buscarSolicitacoesPorCriador(int criadorUsuarioId) {
        String sql = """
            SELECT 
                solicitacaoid, 
                tiposolicitacaoid, 
                criador_usuarioid, 
                responsavel_usuarioid, 
                datacriacao, 
                status, 
                descricao 
            FROM solicitacao 
            WHERE criador_usuarioid = ?
            ORDER BY datacriacao DESC
            """;
        return jdbcTemplate.query(sql, new SolicitacaoRowMapper(), criadorUsuarioId);
    }

    public List<Solicitacao> buscarSolicitacoesPorResponsavel(int responsavelUsuarioId) {
        String sql = """
            SELECT 
                solicitacaoid, 
                tiposolicitacaoid, 
                criador_usuarioid, 
                responsavel_usuarioid, 
                datacriacao, 
                status, 
                descricao 
            FROM solicitacao 
            WHERE responsavel_usuarioid = ?
            ORDER BY datacriacao DESC
            """;
        return jdbcTemplate.query(sql, new SolicitacaoRowMapper(), responsavelUsuarioId);
    }

    public int inserirSolicitacao(Solicitacao solicitacao) {
        String sql = """
            INSERT INTO solicitacao (
                tiposolicitacaoid, 
                criador_usuarioid, 
                responsavel_usuarioid, 
                datacriacao, 
                status, 
                descricao
            ) VALUES (?, ?, ?, ?, ?, ?)
            """;
        return jdbcTemplate.update(sql,
                solicitacao.getTipoSolicitacaoId(),
                solicitacao.getCriadorUsuarioId(),
                solicitacao.getResponsavelUsuarioId(),
                solicitacao.getDataCriacao(),
                solicitacao.getStatus(),
                solicitacao.getDescricao()
        );
    }

    public int atualizarSolicitacao(Solicitacao solicitacao) {
        String sql = """
            UPDATE solicitacao 
            SET tiposolicitacaoid = ?, 
                criador_usuarioid = ?, 
                responsavel_usuarioid = ?, 
                datacriacao = ?, 
                status = ?, 
                descricao = ? 
            WHERE solicitacaoid = ?
            """;
        return jdbcTemplate.update(sql,
                solicitacao.getTipoSolicitacaoId(),
                solicitacao.getCriadorUsuarioId(),
                solicitacao.getResponsavelUsuarioId(),
                solicitacao.getDataCriacao(),
                solicitacao.getStatus(),
                solicitacao.getDescricao(),
                solicitacao.getSolicitacaoId()
        );
    }

    public int deletarSolicitacao(int solicitacaoId) {
        String sql = """
            DELETE FROM solicitacao 
            WHERE solicitacaoid = ?
            """;
        return jdbcTemplate.update(sql, solicitacaoId);
    }

    private static class SolicitacaoRowMapper implements RowMapper<Solicitacao> {
        @Override
        public Solicitacao mapRow(ResultSet rs, int rowNum) throws SQLException {
            Solicitacao solicitacao = new Solicitacao();
            solicitacao.setSolicitacaoId(rs.getInt("solicitacaoid"));
            solicitacao.setTipoSolicitacaoId(rs.getInt("tiposolicitacaoid"));
            solicitacao.setCriadorUsuarioId(rs.getInt("criador_usuarioid"));
            solicitacao.setResponsavelUsuarioId(rs.getInt("responsavel_usuarioid"));
            solicitacao.setDataCriacao(rs.getString("datacriacao"));
            solicitacao.setStatus(rs.getString("status"));
            solicitacao.setDescricao(rs.getString("descricao"));
            return solicitacao;
        }
    }

}