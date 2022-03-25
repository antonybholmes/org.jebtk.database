package org.jebtk.database;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Sqlite connectivity through JDBC.
 * 
 * @author Antony Holmes
 *
 */
public class SqliteJDBCConnection extends JDBCConnection {

  private Path mFile;

  public SqliteJDBCConnection(Path file) throws SQLException {
    super(getSqliteConnection(file));

    mFile = file;

    System.err.println("Loading SQLite DB " + file);
  }

  public Path getFile() {
    return mFile;
  }

  public static Connection getSqliteConnection(Path file) throws SQLException {
    try {
      Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException e) {
      throw new SQLException(e.getMessage());
    }

    try {
      return DriverManager
          .getConnection("jdbc:sqlite:" + file.toAbsolutePath());
    } catch (SQLException e) {
      throw new SQLException(e.getMessage());
    }
  }
}
