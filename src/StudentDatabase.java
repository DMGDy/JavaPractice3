import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// implementing Database Interface with type of Student and key of String (WIN)
public class StudentDatabase implements Database<Student,String> {
  // two collections, one for Good standing and one for Bad standing
  // both implemented as Hashmap, where key is a String (WIN)
  private HashMap<String,Student> goodstandings;
  private HashMap<String,Student> badstandings;

  StudentDatabase() {
    goodstandings = new HashMap<>();
    badstandings = new HashMap<>();
  }

  /// @brief unused for this application
  @Override
  public void add() {
    // unused
  }

  /// @brief insantiates new Student object and sets all the members and adds to appropiate database
  /// @param members, Object array of the members of the Student class
  @Override
  public void add(Object[] members) {
    Student s = new Student();
    s.setWIN(members[0].toString());
    s.setFirstName(members[1].toString());
    s.setLastName(members[2].toString());
    s.setGPA(Float.parseFloat(members[3].toString()));

    // put either in good or bad standings
    if(s.getGPA() >= 2.0) {
      goodstandings.put(s.getWIN(),s);
    }else {
      badstandings.put(s.getWIN(),s);
    }

  }

  /// @brief update the students GPA
  /// @param win the key to the database to get student object
  @Override
  public void updateItem(String win) {
    // default assume student is good standing
    Student s = goodstandings.get(win);
    // else it is badstandings
    if(s == null) {
      s = badstandings.get(win);
    }
  }

  /// @brief this database cannot get full, no set maximum capacity
  /// @return false always
  @Override
  public boolean isFull() {
    return false;
  }

  /// @brief checks if both databases (goodstandings and badstandings) are empty
  /// @return true if both are empty, false if at least one is not
  @Override
  public boolean isEmpty() {
    boolean is_empty = false;
    if(goodstandings.isEmpty()&&badstandings.isEmpty()) {
      return true;
    }
    
    return is_empty;
  }

  @Override
  public void removeItem(String identifier) {
     if(goodstandings.containsKey(identifier)) {
      goodstandings.remove(identifier);
    }else{
      badstandings.remove(identifier);
    }
   
  }

  @Override
  public void showItem(String identifier) {
    if(goodstandings.containsKey(identifier)) {
      goodstandings.get(identifier).displayStudentInfo();
    }else{
      badstandings.get(identifier).displayStudentInfo();
    }
  }

  @Override
  public boolean containsItem(String identifier) {
    boolean contains_item = false;

    return contains_item;
  }

  @Override
  public void sortAscending(String field) {
    ArrayList<Student> good_students = new ArrayList<>(goodstandings.values());
    ArrayList<Student> bad_students = new ArrayList<>(badstandings.values());

    switch(field){
      case "l": {
        good_students.sort((a,b)->{
          return Character.compare(a.getLastName().charAt(0),b.getLastName().charAt(0));
        });
        bad_students.sort((a,b)->{
          return Character.compare(a.getLastName().charAt(0),b.getLastName().charAt(0));
        });

        break;
      }
      // compare floats of gpa
      case "g": {
        good_students.sort((a,b)->{
          return (a.getGPA() > b.getGPA())? -1: 1;
        });

        bad_students.sort((a,b)->{
          return (a.getGPA() > b.getGPA())? -1: 1;
        });

        break;
      }
    }

    System.out.println("Showing sorted good students: \n");

    for(Student student:good_students) {
      student.displayStudentInfo();
    }

    System.out.println("\n\nShowing sorted bad students: \n");

    for(Student student:bad_students) {
      student.displayStudentInfo();
    }
  }

  @Override
  public void sortDescending(String field) {
    ArrayList<Student> good_students = new ArrayList<>(goodstandings.values());
    ArrayList<Student> bad_students = new ArrayList<>(badstandings.values());

    switch(field){
      case "l": {
        good_students.sort((a,b)->{
          return -1 * Character.compare(a.getLastName().charAt(0),b.getLastName().charAt(0));
        });
        bad_students.sort((a,b)->{
          return -1 * Character.compare(a.getLastName().charAt(0),b.getLastName().charAt(0));
        });

        break;
      }
      case "g": {
        good_students.sort((a,b)->{
          return (a.getGPA() > b.getGPA())? 1: -1;
        });

        bad_students.sort((a,b)->{
          return (a.getGPA() > b.getGPA())? 1: -1;
        });
        break;
      }
    }

    System.out.println("Showing sorted good students: \n");

    for(Student student:good_students) {
      student.displayStudentInfo();
    }

    System.out.println("\n\nShowing sorted bad students: \n");

    for(Student student:bad_students) {
      student.displayStudentInfo();
    }

  }




  @Override
  public void showAll() {

    System.out.printf("Good standing Students:\n");
    for(Map.Entry<String,Student> entry: goodstandings.entrySet()) {
      entry.getValue().displayStudentInfo();
    }

    System.out.printf("Students on Academic Probation:\n");
    for(Map.Entry<String,Student> entry: badstandings.entrySet()) {
      entry.getValue().displayStudentInfo();
    }

  }


}
