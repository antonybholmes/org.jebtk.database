package org.jebtk.database;

/**
 * Service for permanent database conneciton.
 *
 * @author Antony Holmes
 *
 */
public class DatabaseService {
  private static final DatabaseService INSTANCE = new DatabaseService();

  public static final DatabaseService getInstance() {
    return INSTANCE;
  }

  private DatabaseConnection database = null;

  private DatabaseService() {
    // do nothing
  }

  public final void setDatabase(DatabaseConnection database) {
    this.database = database;
  }

  public final DatabaseConnection getDatabase() {
    return database;
  }
}
