package client.gui.panes;

import client.gui.Gui;
import client.gui.pane_controller.GuiController;
import client.logic.LogicEvents;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import skat.log.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

public class CardPane extends HBox {

    ArrayList<Label> labels = new ArrayList<>();
    double width;
    double height;

    public CardPane(double screenWidth, double screenHeight) {
        width = GuiController.getInstance().getWidth();
        height = GuiController.getInstance().getHeight();
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: #349746");
        this.setSpacing(-(width/3));
        this.setHeight(height);
    }

    public void addCards(String[] cardsUrls) {
        emptyHand();
        for(String card:cardsUrls) {
            Label label = new Label();
            label.setAlignment(Pos.TOP_LEFT);
            ImageView view = null;
            try {
                Image img = new Image(Gui.class.getResource(card).openStream(), width, height, true, false);
                view = new ImageView(img);
                Rectangle2D rec = new Rectangle2D(0,0,width,(height/3*2));
                view.setViewport(rec);
            } catch(IOException e) {
                Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            }
            label.setGraphic(view);
            label.setOnMouseClicked(e -> {
                boolean cardPlayable = LogicEvents.getInstance().playCard(card);
                if(cardPlayable) {
                    labels.remove(label);
                    this.getChildren().remove(label);
                }
            });
            labels.add(label);
            this.getChildren().add(label);
        }
    }

    public void emptyHand() {
        labels.clear();
        this.getChildren().clear();
    }
}
