import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Display {

   private static Scanner reader;
   /**
    * <pre>
    *           0..*     0..*
    * Display ------------------------> Metro
    *           display        &gt;       metro
    * </pre>
    */
	//Commenting these out for now probably don't need them
//   private Set<Metro> metro;
//   
//   public Set<Metro> getMetro() {
//      if (this.metro == null) {
//         this.metro = new HashSet<Metro>();
//      }
//      return this.metro;
//   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Display ------------------------> Controller
    *           display        &gt;       controller
    * </pre>
    */
   // Same as above
//   private Set<Controller> controller;
//   
//   public Set<Controller> getController() {
//      if (this.controller == null) {
//         this.controller = new HashSet<Controller>();
//      }
//      return this.controller;
//   }


   public Display() {
      reader = new Scanner(System.in);
   }

   public String readLine() {
      return reader.nextLine();
   }

   public int readInt() {
      return reader.nextInt();
   }


   public void printMenu() {
      output("Would you like to 1.Plan your route or 2.exit");
      output("Please enter the number of the option you require");
      printPrompt();
   }
   
   public String getStation(String s) {
	   output(s);
	   printPrompt();
	   return readLine();
   }



   public void output(String s) { System.out.println(s); }

   private void printPrompt() {System.out.print(">");}
}
