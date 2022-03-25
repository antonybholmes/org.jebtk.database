package org.jebtk.database.query;

import java.sql.Connection;
import java.sql.SQLException;

import org.jebtk.database.DatabaseResultsTable;
import org.jebtk.database.JDBCConnection;

public class TableQuery extends Query {
  protected final static String AND_CLAUSE = " AND ";

  private ValuesQuery mValuesQuery;

  public TableQuery(Connection conn) throws SQLException {
    super(conn);

    mValuesQuery = select("column_name", "data_type")
        .from("information_schema.columns").where("table_name")
        .orderBy("ordinal_position").values();
  }

  public TableQuery(Query query) {
    super(query);
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
    System.err.println(getSql());

    return JDBCConnection.getTable(getConnection().prepareStatement(getSql()));
  }

  public boolean execute() throws SQLException {
    return getConnection().prepareStatement(getSql()).execute();
  }

  public SelectQuery select(String column, String... columns) {
    return new SelectQuery(this, column, columns);
  }

  public UpdateQuery update(String table) {
    return update(new Table(table));
  }

  public UpdateQuery update(Table table) {
    return new UpdateQuery(this, table);
  }

  public InsertQuery insert(String table) {
    return insert(new Table(table));
  }

  public InsertQuery insert(Table table) {
    return new InsertQuery(this, table);
  }

  public DeleteQuery delete() {
    return new DeleteQuery(this);
  }

  public Table describe(String table) throws SQLException {

    mValuesQuery.values(table);

    DatabaseResultsTable results = mValuesQuery.fetch();

    Table t = new Table(table);

    for (int i = 0; i < results.getRowCount(); ++i) {
      ColumnType ct;

      if (results.getString(i, 1).contains("double")) {
        ct = ColumnType.DOUBLE;
      } else if (results.getString(i, 1).contains("int")) {
        ct = ColumnType.INT;
      } else {
        ct = ColumnType.STRING;
      }

      Column c = new Column(results.getString(i, 0), ct);

      t.col(c);
    }

    return t;
  }
}
