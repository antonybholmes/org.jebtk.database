package org.jebtk.database;

import java.sql.SQLException;

import org.jebtk.core.pool.DynamicObjectPool;
import org.jebtk.core.pool.ObjectCreator;

public class DatabaseConnectionPool
    extends DynamicObjectPool<DatabaseConnection> {
  public DatabaseConnectionPool(String name,
      ObjectCreator<DatabaseConnection> creator, int maxSize) {
    super(name, creator, maxSize);
  }

  public synchronized void close() throws SQLException {
    for (DatabaseConnection database : pool) {
      database.close();
    }
  }
}
