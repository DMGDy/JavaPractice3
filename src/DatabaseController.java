/* Class mean to interact with a databases public interface
 * mean to be owned and used by some UI class
 *
 * K being some type that the database uses for identification
 */
public class DatabaseController<K> {
  private final Database db;

  public DatabaseController(Database db) {
    this.db = db;
  }

  public int addItem() {
    if(!db.isFull()) {
      db.add();
      return 0;
    } else { return -1; }
  }

  // overload for case to have an array of members 
  public int addItem(Object[] members) {
    if(!db.isFull()) {
      db.add(members);
      return 0;
    } else { return -1; }
  }


  public int showAll() {
    if(db.isEmpty()) {
      return -1;
    }
    db.showAll();
    return 0;
  }

  public int showItem(K identifier) {
    if(!db.containsItem(identifier)) { return -1; }
    else if(db.isEmpty()) { return -2; }
    else { 
      db.showItem(identifier);
      return 0;
    }

  }

  // Only edit the database if the item exists and the database is not empty
  public int edit(K identifier) {
    if(!db.containsItem(identifier)) { return -1; }
    else if(db.isEmpty()) { return -2; }
    else { 
      db.updateItem(identifier);
      return 0;
    }
  }

  public int delete(K identifier) {
    if(!db.containsItem(identifier)) { return -1; }
    else if(db.isEmpty()) { return -2; }
    else { 
      db.removeItem(identifier);
      return 0;
    }
  }

  // Show all the database items given the field to sort by and the order (in-order or reversed)
  public int sortedShowAll(String field, String order) {
    if(db.isEmpty()) { return -1; }
    switch(order) {
      case "d":
        db.sortAscending(field);
        break;
      case "a":
        db.sortDescending(field);
        break;
    }
    return 0;
  }
}


