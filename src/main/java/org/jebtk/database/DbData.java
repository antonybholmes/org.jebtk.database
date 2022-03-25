package org.jebtk.database;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.jebtk.core.io.Io;
import org.jebtk.core.io.Writeable;
import org.jebtk.core.text.TextUtils;

public class DbData implements Writeable {
  private List<String> header = new ArrayList<String>();
  private List<List<String>> rows = new ArrayList<List<String>>();
  private String name;

  public DbData(String name, String db, List<String> header, ResultSet rows)
      throws Exception {
    this.name = name;

    ResultSetMetaData rsMetaData = rows.getMetaData();

    int numberOfColumns = rsMetaData.getColumnCount();

    while (rows.next()) {
      List<String> row = new ArrayList<String>();

      // add db
      row.add(db);

      for (int i = 0; i < numberOfColumns; ++i) {
        row.add(rows.getString(i + 1));
      }

      this.rows.add(row);
    }
  }

  public final String getName() {
    return name;
  }

  public List<String> getHeader() {
    return header;
  }

  public List<List<String>> rows() {
    return rows;
  }

  public List<List<String>> getRows() {
    return rows;
  }

  public void append(StringBuilder buffer) {
    buffer.append(name).append("\n");

    buffer.append(TextUtils.join(header, TextUtils.TAB_DELIMITER)).append("\n");

    for (List<String> row : rows) {
      buffer.append(TextUtils.join(row, TextUtils.TAB_DELIMITER)).append("\n");
    }
  }

  @Override
  public void write(Path file) throws IOException {
    StringBuilder buffer = new StringBuilder();

    append(buffer);

    Io.write(file, buffer.toString());
  }
}
