package application.Util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

/**
 * Created by ZQ on 2017/5/16.
 */
public class CompContextMenu extends ContextMenu {
    private MenuItem item = new MenuItem("delete");

    public CompContextMenu(Node node) {
        super();
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (node instanceof BaseCom) {
                    ((BaseCom) node).delete();
                }
            }
        });
        getItems().add(item);
    }
}
