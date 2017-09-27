import java.util.Set;
import java.util.HashSet;

public class Route {
   /**
    * <pre>
    *           0..*     0..*
    * Route ------------------------- Metro
    *           route        &lt;       metro
    * </pre>
    */
   private Set<Metro> metro;
   
   public Set<Metro> getMetro() {
      if (this.metro == null) {
         this.metro = new HashSet<Metro>();
      }
      return this.metro;
   }
   
   private int color;
   
   public void setColor(int value) {
      this.color = value;
   }
   
   public int getColor() {
      return this.color;
   }
   
   }
