import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class UserInterface {

  // member as dependency injection for model-view-controller architecture
  private DatabaseController dbcontroller;

  UserInterface(DatabaseController controller) {
    this.dbcontroller = controller;
  }

  void menu() {
    System.out.println("Student Standings Manager Application");
    System.out.println("=====================================");
    displayOptions();
    String option = InputReader.inputString("Enter option: ");

    // Return code of database controller
    int rc = 0;

    switch(option) {

      // a - add a new student
      case "a": {
        String win = InputReader.inputString("Enter Student WIN number: ");
        while(isValidWIN(win) != 0) {

          win = InputReader.inputString("Invalid WIN number!\nEnter a valid WIN: ");

        }
        String gpa_str = InputReader.inputString("Enter Student's GPA: ");
        float gpa = strToGPA(gpa_str);

        while(gpa == -1.0f) {
          gpa_str = InputReader.inputString("Invalid GPA! Enter a GPA from 0.0 to 4.0: ");
          gpa = strToGPA(gpa_str);
        }

        String fname = InputReader.inputString("Enter Student's first name: ");
        String lname = InputReader.inputString("Enter Student's last name: ");

        // create array to pass to controller to create new object
        Object[] members = new Object[4];
        members[0] = win;
        members[1] = fname;
        members[2] = lname;
        members[3] = gpa;

        dbcontroller.addItem(members);
        // write to record using PrintWriter for formatted writing of fields
        if(gpa >= 2.0) {
          try(FileWriter fw = new FileWriter("StudentsGoodStanding.txt",true);
              BufferedWriter bw = new BufferedWriter(fw);
              PrintWriter out = new PrintWriter(bw)) {
            
            out.printf("%s,%s,%s,%.2f\n"
                ,win,fname,lname,gpa);
          } catch(IOException e){
            System.err.printf("Error writing to file!: %s",e.getMessage());
          }
        } else {
          try(FileWriter fw = new FileWriter("StudentsAcademicProbation.txt",true);
              BufferedWriter bw = new BufferedWriter(fw);
              PrintWriter out = new PrintWriter(bw)) {
            
            out.printf("%s,%s,%s,%.2f\n"
                ,win,fname,lname,gpa);
          } catch(IOException e){
            System.err.printf("Error writing to file!: %s",e.getMessage());
          }
        }
        


        break;
      }

      // display all students in sorted fashion
      case "d": {
        String sort = InputReader.inputString("Sort by GPA or Last Name?(g/l): ");
        String order = InputReader.inputString("Sort by ascending or descending?(a/d): ");
        rc = dbcontroller.sortedShowAll(sort,order);
        if(rc==-1) {
          System.out.println("Databse currently contains no user accounts to show!");
        }
        break;

      }

      // modify an existing students GPA
      // NOTE: if a student is originally in one standing and a change in GPA should change it,
      //  the program does not update it as this is already beyond the scope of Lab 5 : >
      case "m": {
        String win = InputReader.inputString("Enter Student Win to Modify: ");
        rc = dbcontroller.edit(win);
        switch(rc) {
          case(0):
            System.out.println("GPA for student account successfully updated!\n");
            break;
          case(-1):
            System.out.printf("Student with WIN \"%s\" does not exist!",win);
            break;
          case(-2):
            System.out.println("Database currently contains no students to update!");
        }
        break;
      }

      // only show a specific student
      case "s": {
        String win = InputReader.inputString("Enter WIN of student you wish to view");
        rc = dbcontroller.edit(win);
        switch(rc) {
          case(0):
            break;
          case(-1):
            System.out.printf("Student with that WIN \"%s\" does not exist!\n",win);
            break;
          case(-2):
            System.out.println("Database currently contains no students to show!");
        }
        break;
      }

      // remove a specific student given a WIN
      case "r": {
        System.out.println("Feature not implemented :)\n");
        break;
      }

      // quit the program
      case "q": {
        System.exit(0);
      }

      // not recognized option
      default:
        System.out.printf("Unknown option \"%s\". Please provide a valid menu option. ");
        break;

    }

  }

  // @brief prints the key to press and its associated command as a menu option
  void displayOptions() {
    System.out.println("a -- Add a new student to the database\n"+
                       "d -- Display all students in the databse, sorted by either GPA or Last names (ascending or descending)\n" +
                       "m -- Modify an existing students GPA by providing their associated WIN number\n" +
                       "s -- Display a specific student by providing their associated WIN number\n" +
                       "r -- Remove a specific student by providing their associated WIN number\n" +
                       "q -- Exit the program\n");
  }

  /// @brief checks if provided WIN number is a valid WMU WIN number(9 digits, no letters)
  /// @param win The WIN number of an existing student or a new student
  /// @return True if the provided WIN is valid, false otherwise
  private int isValidWIN(String win) {
    // regex to check if string contains only 0-9 and check if the stirng is not blank
    int valid = (win.matches("\\d+") && !win.isBlank())? 0: 1;
    // TODO: check if WIN is unique or not by querying the controller
    return valid;
  }

  /// @brief converts the given string to float for gpa or -1 if it is invalid
  /// @param gpa_str the user entered string representation of the student's GPA
  /// @return the gpa as a float type or -1.0f if it is invalid 
  private float strToGPA(String gpa_str) {
    float gpa = 0.0f;

    // try-catch to check if string is parsable to float
    try {
      gpa = Float.parseFloat(gpa_str);
      // if outside the range of possible GPA, set to -1 as an error
      if(gpa < 0.0f || gpa > 4.0) {
        gpa = -1.0f;
      }

    }

    // if not parsable float, set as number 1
    catch(NumberFormatException e) {
      gpa = -1.0f;
    }

    return gpa;
  }
}
