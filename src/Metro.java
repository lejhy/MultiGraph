import java.util.Set;
import java.util.HashSet;

public abstract class Metro {
   /**
    * <pre>
    *           0..*     0..*
    * Metro ------------------------- Station
    *           metro        &lt;       station
    * </pre>
    */
   private Set<Station> station;
   
   public Set<Station> getStation() {
      if (this.station == null) {
         this.station = new HashSet<Station>();
      }
      return this.station;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Metro ------------------------- Route
    *           metro        &gt;       route
    * </pre>
    */
   private Set<Route> route;
   
   public Set<Route> getRoute() {
      if (this.route == null) {
         this.route = new HashSet<Route>();
      }
      return this.route;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Metro ------------------------> MultiGraph
    *           metro        &gt;       multiGraph
    * </pre>
    */
   private Set<MultiGraph> multiGraph;
   
   public Set<MultiGraph> getMultiGraph() {
      if (this.multiGraph == null) {
         this.multiGraph = new HashSet<MultiGraph>();
      }
      return this.multiGraph;
   }
   
   }
