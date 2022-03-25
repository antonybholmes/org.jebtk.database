package org.jebtk.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sqlite {
  private Sqlite() {
    // do nothing
  }

  /**
   * Creates a connection to an sqlite database
   * 
   * @param file
   * @return
   * @throws Exception {
   */
  public static final Connection open(File sqliteFile) throws Exception {
    Class.forName("org.sqlite.JDBC");

    System.err.println("opening " + sqliteFile);

    return DriverManager
        .getConnection("jdbc:sqlite:" + sqliteFile.getAbsolutePath());
  }

  public static final void close(Connection db) throws SQLException {
    db.close();
  }

  public static final ResultSet getRows(Connection db, String sql) {
    ResultSet set = null;

    try {
      Statement stat = db.createStatement();

      // stat.execute(sql);

      set = stat.executeQuery(sql);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return set;
  }

  public static final void execute(Connection db, String sql) {
    try {
      Statement stat = db.createStatement();

      // stat.execute(sql);

      stat.execute(sql);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
