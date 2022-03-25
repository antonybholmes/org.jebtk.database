package org.jebtk.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JDBCConnection extends DatabaseConnection {
  private static final int DEFAULT_SIZE = 1000;

  private Connection mConnection;

  public JDBCConnection(Connection connection) {
    mConnection = connection;
  }

  @Override
  public void execute(final String sql) throws SQLException {
    Statement statement = mConnection.createStatement();

    try {
      statement.execute(sql);
    } finally {
      statement.close();
    }
  }

  @Override
  public PreparedStatement prepare(final String sql) throws SQLException {
    return mConnection.prepareStatement(sql);
  }

  @Override
  public void close() throws SQLException {
    if (mConnection == null) {
      return;
    }

    mConnection.close();
  }

  public static String getSqlTypeName(int type) {
    switch (type) {
    case Types.BIT:
      return "BIT";
    case Types.TINYINT:
      return "TINYINT";
    case Types.SMALLINT:
      return "SMALLINT";
    case Types.INTEGER:
      return "INTEGER";
    case Types.BIGINT:
      return "BIGINT";
    case Types.FLOAT:
      return "FLOAT";
    case Types.REAL:
      return "REAL";
    case Types.DOUBLE:
      return "DOUBLE";
    case Types.NUMERIC:
      return "NUMERIC";
    case Types.DECIMAL:
      return "DECIMAL";
    case Types.CHAR:
      return "CHAR";
    case Types.VARCHAR:
      return "VARCHAR";
    case Types.LONGVARCHAR:
      return "LONGVARCHAR";
    case Types.DATE:
      return "DATE";
    case Types.TIME:
      return "TIME";
    case Types.TIMESTAMP:
      return "TIMESTAMP";
    case Types.BINARY:
      return "BINARY";
    case Types.VARBINARY:
      return "VARBINARY";
    case Types.LONGVARBINARY:
      return "LONGVARBINARY";
    case Types.NULL:
      return "NULL";
    case Types.OTHER:
      return "OTHER";
    case Types.JAVA_OBJECT:
      return "JAVA_OBJECT";
    case Types.DISTINCT:
      return "DISTINCT";
    case Types.STRUCT:
      return "STRUCT";
    case Types.ARRAY:
      return "ARRAY";
    case Types.BLOB:
      return "BLOB";
    case Types.CLOB:
      return "CLOB";
    case Types.REF:
      return "REF";
    case Types.DATALINK:
      return "DATALINK";
    case Types.BOOLEAN:
      return "BOOLEAN";
    case Types.ROWID:
      return "ROWID";
    case Types.NCHAR:
      return "NCHAR";
    case Types.NVARCHAR:
      return "NVARCHAR";
    case Types.LONGNVARCHAR:
      return "LONGNVARCHAR";
    case Types.NCLOB:
      return "NCLOB";
    case Types.SQLXML:
      return "SQLXML";
    }

    return "?";
  }

  public DatabaseResultsTable getTable(String sql) throws SQLException {
    return getTable(mConnection.prepareStatement(sql));
  }

  // public static DatabaseResultsTable getTable(PreparedStatement statement)
  // throws SQLException {
  // JDBCStatement s = new JDBCStatement(statement);
  //
  // return s.getResultsTable();
  // }

  /**
   * Convert a statement into a table for processing.
   * 
   * @param statement
   * @return
   * @throws SQLException
   */
  public static DatabaseResultsTable getTable(PreparedStatement statement)
      throws SQLException {

    DatabaseResultsTable table = new DatabaseResultsTable();

    ResultSet results = statement.executeQuery();

    try {
      int columns = results.getMetaData().getColumnCount();

      for (int i = 0; i < columns; ++i) {
        String name = results.getMetaData().getColumnName(i + 1);

        table.getColumnNameMap().put(name, i);
        table.getColumnNames().add(name);
      }

      int type;

      while (results.next()) {
        List<Object> row = new ArrayList<Object>();

        for (int i = 1; i <= columns; ++i) {
          type = results.getMetaData().getColumnType(i);

          switch (type) {
          case Types.BIT:
          case Types.TINYINT:
          case Types.SMALLINT:
          case Types.INTEGER:
          case Types.BIGINT:
            row.add(results.getInt(i));
            break;
          case Types.FLOAT:
          case Types.REAL:
          case Types.DOUBLE:
          case Types.NUMERIC:
          case Types.DECIMAL:
            row.add(results.getDouble(i));
            break;
          default:
            row.add(results.getString(i));
          }
        }

        table.getData().add(row);
      }
    } finally {
      results.close();
    }

    return table;
  }

  /**
   * Wrap the results set to make it more user friendly.
   * 
   * @param statement
   * @return
   * @throws SQLException
   */
  public static ResultsSetTable resultSetTable(PreparedStatement statement)
      throws SQLException {
    return new ResultsSetTable(statement.executeQuery());
  }

  public static ResultsSetTable resultSetTable(Connection connection,
      String sql) throws SQLException {
    return resultSetTable(connection.prepareStatement(sql));
  }

  /**
   * Returns the first int from a query or -1 if the query fails or there is no
   * result.
   * 
   * @param statement
   * @return
   * @throws SQLException
   */
  public static int getId(final PreparedStatement statement)
      throws SQLException {
    int ret = -1;

    ResultSet results = statement.executeQuery();

    try {
      if (results.next()) {
        ret = results.getInt(1);
      }
    } finally {
      results.close();
    }

    return ret;
  }

  public static List<Integer> getIntList(Connection connection, String sql)
      throws SQLException {
    return getIntList(connection.prepareStatement(sql));
  }

  /**
   * Return the first column of results from a statement as a list of integers.
   * 
   * @param statement
   * @return
   * @throws SQLException
   */
  public static List<Integer> getIntList(final PreparedStatement statement)
      throws SQLException {
    List<Integer> ids = new ArrayList<Integer>(DEFAULT_SIZE);

    ResultSet results = statement.executeQuery();

    try {
      while (results.next()) {
        ids.add(results.getInt(1));
      }
    } finally {
      results.close();
    }

    return ids;
  }

  public static Set<Integer> getIntSet(PreparedStatement statement)
      throws SQLException {
    Set<Integer> ids = new HashSet<Integer>(DEFAULT_SIZE);

    ResultSet results = statement.executeQuery();

    try {
      while (results.next()) {
        ids.add(results.getInt(1));
      }
    } finally {
      results.close();
    }

    return ids;
  }

  /**
   * Returns the first id encountered in the statement or -1 if there are no
   * records.
   * 
   * @param statement
   * @return
   * @throws SQLException
   */
  public static int getInt(PreparedStatement statement) throws SQLException {
    int id = -1;

    ResultSet results = statement.executeQuery();

    try {
      if (results.next()) {
        id = results.getInt(1);
      }
    } finally {
      results.close();
    }

    return id;
  }

  /**
   * Return the first column of results from a statement as a list of strings.
   * 
   * @param statement
   * @return
   * @throws SQLException
   */
  public static List<String> getStringList(PreparedStatement statement)
      throws SQLException {
    List<String> ids = new ArrayList<String>(DEFAULT_SIZE);

    ResultSet results = statement.executeQuery();

    try {
      while (results.next()) {
        ids.add(results.getString(1));
      }
    } finally {
      results.close();
    }

    return ids;
  }

  /**
   * Returns the first record as a string, or null otherwise.
   * 
   * @param statement
   * @return
   * @throws SQLException
   */
  public static String getString(PreparedStatement statement)
      throws SQLException {
    String text = null;

    ResultSet results = statement.executeQuery();

    try {
      if (results.next()) {
        text = results.getString(1);
      }
    } finally {
      results.close();
    }

    return text;
  }

  /**
   * Returns true of the statement yields records.
   * 
   * @param statement
   * @return
   * @throws SQLException
   */
  public static boolean hasRecords(PreparedStatement statement)
      throws SQLException {
    boolean ret = false;

    ResultSet results = statement.executeQuery();

    try {
      ret = results.next();
    } finally {
      results.close();
    }

    return ret;
  }

  /**
   * Create a statement from an sql string with one parameter.
   * 
   * @param connection
   * @param sql
   * @param id
   * @return
   * @throws SQLException
   */
  public static PreparedStatement createStatement(Connection connection,
      String sql,
      int id) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(sql);

    statement.setInt(1, id);

    return statement;
  }

  /**
   * Create a statement from an sql string with one parameter.
   * 
   * @param connection
   * @param sql
   * @param id
   * @return
   * @throws SQLException
   */
  public static PreparedStatement createStatement(Connection connection,
      String sql,
      String id) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(sql);

    statement.setString(1, id);

    return statement;
  }

  /**
   * Create a table from an sql statement.
   * 
   * @param connection
   * @param sql
   * @return
   * @throws SQLException
   */
  public static DatabaseResultsTable getTable(Connection connection, String sql)
      throws SQLException {
    PreparedStatement statement = connection.prepareStatement(sql);

    DatabaseResultsTable table = null;

    try {
      table = getTable(statement);
    } finally {
      statement.close();
    }

    return table;
  }

  public static DatabaseResultsTable getTable(Connection connection,
      String sql,
      int id) throws SQLException {
    PreparedStatement statement = createStatement(connection, sql, id);

    DatabaseResultsTable table = null;

    try {
      table = getTable(statement);
    } finally {
      statement.close();
    }

    return table;
  }

  public static List<Integer> getIntList(Connection connection,
      String sql,
      int id) throws SQLException {
    PreparedStatement statement = createStatement(connection, sql, id);

    List<Integer> ids = Collections.emptyList();

    try {
      ids = getIntList(statement);
    } finally {
      statement.close();
    }

    return ids;
  }
}
