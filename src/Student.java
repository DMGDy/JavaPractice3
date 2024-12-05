// Object class for each student 
public class Student {
  // Fields needed to keep track of
  private String win_number;
  private String first_name;
  private String last_name;

  void setWIN(String win) {
    this.win_number = win;
  }

  void setFirstName(String f_name) {
    this.first_name = f_name;
  }

  void setLastName(String l_name) {
    this.last_name = l_name;
  }

  String getWIN() {
    return this.win_number;
  }

  String getFirstName() {
    return this.first_name;
  }

  String getLastName() {
    return this.last_name;
  }
}
