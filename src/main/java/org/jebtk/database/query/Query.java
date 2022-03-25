package org.jebtk.database.query;

import java.sql.Connection;
import java.sql.SQLException;

import org.jebtk.database.DatabaseResultsTable;
import org.jebtk.database.JDBCConnection;

public class Query implements SqlRepresentation {
  protected final static String AND_CLAUSE = " AND ";

  private Query mParent = null;

  private Connection mConn;

  public Query(Connection conn) throws SQLException {
    mConn = conn;
  }

  public Query(Query query) {
    mConn = query.mConn;
    mParent = query;
  }

  public Connection getConnection() {
    return mConn;
  }

  public Query getParent() {
    return mParent;
  }

  @Override
  public String toString() {
    return getSql();
  }

  public String getSql() {
    StringBuilder buffer = new StringBuilder();

    getSql(buffer);

    return buffer.toString();
  }

  @Override
  public void getSql(StringBuilder buffer) {
    // Do nothing
  }

  public DatabaseResultsTable fetch() throws SQLException {
    return JDBCConnection.getTable(getConnection().prepareStatement(getSql()));
  }

  public boolean execute() throws SQLException {
    System.err.println(getSql());

    return getConnection().prepareStatement(getSql()).execute();
  }
}
