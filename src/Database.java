/* Database interface
 * - defines key methods to implement for a database
 * - Database will hold objects of some type T 
 *   and will access each object through
 *   some key type K
 */
public interface Database<T,K> {
  public void add();
  // overload method to handle initialization members
  public void add(Object[] members);
  public void updateItem(K identifier);
  public void showItem(K identifier);
  public void removeItem(K identifier);
  public void showAll();
  public void sortAscending(String field);
  public void sortDescending(String field);
  public boolean isEmpty();
  public boolean isFull();
  public boolean containsItem(K identifier);
}
