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
           int result = view.getMenuChoice();
           boolean checkMenu = isValidMenuChoice(result, 2);
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
           if (stationIDs.size() < 1)
               view.output("Sorry, that is not a valid station");
           else if (stationIDs.size() > 1) {
               view.output("There appears to be more than one station with that name");
               ArrayList<ArrayList<String>> nearbyStations = new ArrayList<>();
               for (int id : stationIDs) {
                   nearbyStations.add(model.getNearbyStations(id));
               }

               view.output("Would you like to select the " + start + " next to");
               for (int i = 0; i < nearbyStations.size(); i++) {
                   view.output(i + "");
                   for (String station : nearbyStations.get(i)) {
                       view.output(station + "and");
                   }
                   view.output("Or");
               }
               int choice = -1;
               while (isValidMenuChoice(choice, nearbyStations.size())) {
                   view.output("Please provide a valid number between 1 and " + nearbyStations.size());
                   choice = view.getMenuChoice();
               }






           }
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

   private boolean isValidMenuChoice(int choice, int limit) {
      if(choice>0 && choice<= limit)
          return true;
      else
          return false;
   }












}
