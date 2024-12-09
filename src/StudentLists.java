public class StudentLists {
  public static void main(String args[]) {
    // initialize all the classes and do dependency injection
    StudentDatabase student_db = new StudentDatabase();
    DatabaseController<String> dbcontroller = new DatabaseController<String>(student_db);
    StudentRecordOpener opener = new StudentRecordOpener(dbcontroller);
    opener.initializeInternalDatabase();

    UserInterface ui = new UserInterface(dbcontroller);
    while(true) {
      ui.menu();
    }
  }
}
