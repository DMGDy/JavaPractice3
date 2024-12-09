// Object class for each student 
public class Student {
  // Fields needed to keep track of
  private String win_number;
  private String first_name;
  private String last_name;
  private float gpa;

  // member setters
  public void setWIN(String win) {
    this.win_number = win;
  }

  public void setFirstName(String f_name) {
    this.first_name = f_name;
  }

  public void setLastName(String l_name) {
    this.last_name = l_name;
  }

  public void setGPA(float gpa) {
    this.gpa = gpa;
  }

  // member getters
  public String getWIN() {
    return this.win_number;
  }

  public String getFirstName() {
    return this.first_name;
  }

  public String getLastName() {
    return this.last_name;
  }

  public float getGPA() {
    return this.gpa;
  }

  // @brief print in formatted way of the class memebrs
  public void displayStudentInfo() {
    System.out.println("========================================");
    System.out.printf("Showing Student information for %s %s: \n",first_name,last_name);
    System.out.printf("\tStudent WIN: %s\n",win_number);
    System.out.printf("\tStudent GPA: %.2f\n",gpa);

    // Conditionally show how much their exceeding good/bad standing 
    if(gpa >= 2.0f) {
      System.out.printf("\tStudent exceeds by %.2f\n",gpa-2.0f);
    }else if(gpa < 2.0f) {
      System.out.printf("\tStudent under by %.2f\n",2.0f-gpa);
    }

  }

}
