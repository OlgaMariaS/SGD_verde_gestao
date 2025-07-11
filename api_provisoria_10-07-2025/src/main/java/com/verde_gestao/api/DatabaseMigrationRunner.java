package com.verde_gestao.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Component
public class DatabaseMigrationRunner implements ApplicationRunner {

    private final JdbcTemplate jdbcTemplate;

    @Value("classpath:db/migration/drawSQL-pgsql-export-2025-07-07.sql")
    private Resource migrationScript;

    public DatabaseMigrationRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*  Todo: Fazer uma função que verifica a versão do banco de dados através do próprio banco.
            pra ver se vai atualizar.
        */
        String sqlCheck = "SELECT to_regclass('public.usuario')";
        String tableName = jdbcTemplate.queryForObject(sqlCheck, String.class);

        if (tableName != null) {
            System.out.println("⚠️ Migração já aplicada. Pulando execução.");
            return;
        }

        String sql = Files.readString(migrationScript.getFile().toPath(), StandardCharsets.UTF_8);

        String[] statements = sql.split(";");

        for (String statement : statements) {
            statement = statement.trim();
            if (!statement.isEmpty()) {
                try {
                    jdbcTemplate.execute(statement);
                } catch (Exception e) {
                    System.out.println("Erro ao executar statement: " + statement);
                    e.printStackTrace();
                }
            }
        }

        System.out.println("✅ Migrações executadas com sucesso!");
    }

}