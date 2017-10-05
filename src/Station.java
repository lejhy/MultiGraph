public class Station {
   
   private String name;
   private int id;
   
   public Station(int id, String name) {
	   this.name = name;
	   this.id = id;
   }
   
   public void setName(String value) {
      this.name = value;
   }
   
   public String getName() {
      return this.name;
   }

   public int getID() {
      return id;
   }

   public void setID(int id) {
      this.id = id;
   }
   
   }
