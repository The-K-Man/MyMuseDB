package museDB;


import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.MediaPlayer;

public class EqualizerView {
  private static final double START_FREQ = 250.0;
  private static final int BAND_COUNT = 7;

  public EqualizerView(String songModel) {
//  createEQInterface();
}

protected Node initView() {
  final GridPane gp = new GridPane();
  gp.setPadding(new Insets(10));
  gp.setHgap(20);

  RowConstraints middle = new RowConstraints();
  RowConstraints outside = new RowConstraints();
  outside.setVgrow(Priority.ALWAYS);

  gp.getRowConstraints().addAll(outside, middle, outside);
  return gp;
}
/**
private void createEQInterface() {
  final GridPane gp = (GridPane) getViewNode();
  final MediaPlayer mp = mediaView.getMediaPlayer();

  createEQBands(gp, mp);
 }
 **/

 // To be continued...
}

