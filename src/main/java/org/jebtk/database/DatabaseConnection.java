package org.jebtk.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.jebtk.core.io.Io;

public abstract class DatabaseConnection {

  public void execute(File sqliteFile) throws IOException, SQLException {
    System.err.println("execute " + sqliteFile);

    BufferedReader reader = new BufferedReader(new FileReader(sqliteFile));

    String line;

    try {
      while ((line = reader.readLine()) != null) {
        if (Io.isEmptyLine(line)) {
          continue;
        }

        execute(line);
      }
    } finally {
      reader.close();
    }
  }

  public abstract void execute(final String sql) throws SQLException;

  public abstract PreparedStatement prepare(final String sql)
      throws SQLException;

  public abstract void close() throws SQLException;
}
