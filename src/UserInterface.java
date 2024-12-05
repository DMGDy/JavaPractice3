public class UserInterface {

  // member as dependency injection for model-view-controller architecture
  private DatabaseController controller;

  UserInterface(DatabaseController controller) {
    this.controller = controller;
  }

  void menu() {
    System.out.println("Student Standings Manager Application");
    System.out.println("=====================================");
    displayOptions();
    String option = InputReader.inputString("Enter option: ");

    // Return code of database controller
    int rc = 0;

    switch(option) {
      case "a": {
        String win = InputReader.inputString("Enter Student WIN number: ");
        while(!isValidWIN(win)) {
          if(win.trim().equals("q")) {
            return;
          }
          win = InputReader.inputString("Invalid WIN number!\nEnter a valid WIN or enter 'q' to quit: ");
        }
        break;
      }

      case "d": {
        break;
      }

      case "m": {
        break;
      }

      case "s": {
        break;
      }

      case "r": {
        break;
      }

      default:
        System.out.printf("Unknown option \"%s\". Please provide a valid menu option. ");
        break;

    }

  }

  // @brief prints the key to press and its associated command as a menu option
  void displayOptions() {
    System.out.println("a -- Add a new student to the database\n"+
                       "d -- Display all students in the databse, sorted by either GPA or Last names (ascending or descending)\n" +
                       "m -- Modify an existing students GPA by providing their associated WIN number" +
                       "s -- Display a specific student by providing their associated WIN number" +
                       "r -- Remove a specific student by providing their associated WIN number");
  }

  /// @brief checks if provided WIN number is a valid WMU WIN number(9 digits, no letters)
  /// @param win The WIN number of an existing student or a new student
  /// @return True if the provided WIN is valid, false otherwise
  private boolean isValidWIN(String win) {
    // regex to check if string contains only 0-9 and check if the stirng is not blank
    boolean valid = win.matches("\\d+") && !win.isBlank();
    return valid;
  }
}
