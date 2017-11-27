/*
 */
package museDB;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
//import java.beans.EventHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import java.awt.event.ActionEvent;
import javafx.geometry.Pos;

//import java.nio.file.Files;
import java.io.File;
import javafx.stage.FileChooser;
import java.util.*;

import javax.swing.event.ChangeListener;

import javafx.scene.media.AudioSpectrumListener;


/**
 *
 * @author Lloyd Cloer
 */

public class MuseDB extends Application {
  Player player;
  VBox song_listing;
  
  MediaPlayer media_player;      
  AudioSpectrumListener audioSpecListener;
  Group Vidroot = new Group();
  static StackPane Vroot = new StackPane();
  static GridPane gridpane = new GridPane();
  static Stage worldStage;

  
  public static void main(String[] args) {
      String s = "C:\\Users\\Lloyd Cloer\\Music\\Aesthetic Perfection\\Close To Human\\03 Architech.mp3";
      File file = new File(s);
  //    Media media = new Media(s);
      
     // System.out.print
   //   file = new File("file:/C:/Users/Lloyd%20Cloer/Music/AFI/AFI/01%20The%20Lost%20Souls.mp3");
   //   Player p = new Player();
     // p.selectSong(file);
     // p.play();
     // FileCoordinator.copyFile(file, "C:\\Users\\Lloyd Cloer\\Music\\");
    //  System.out.println(FileCoordinator.extractMetadata(file));
   //   Media media = new Media(file.toURI().toString());
   //MediaPlayer media_player = new MediaPlayer(media);
     //     System.out.println(media.getMetadata());
      
      if (true){
          
          MuseDB muse = new MuseDB();
          muse.launch();
      }
  }
  
  public void start(Stage stage) {
      Visualizer vis = new Visualizer();
      worldStage = stage;
      audioSpecListener = new AudioSpectrumListener() {
      @Override
      public void spectrumDataUpdate(double timestamp, double duration,
              float[] magnitudes, float[] phases) {
              vis.updateCircs(magnitudes);
              vis.updateVtriangs(magnitudes);
              System.out.println(magnitudes[0] +" " + magnitudes[2] + " " +magnitudes[4]);
      }
      };
      
      //CHANGED TO NEED ARG
      // *** Initialize Model Classes *** //
      File file = new File("C:\\Users\\kpari\\workspace\\MymuseDB\\src\\museDB\\omgrobots.flv");
      Media media = new Media(file.toURI().toString());
      media_player = new MediaPlayer(media);
      player = new Player(media_player);
      


//      File file = new File("C:/Users/Lloyd%20Cloer/Music/AFI/AFI/01%20The%20Lost%20Souls.mp3");
//      player.selectSong(file);
  //    player.play();
      FileCoordinator fc = new FileCoordinator();
      
      
      stage.setTitle("MuseDB");
      
      BorderPane root = new BorderPane();
      
      
      
      VBox control_panel = new VBox();
      root.setLeft(control_panel);
      
      
      
      song_listing = new VBox();
      //root.setCenter(song_listing);
      
      
      ScrollPane sp = new ScrollPane();
      sp.setContent(song_listing);
      root.setCenter(sp);
      
      // **** File Import *** // 
      FileChooser file_chooser = new FileChooser();
      
      
      
      // *** Menu Bar *** //
      final Menu file_menu = new Menu("File");
      MenuItem import_item = new MenuItem("Import");
      import_item.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
              List<File> list = file_chooser.showOpenMultipleDialog(stage);
              fc.importFiles(list);
              listSongs(list);
          }
      });
      file_menu.getItems().add(import_item);
      
      final Menu menu2 = new Menu("Options");
      final Menu menu3 = new Menu("Help");

      MenuBar menu_bar = new MenuBar();
      menu_bar.getMenus().addAll(file_menu, menu2, menu3);
      
      root.setTop(menu_bar);
      //root
      
      //******* Test Player *******/
      
      Button play_button = new Button();
      play_button.setText("Play");
      play_button.setOnAction(new EventHandler<ActionEvent>() {
          @Override public void handle(ActionEvent e) {
              if (play_button.getText() == "Play"){
                  File file = new File("C:\\Users\\kpari\\workspace\\MymuseDB\\src\\museDB\\03 Understand Me Now 1.mp3");
                  player.selectSong(file);
                  player.play();
                  player.media_player.setAudioSpectrumListener(audioSpecListener);
                  player.media_player.setAudioSpectrumInterval(0.1);
                  player.media_player.setAudioSpectrumThreshold(-100);
                  player.media_player.setAudioSpectrumNumBands(10);
                  play_button.setText("Pause");
              }
              else if (play_button.getText() == "Resume") {
                player.play();
                play_button.setText("Pause");
              }
              else {
                  player.pause();
                  play_button.setText("Resume");
              }
          }
      });
      
      //StackPane Vroot = new StackPane();

      BorderPane VReturnRoot = new BorderPane();

      
      Button returnButton = new Button();
      returnButton.setText("Return");
      VReturnRoot.setTop(menu_bar);
      
      returnButton.setOnAction(new EventHandler<ActionEvent>() {
          Scene sceneHome = new Scene(VReturnRoot, 300, 250); 
          @Override public void handle(ActionEvent e) {
            media_player.pause();
            stage.setScene(sceneHome);
            stage.show();
          }
      });

      
              
      Button playVideo = new Button();
      playVideo.setText("Pause");
      playVideo.setOnAction(new EventHandler<ActionEvent>() {
          @Override public void handle(ActionEvent e) {
              if (playVideo.getText() == "Play"){
                  media_player.play();
                  playVideo.setText("Pause");
              }
              else {
                  media_player.pause();
                  playVideo.setText("Play");
              }
          }
      });
      
      
      Button playVis = new Button();
      playVis.setText("Visualizer");
      playVis.setOnAction(new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
          stage.setScene(vis.scene);
          stage.setTitle("VISUALIZER");
          stage.show();
          
        }
      });
      
      Button visual_button = new Button();
      visual_button.setText("Visual");

      
      Button video_button = new Button();
      video_button.setText("Start Video");
      video_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
              if (video_button.getText() == "Start Video"){
                  if (play_button.getText() == "Pause"){
                  player.pause();
                  }
                  File file = new File("C:\\Users\\kpari\\workspace\\MymuseDB\\src\\museDB\\omgrobots.flv");
                  Media media = new Media(file.toURI().toString());
                  media_player = new MediaPlayer(media);                  
                  MediaView mediaView = new MediaView(media_player);
                  mediaView.setMediaPlayer(media_player);
                  /**
                  Vroot.getChildren().add(mediaView);
                  Vroot.getChildren().add(playVideo);
                  Vroot.getChildren().add(returnButton);
                  Vroot.setAlignment(returnButton, Pos.BOTTOM_LEFT);
                  Vroot.setAlignment(playVideo, Pos.BOTTOM_CENTER);
  **/
                  Player mediaControl = new Player(media_player);
                  Scene scene = new Scene(Vidroot, 960, 540);
                  scene.setRoot(mediaControl);
                  stage.setScene(scene);
                  media_player.play();
                  stage.setTitle("VIDPLAYER");
                  stage.show();
                  //player.selectVideo(file, stage);
                  media_player.play();
              }
          }
      });
      
      control_panel.getChildren().add(visual_button);
      control_panel.getChildren().add(video_button);
      control_panel.getChildren().add(play_button);
      control_panel.getChildren().add(playVis);
      
      // Test playing music
      
      //player.selectSong();  
     // player.play();
      
      // Test moving files
      String a1 = "C:\\Users\\Lloyd Cloer\\Documents\\Java Test\\Green Neuron.jpg";
      String a2 = "C:\\Users\\Lloyd Cloer\\Documents\\Java Test\\the folder\\Green Neuron.jpg";
    //  FileCoordinator.moveFile(a2, a1);

      stage.setScene(new Scene(root, 300, 250));
      stage.show();
      
  }
  //Main screen recreation should return a scene
  /**
  public void returningMainScreen(){
    VReturnRoot.getChildren().add(visual_button);
    VReturnRoot.getChildren().add(video_button);
    VReturnRoot.getChildren().add(play_button);
  }
  **/
  public void listSongs(List<File> list){
      for (File f : list){
          SongButton b = new SongButton();
          b.setText(f.getName());
          song_listing.getChildren().add(b);
      }
  }
  
  public static void backToHome(){
    Group root = new Group();
    Scene scene = new Scene(root, 1000, 700, Color.BLACK);
      worldStage.setScene(scene);
      worldStage.show();
  }
  

  
  public class SongButton extends Button{
      
      public SongButton(){
          setOnAction(
              new EventHandler<ActionEvent>() {
                  @Override 
                  public void handle(ActionEvent e) {
                      File f = FileCoordinator.song_table.get(getText());
                      player.selectSong(f);
                  }
              }
          );
      }
  }
  
  
}