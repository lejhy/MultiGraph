import java.lang.reflect.Array;
import java.util.ArrayList;

public class Controller {
	private Metro model;
	private Display view;
   /* Initialise the Metro model and display
      Tell model to initialise all data necessary
      and send to menu to begin core loop
    */
   public Controller() {
      view = new Display();
      model = new Metro();
      model.populate("data.txt");
   }

   /* Core loop which will continue until
      user has specified to exit
    */
   public void menu() {
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
       ArrayList<Integer> stationIDs = new ArrayList<>();
       String start = "";
       String end = "";
       while(stationIDs.size()<1) {
           start = view.getStation("Please enter your starting station");
           stationIDs = isStation(start);
           if(stationIDs)
               view.output("Sorry, that is not a valid station");
       }

       stationIDs=false;
       while(!stationIDs && keepGoing) {
           end = view.getStation("Please enter your destination station");
           if(!keepGoing)
               break;
           stationIDs = isStation(end);
           if(!stationIDs)
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


   private ArrayList<Integer> isStation(String station) {
       if(station==null) {
       }
       ArrayList<Integer> stationIDList = model.getStationsOfSameName(station);
       return stationIDList;
   }

   private boolean isValidMenuChoice(int choice) {
      if(choice>0 && choice< 3)
          return true;
      else
          return false;
   }












}
