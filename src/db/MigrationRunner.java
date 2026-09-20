package db;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/*
* Migration runner
* */
public class MigrationRunner {
    private static final String MIGRATION_FOLDER = "src/db/migrations";

    public static void run(Connection connection) throws SQLException, IOException {
        // Create schema_migrations table for tracking the version
        try(Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS schema_migrations (" +
                    "version VARCHAR(50) PRIMARY KEY, " +
                    "executed_at timestamp DEFAULT CURRENT_TIMESTAMP)");
        }

        // retrieve all sql migration that already executed
        Set<String> executed = new HashSet<>();
        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("SELECT version FROM schema_migrations")) {
            while(result.next()) {
                executed.add(result.getString("version"));
            }
        }

        // read all .sql file in migration folder
        File folder = new File(MIGRATION_FOLDER);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".sql"));

        // check the folder and the file
        if(files == null) {
            throw new IOException("Migration folder not found!");
        }

        if(files.length == 0) {
            System.out.println("No migartion file found!");
        }

        // Sort base on file name (timestamp, YYYYMMDDHHmm)
        Arrays.sort(files, Comparator.comparing(File::getName));

        // run migration that still not execute
        boolean originalAutoCommit = connection.getAutoCommit();

        for(File file : files) {
            String version = file.getName();

            if(executed.contains(version)) {
                System.out.println("File :" + version + " already executed");
                continue;
            }

            String sql = Files.readString(file.toPath(), StandardCharsets.UTF_8);

            connection.setAutoCommit(false);

            try(Statement statement = connection.createStatement();
                PreparedStatement preStatement = connection.prepareStatement("INSERT INTO schema_migrations (version) VALUES (?)")) {
                    statement.execute(sql);

                    preStatement.setString(1, version);
                    preStatement.executeUpdate();

                    connection.commit();

                    System.out.println("version : " + version + " executed succesfully");

            }catch(SQLException e) {
                connection.rollback();
                System.out.println("Failed to executed : " + version + e.getMessage());
                throw e;

            }finally {
                connection.setAutoCommit(originalAutoCommit);
            }

        }

    }
}
