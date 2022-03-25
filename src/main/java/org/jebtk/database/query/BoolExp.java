package org.jebtk.database.query;

import java.util.ArrayList;
import java.util.List;

public class BoolExp<T> implements SqlRepresentation {
  private List<T> mClauses = new ArrayList<T>();
  private List<String> mLogical = new ArrayList<String>();

  public BoolExp(T clause) {
    addClause(clause);
  }

  public BoolExp(BoolExp<T> exp) {
    mClauses.addAll(exp.mClauses);
    mLogical.addAll(exp.mLogical);
  }

  private BoolExp<T> addClause(T clause) {
    mClauses.add(clause);

    return this;
  }

  public BoolExp<T> and(T clause) {
    mLogical.add("AND");

    return addClause(clause);
  }

  public BoolExp<T> or(T clause) {
    mLogical.add("OR");

    return addClause(clause);
  }

  @Override
  public void getSql(StringBuilder buffer) {
    for (int i = 0; i < mClauses.size(); ++i) {
      if (i > 0) {
        buffer.append(" ");
        buffer.append(mLogical.get(i - 1));
        buffer.append(" ");
      }

      buffer.append(mClauses.get(i));
    }
  }
}
