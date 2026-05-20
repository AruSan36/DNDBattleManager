
package ui;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Screen {
    
    private final BorderPane root;
    private final GridPane grid;
    private final Button[] boxButtons;
    private final Button createButton;
    private final Button deleteButton;

    public Screen() {
        root = new BorderPane();
        grid = new GridPane();
        boxButtons = new Button[12];
        createButton = new Button("Create");
        deleteButton = new Button("Delete");

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        HBox topBar = new HBox(10, createButton, deleteButton);
        topBar.setPadding(new Insets(20));

        root.setTop(topBar);
        root.setCenter(grid);
    }

    public Parent getRoot() {
        return root;
    }

    public Button[] getBoxButtons() {
        return boxButtons;
    }

    public Button getCreateButton() {
        return createButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

}
