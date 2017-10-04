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
                   findPathMenu();
               }
           }
       }

   }

   /* Prompt display to give the starting point
      and destination
      TODO: probably don't have to loop twice
    */


   private void findPathMenu() {
       ArrayList<Integer> stationIDsStart = new ArrayList<>();
       ArrayList<Integer> stationIDsEnd = new ArrayList<>();
       String start = "";
       String end = "";
       int choiceStart;
       int choiceEnd;
       while(stationIDsStart.size() < 1) {
           start = view.requestStation("Please enter your starting station");
           System.out.println(start);
           stationIDsStart = isStation(start);
           if (stationIDsStart.size() < 1)
               view.output("Sorry, that is not a valid station");
       }
       while(stationIDsEnd.size() < 1) {
           end = view.requestStation("Please enter your destination station");
           stationIDsEnd = isStation(end);
           if (stationIDsEnd.size() < 1)
               view.output("Sorry, that is not a valid station");
       }
       if (stationIDsStart.size() > 1)
           choiceStart = clarifyStationChoice(stationIDsStart, start);
       else
           choiceStart=1;
       if(stationIDsEnd.size()>1)
           choiceEnd = clarifyStationChoice(stationIDsEnd, end);
       else
           choiceEnd = 1;

       System.out.println(start+ " " + end +  " " + stationIDsStart.get(choiceStart-1) + " " + stationIDsEnd.get(choiceEnd-1));
       findPath(start, end, stationIDsStart.get(choiceStart-1), stationIDsEnd.get(choiceEnd-1));
   }


    private int clarifyStationChoice(ArrayList<Integer> stationIDs, String stationName) {
       int choice=-1;
        view.output("There appears to be more than one station with that name");
        ArrayList<ArrayList<String>> nearbyStations = new ArrayList<>();
        for (int id : stationIDs) {
            nearbyStations.add(model.getNearbyStations(id));
        }
        view.output("Would you like to select the " + stationName + " station next to");
        for (int i = 0; i < nearbyStations.size(); i++) {
            view.output(i + "");
            for (String station : nearbyStations.get(i)) {
                view.output(station + "and");
            }
            view.output("Or");
        }
        while (!isValidMenuChoice(choice, nearbyStations.size())) {
            view.output("Please provide a valid number between 1 and " + nearbyStations.size());
            choice = view.getMenuChoice();
        }
        return choice;
    }

    /* Send a request to find the
       best path between 2 stations
       and send the route to the
       display to be printed
     */

    private void findPath(String start, String end, int choiceStart, int choiceEnd) {
        String result = model.findPath(start, end, choiceStart, choiceEnd);
        view.output(result);
    }


   private ArrayList<Integer> isStation(String station) {
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
