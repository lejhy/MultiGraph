import java.util.Set;
import java.util.HashSet;

public class Display {
   /**
    * <pre>
    *           0..*     0..*
    * Display ------------------------> Metro
    *           display        &gt;       metro
    * </pre>
    */
   private Set<Metro> metro;
   
   public Set<Metro> getMetro() {
      if (this.metro == null) {
         this.metro = new HashSet<Metro>();
      }
      return this.metro;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Display ------------------------> Controller
    *           display        &gt;       controller
    * </pre>
    */
   private Set<Controller> controller;
   
   public Set<Controller> getController() {
      if (this.controller == null) {
         this.controller = new HashSet<Controller>();
      }
      return this.controller;
   }

   
   }
