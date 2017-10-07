import java.util.ArrayList;

public class Controller {
	private Metro model;
	private Display view;
	
   /** 
    * Initialise the Metro model and display 
    * Tell model to initialise all data necessary
    */
	
   public Controller() {
      view = new Display();
      model = new Metro();
      model.populate("data.txt");
   }

   /**
    * Core loop which will continue until the user has specified to exit
    */
   
   public void mainMenu() {
	   boolean isRunning= true;
	   while(isRunning) {
           view.output("\nWould you like to:\n1. Plan your route \n2. Exit");
           int result = view.getMenuChoice(2);
           if(result==2) {
               isRunning = false;
           } else if(result==1) {
               findPathMenu();
           }
       }

   }

   /**
    *  Prompt display to give the starting point and destination
    */

   private void findPathMenu() {
       ArrayList<Integer> startIds = new ArrayList<>();
       ArrayList<Integer> endIds = new ArrayList<>();
       String startName = "";
       String endName = "";
       int startIdIndex;
       int endIdIndex;
       
       while(startIds.size() < 1) {
    	   startName = view.requestStation("Please enter your starting station");
    	   startIds = model.getStationsOfSameName(startName);
           if (startIds.size() < 1)
               view.output("Sorry, that is not a valid station");
       }
       
       if (startIds.size() > 1) {
    	   startIdIndex = clarifyStationChoice(startIds, startName);
       } else {
    	   startIdIndex = 0;
       }
       
       while(endIds.size() < 1) {
    	   endName = view.requestStation("Please enter your destination station");
           endIds = model.getStationsOfSameName(endName);
           if (endIds.size() < 1)
               view.output("Sorry, that is not a valid station");
       }
       
       if(endIds.size() > 1) {
    	   endIdIndex = clarifyStationChoice(endIds, endName);
       } else {
    	   endIdIndex = 0;
       }
       
       if (startIds.get(startIdIndex) == endIds.get(endIdIndex)) {
    	   view.output("You are already on " + endName);
       } else {
    	   view.output(model.findPath(startIds.get(startIdIndex), endIds.get(endIdIndex)));
       }
   }


    private int clarifyStationChoice(ArrayList<Integer> stationIDs, String stationName) {
    	int choice;
        view.output("There appears to be more than one station with that name");
        ArrayList<ArrayList<String>> nearbyStations = new ArrayList<>();
        for (int id : stationIDs) {
            nearbyStations.add(model.getNearbyStations(id));
        }
        
        view.output("Would you like to select the " + stationName + " station next to:");
        for (int i = 0; i < nearbyStations.size(); i++) {
        	String stations = (i+1) + ". ";
            for (String station : nearbyStations.get(i)) {
                stations += station + ", ";
            }
            view.output(stations);
        }
        choice = view.getMenuChoice(nearbyStations.size());
        return choice-1;
    }
}