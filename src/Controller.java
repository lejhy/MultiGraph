import java.util.Set;
import java.util.HashSet;

public class Controller {
   /**
    * <pre>
    *           0..*     0..*
    * Controller ------------------------> Display
    *           controller        &gt;       display
    * </pre>
    */
   private Set<Display> display;
   
   public Set<Display> getDisplay() {
      if (this.display == null) {
         this.display = new HashSet<Display>();
      }
      return this.display;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Controller ------------------------> Metro
    *           controller        &gt;       metro
    * </pre>
    */
   private Set<Metro> metro;
   
   public Set<Metro> getMetro() {
      if (this.metro == null) {
         this.metro = new HashSet<Metro>();
      }
      return this.metro;
   }
   
   }
