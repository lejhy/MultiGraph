import java.util.Set;
import java.util.HashSet;

public class Controller {
	private Metro model;
	private Display view;
	private boolean keepGoing;

   /* Initialise the Metro model and display
      Tell model to initialise all data necessary
      and send to menu to begin core loop
    */
   private void initialise() {
      view = new Display();
      model = new Metro();
      model.initialise();
      keepGoing=true;
      menu();
   }

   /* Core loop which will continue until
      user has specified to exit
    */
   private void menu() {
	   boolean isRunning= true;
	   while(isRunning) {
           view.printMenu();
           int result = view.readInt();
           boolean checkMenu = isValidMenuChoice(result);
           if(checkMenu) {
               if(result==2)
                   isRunning=false;
               else if(result==1) {
                   String[] startAndEnd = retrieveStartAndEnd();
                   if(!keepGoing) {
                       keepGoing=true;
                       continue;
                   }
                   findPath(startAndEnd);
               }
           }
       }

   }

   /* Prompt display to give the starting point
      and destination
      TODO: probably don't have to loop twice
    */
   private String[] retrieveStartAndEnd() {
       boolean checkStation = false;
       String start = "";
       String end = "";
       while(!checkStation && keepGoing) {
           start = view.getStation("Please enter your starting station");
           if(!keepGoing)
               break;
           checkStation = isStation(start);
           if(!checkStation)
               view.output("Sorry, that is not a valid station");
       }

       checkStation=false;
       while(!checkStation && keepGoing) {
           end = view.getStation("Please enter your destination station");
           if(!keepGoing)
               break;
           checkStation = isStation(end);
           if(!checkStation)
               view.output("Sorry, that is not a valid station");
       }

       String[] stations = new String[2];
       stations[0] = start;
       stations[1] = end;
       return stations;


   }


   /* Send a request to find the
      best path between 2 stations
      and send the route to the
      display to be printed
    */
   private void findPath(String[] stations) {
       model.findPath(stations[0], stations[1]);

   }


   private boolean isStation(String station) {
       if(station==null) {
           return false;
       }
      if(model.getStation(station)!=null)
          return true;
      else
          return false;
   }

   private boolean isValidMenuChoice(int choice) {
      if(choice>0 && choice< 3)
          return true;
      else
          return false;
   }












}
