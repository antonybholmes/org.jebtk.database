package org.jebtk.database.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jebtk.core.text.TextUtils;

public class OrderByQuery extends Query {
  protected List<OrderBy> mColumns = new ArrayList<OrderBy>();

  public OrderByQuery(Query query, OrderBy order) {
    super(query);

    col(order);
  }

  public OrderByQuery(Query query, String column, String... columns) {
    super(query);

    mColumns.add(new OrderBy(column));

    for (String c : columns) {
      mColumns.add(new OrderBy(c));
    }
  }

  public OrderByQuery(OrderByQuery query) {
    super(query.getParent());

    mColumns.addAll(query.mColumns);
  }

  public OrderByQuery cols(String column, String... columns) {
    OrderByQuery ret = new OrderByQuery(this);

    ret.mColumns.add(new OrderBy(column));

    for (String c : columns) {
      ret.mColumns.add(new OrderBy(c));
    }

    return ret;
  }

  public OrderByQuery col(String column) {
    return col(new OrderBy(column));
  }

  public OrderByQuery col(OrderBy column) {
    OrderByQuery ret = new OrderByQuery(this);

    ret.mColumns.add(column);

    return ret;

  }

  public ValuesQuery values(Object... values) throws SQLException {
    return new ValuesQuery(this, values);
  }

  @Override
  public void getSql(StringBuilder buffer) {
    getParent().getSql(buffer);

    buffer.append(" ORDER BY ");
    buffer.append(TextUtils.commaJoin(mColumns));
  }
}
