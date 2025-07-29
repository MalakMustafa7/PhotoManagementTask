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

    public List<Photo> searchByCoordinates(double latitude,double longitude,double radius){
        List<Photo> res = new ArrayList<>();
        for(List<Photo> photos : locationMap.values()){
            for(Photo photo : photos){
                double distance = calculateDistance(latitude,photo.getLatitude(),longitude,photo.getLongitude());
                if(distance<=radius){
                    res.add(photo);
                }
             }
            if (!res.isEmpty()) {
                break;
            }
        }
        return res;
    }

    private double calculateDistance(double lat1, double lat2, double lon1, double lon2) {
        final int R = 6371; // Radius of Earth in km

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}

