import java.util.Set;
import java.util.HashSet;

public class Station {
   /**
    * <pre>
    *           0..*     0..*
    * Station ------------------------- Metro
    *           station        &gt;       metro
    * </pre>
    */
   private Set<Metro> metro;
   
   public Set<Metro> getMetro() {
      if (this.metro == null) {
         this.metro = new HashSet<Metro>();
      }
      return this.metro;
   }
   
   private String name;
   
   public void setName(String value) {
      this.name = value;
   }
   
   public String getName() {
      return this.name;
   }
   
   private int positionX;
   
   public void setPositionX(int value) {
      this.positionX = value;
   }
   
   public int getPositionX() {
      return this.positionX;
   }
   
   private int positionY;
   
   public void setPositionY(int value) {
      this.positionY = value;
   }
   
   public int getPositionY() {
      return this.positionY;
   }
   
   }
