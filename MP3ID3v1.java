package museDB;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;
import java.util.Set;

import javax.imageio.ImageIO;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException; 

public class MP3ID3v1 {
  
  static Hashtable getInfo(String fileName){
    
    Hashtable<String, String> tempMetaData = new Hashtable<String, String>();

    Mp3File mp3file;
    try {
      mp3file = new Mp3File(fileName);
      if (mp3file.hasId3v2Tag()) {
        ID3v2 id3v2Tag = mp3file.getId3v2Tag();
        if (id3v2Tag.getTrack() != null){
          tempMetaData.put("Track", (id3v2Tag.getTrack()));
        }
        if (id3v2Tag.getArtist() != null){
          tempMetaData.put("Artist", (id3v2Tag.getArtist()));
        }
        if (id3v2Tag.getTitle() != null){
          tempMetaData.put("Title", (id3v2Tag.getTitle()));
        }
        if (id3v2Tag.getAlbum() != null){
          tempMetaData.put("Album", (id3v2Tag.getAlbum()));
        }
        if (id3v2Tag.getYear() != null){
          tempMetaData.put("Year", (id3v2Tag.getYear()));
        }
        if ((Integer.toString(id3v2Tag.getGenre())) != null){
          tempMetaData.put("Genre", id3v2Tag.getGenre()+" (" + id3v2Tag.getGenreDescription() + ")");
        }
        
        byte[] imageData = id3v2Tag.getAlbumImage();
        if (imageData != null && id3v2Tag.getTitle() != null) {
            // Write image to file - can determine appropriate file extension from the mime type
            RandomAccessFile fileImage = new RandomAccessFile("C:\\Users\\kpari\\workspace\\museDB\\images\\"+(id3v2Tag.getTitle())+".jpg", "rw");
            fileImage.write(imageData);
            fileImage.close();
        }
      }
    }
    catch (UnsupportedTagException e) {
      e.printStackTrace();
    }
    catch (InvalidDataException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return tempMetaData;

  }
  public static void main(String[] arguments) { 
    Hashtable<String, String> metaData = getInfo("C:\\Users\\kpari\\workspace\\museDB\\Pretty_Lights_-_Rainbows___Waterfalls.mp3");
    Set<String> keys = metaData.keySet();
    for(String key: keys){
      if (metaData.get(key) != null) {
        System.out.println("Value of "+key+" is: "+metaData.get(key));
      }
    }
 } 
}


