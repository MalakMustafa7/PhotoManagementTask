import java.time.LocalDate;
import java.util.*;

public class PhotoManager {
    Map<String, List<Photo>> tagMap;
    Map<LocalDate, List<Photo>> dateMap;
    Map<String, List<Photo>> locationMap;

    public PhotoManager(){
        tagMap = new HashMap<>();
        dateMap = new HashMap<>();
        locationMap = new HashMap<>();
    }

    public void uploadPhoto(Photo photo){
          dateMap.computeIfAbsent(photo.getDate(),d->new ArrayList<>()).add(photo);
          locationMap.computeIfAbsent(photo.getLocation(), l->new ArrayList<>()).add(photo);
          for(String tag: photo.getTags()){
              tagMap.computeIfAbsent(tag.toLowerCase(),t->new ArrayList<>()).add(photo);
          }
    }
    public List<Photo> searchByDate(LocalDate date){
        return dateMap.get(date);
    }
    public List<Photo> searchByLocation(String location){
        return locationMap.get(location.toLowerCase());
    }
    public List<Photo> searchByTag(String tag){
        return tagMap.get(tag.toLowerCase());
    }
    public List<Photo> searchByMultipleTags(Set<String> tags){
        List<Photo> res = new ArrayList<>();
         for (String tag:tags){
             if(res.isEmpty()){
                 res = tagMap.get(tag.toLowerCase());
             }
              res.retainAll(tagMap.get(tag.toLowerCase()));
         }
         return res;
    }


}


}
