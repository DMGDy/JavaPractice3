import java.util.Scanner;
public class InputReader {
  
  private static final Scanner scanner = new Scanner(System.in);

  static String inputString() {
    return scanner.nextLine();
  }
  
  //overloaded method to optionally display message
  static String inputString(String message) {
    System.out.printf("%s",message);
    return scanner.nextLine();
  }


  // close scanner, no longer needed
  static void endReader() {
    scanner.close();
  }

}
