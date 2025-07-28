import javax.xml.stream.Location;
import java.time.LocalDate;
import java.util.Set;

public class Photo {
   private String id;
   private String title;
   private LocalDate date;
   private String location;
   private Set<String> tags;

   public Photo(String id , String title,LocalDate date,String location, Set<String> tags){
       this.id = id;
       this.title = title;
       this.date = date;
       this.location = location;
       this.tags = tags;

   }

   public String getId(){
       return id;
   }

   public String getTitle(){
       return title;
   }
   public LocalDate getDate(){
       return date;
   }
   public String getLocation(){
       return location;
   }
   public Set<String> getTags(){
       return tags;
   }

    @Override
    public  String toString() {
        return "photoId:"+id+ ", Title:"+ title +", Location:"+ location + ", TakenDate: "+date;
    }
}
