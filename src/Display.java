import java.util.NoSuchElementException;
import java.util.Scanner;

public class Display {

   private static Scanner reader;

   public Display() {
      reader = new Scanner(System.in);
   }

   public String readLine() {
      try {
         reader = new Scanner(System.in);
         return reader.nextLine().trim();
      }
      catch(NoSuchElementException a) {
         output("Exiting in response to CTRL+D");
         System.exit(0);
      }
      return null;
   }

   public int getMenuChoice(int limit) {
	  int choice;
	  
	  output("Please enter the number of the option you require");
	  printPrompt();
      while(true) {
         try {
            choice = Integer.parseInt(reader.next());
            if(choice < 1 || choice > limit) {
               output("Sorry, that number is not an option. Please enter a number between 1 and " + limit + " for this menu");
               printPrompt();
            }
            else {
               return choice;
            }
         }
         catch (NumberFormatException e) {
            output("Sorry, this menu will only accept whole numbers. Please enter a valid menu choice");
            printPrompt();
         }
         catch (NoSuchElementException e) {
            output("Exiting in response to CTRL+D");
            System.exit(0);
         }
      }
   }
   
   public String requestStation(String line) {
      output(line);
	  printPrompt();
	  return readLine();
   }



   public void output(String s) { System.out.println(s); }

   private void printPrompt() {System.out.print(">");}
}
