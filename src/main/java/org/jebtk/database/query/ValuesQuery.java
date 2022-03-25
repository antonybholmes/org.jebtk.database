package org.jebtk.database.query;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.jebtk.database.DatabaseResultsTable;
import org.jebtk.database.JDBCConnection;

public class ValuesQuery extends Query {
  private PreparedStatement mStatement;

  public ValuesQuery(Query query, Object value, Object... values)
      throws SQLException {
    super(query);

    createStatement();

    values(value, values);
  }

  /**
   * Set the values of the prepared statement. This allows the built query
   * object to be reused.
   * 
   * @param values
   * @throws SQLException
   */
  public void values(Object value, Object... values) throws SQLException {

    if (value instanceof Double) {
      mStatement.setDouble(1, (Double) value);
    } else if (value instanceof Integer) {
      mStatement.setInt(1, (Integer) value);
    } else {
      mStatement.setString(1, value.toString());
    }

    for (int i = 0; i < values.length; ++i) {
      Object v = values[i];

      if (v instanceof Double) {
        mStatement.setDouble(i + 2, (Double) v);
      } else if (v instanceof Integer) {
        mStatement.setInt(i + 2, (Integer) v);
      } else {
        mStatement.setString(i + 2, v.toString());
      }
    }
  }

  private void createStatement() throws SQLException {
    mStatement = getConnection().prepareStatement(super.getSql());
  }

  @Override
  public String getSql() {
    return mStatement.toString();
  }

  @Override
  public void getSql(StringBuilder buffer) {
    getParent().getSql(buffer);
  }

  @Override
  public DatabaseResultsTable fetch() throws SQLException {
    System.err.println("mm " + mStatement);
    return JDBCConnection.getTable(mStatement);
  }

  public OrderByQuery orderBy(OrderBy orderBy) {
    return new OrderByQuery(this, orderBy);
  }
}
