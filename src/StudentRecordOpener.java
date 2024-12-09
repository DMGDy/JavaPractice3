import java.nio.file.*;
import java.io.*;
// class to parse the two files and create database
public class StudentRecordOpener { 
  // files where database is recorded in 
  private static final String goodstandings = "StudentsGoodStanding.txt";
  private static final String badstandings = "StudentsAcademicProbation.txt";

  private DatabaseController dbcontroller;

  /// @brief assign databasecontroller for students so it can initialize internal database
  StudentRecordOpener(DatabaseController dbcontroller) {
    this.dbcontroller = dbcontroller;
  }

  /// @brief initialize studentdatabse record through calling controller
  public void initializeInternalDatabase() {
    String[] records = openDatabase();
    
    // split records by newline
    String[] good_record = records[0].split("\n");
    String[] bad_record = records[1].split("\n");

    // start parsing values, skipping header
    for(int i = 1;i < good_record.length;i++){
      // polymorphism is great, create array for fields in csv to be created as an object
      Object[] members = new Object[4];
      // split line by comma to extract fields
      String[] fields = good_record[i].split(",");
      
      // Assumption made: all data within this file is valid data for the program
      // no checks will be made
      members[0] = fields[0];
      members[1] = fields[1];
      members[2] = fields[2];
      // Only GPA needs to be parsed as float
      members[3] = Float.parseFloat(fields[3]);

      dbcontroller.addItem(members);
    }

    // do same for bad standing student record
    for(int i = 1;i < bad_record.length;i++){
      // polymorphism is great, create array for fields in csv to be created as an object
      Object[] members = new Object[4];
      // split line by comma to extract fields
      String[] fields = bad_record[i].split(",");

      // Assumption made: all data within this file is valid data for the program
      // no checks will be made
      members[0] = fields[0];
      members[1] = fields[1];
      members[2] = fields[2];
      // Only GPA needs to be parsed as float
      members[3] = Float.parseFloat(fields[3]);

      dbcontroller.addItem(members);
    }


  }

  /// @brief parase or create empty database files
  /// @return the 2 databses in array of string to be parsed into the internal database
  private String[] openDatabase() {
    // get paths of both files
    Path good =  Paths.get(goodstandings);
    Path bad = Paths.get(badstandings);

    String[] records = new String[2];
    
    // delimeter comma for csv-style storage
    // create recoreds if they dont exist
    try {
        if (!Files.exists(good)) {
          System.out.printf("File %s does not exist! creating...\n",good);
            Files.createFile(good);
        }
        if (!Files.exists(bad)) {
          System.out.printf("File %s does not exist! creating...\n",bad);
            Files.createFile(bad);
        }
    } catch (IOException e) {
      System.out.printf("Error creating file: %s",e.getMessage());
      // exit with non 0 exit code, cannot read or write to anything
      System.exit(1);
    }

    try {
      String good_record = Files.readString(good);
      String bad_record = Files.readString(bad);

      // move both records to array
      records[0] = good_record;
      records[1] = bad_record;
      
    } 
    // catch exceptions and exit if problem reading file
    catch(IOException e) {
      System.out.printf("Error opening file for reading!: %s",e.getMessage());
      // exit with non 0 exit code, cannot read or write to anything
      System.exit(1);
    }
    catch(Exception e) {
      System.out.printf("Error opening file for reading!: %s",e.getMessage());
      // exit with non 0 exit code, cannot read or write to anything
      System.exit(1);
    }

    return records;
  }

  

}
