import java.util.Scanner;

public class Display {

   private static Scanner reader;

   public Display() {
      reader = new Scanner(System.in);
   }

   public String readLine() {
      reader = new Scanner(System.in);
      return reader.nextLine().trim();
   }

   public int getMenuChoice() { return reader.nextInt();
   }


   public void printMenu() {
      output("Would you like to 1.Plan your route or 2.exit");
      output("Please enter the number of the option you require");
      printPrompt();
   }

   
   public String requestStation(String line) {
      output(line);
	   printPrompt();
	   return readLine();
   }



   public void output(String s) { System.out.println(s); }

   private void printPrompt() {System.out.print(">");}
}
