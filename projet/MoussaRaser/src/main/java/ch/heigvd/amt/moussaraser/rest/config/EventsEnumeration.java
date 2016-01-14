package ch.heigvd.amt.moussaraser.rest.config;

public enum EventsEnumeration {
   AddPoints,
   AddBadge,
   AddReward,
   RemoveBadge,
   RemoveReward;
   
   public static boolean contains(String type) {
      boolean eventTypeOK = false;
      
      for(EventsEnumeration e : EventsEnumeration.values()) {
         if(e.name().equals(type)) {
            eventTypeOK = true;
            break;
         }
      }
      
      return eventTypeOK;
   }
}