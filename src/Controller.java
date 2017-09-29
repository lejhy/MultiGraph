import java.util.Set;
import java.util.HashSet;

public class Controller {
	private Metro model;
	private Display view;
	
   /**
    * <pre>
    *           0..*     0..*
    * Controller ------------------------> Display
    *           controller        &gt;       display
    * </pre>
    */
	//Commenting these out for now probably don't need them
//   private Set<Display> display;
//   
//   public Set<Display> getDisplay() {
//      if (this.display == null) {
//         this.display = new HashSet<Display>();
//      }
//      return this.display;
//   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Controller ------------------------> Metro
    *           controller        &gt;       metro
    * </pre>
    */
	//Same as above
//   private Set<Metro> metro;
//   
//   public Set<Metro> getMetro() {
//      if (this.metro == null) {
//         this.metro = new HashSet<Metro>();
//      }
//      return this.metro;
//   }





   /* Initialise the Metro model and display
      Tell model to initialise all data necessary
      and send to menu to begin core loop
    */
   private void initialise() {
      view = new Display();
      model = new Metro();model
      model.initialise();
      menu();
   }

   /* Core loop which will continue until
      user has specified to exit
    */
   private void menu() {
	   boolean isRunning= true;
	   while()
	   view.printMenu();
	   int result = view.readInt();
	   boolean checkMenu = isValidMenuChoice(result);
	   
   }

   /* Prompt display to give the starting point
      and destination
    */
   private String[] retrieveStartAndEnd() {
      return null;
   }

   /* Take a list of options and
      make display print them out
    */
   private void printOptions() {
   }

   /* Send a request to find the
      best path between 2 stations
      and send the route to the
      display to be printed
    */
   private void findPath() {
   }


   private boolean isStation(String station) {
      return false;
   }

   private boolean isValidMenuChoice(int choice) {
      return false;
   }
 
   










}
