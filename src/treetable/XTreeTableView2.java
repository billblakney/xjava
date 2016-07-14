package treetable;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.NumberStringConverter;

public class XTreeTableView2 extends Application {
  public static void main(String[] args) {
    Application.launch(args);
  }
  @Override
  public void start(Stage stage) {
    final Scene scene = new Scene(new Group(), 400, 300);
    Group sceneRoot = (Group) scene.getRoot();
    
    /*
     * Create the root item.
     */
    TreeItem<TableEntry> root = new TreeItem<>(new TableEntry());
    root.setExpanded(true);
    
    /*
     * Create the child items, and add them to root.
     */
    TreeItem<TableEntry> tChild1 = new TreeItem<>(new TableEntry(1,"Joe",10.0));
    TreeItem<TableEntry> tChild2 = new TreeItem<>(new TableEntry(2,"Mary",99.0));
    TreeItem<TableEntry> tChild3 = new TreeItem<>(new TableEntry(3,"Harris",45.3));

    root.getChildren().setAll(tChild1, tChild2, tChild3);

    /*
     * Create the id column.
     */
    TreeTableColumn<TableEntry,Integer> idColumn = new TreeTableColumn<>("Id");
    idColumn.setPrefWidth(150);
    idColumn.setCellValueFactory(
       new Callback<CellDataFeatures<TableEntry,Integer>,ObservableValue<Integer>>()
       {
          @Override public ObservableValue<Integer>
          call(CellDataFeatures<TableEntry,Integer> p)
          {
             // Note: SimpleIntegerProperty implements ObservableValue<Number>,
             // so must use this workaround.
             return p.getValue().getValue().idProperty().asObject();
          }
       }
     );

    /*
     * Create the name column. Set it up to support editing.
     */
    TreeTableColumn<TableEntry,String> nameColumn = new TreeTableColumn<>("Name");
    nameColumn.setPrefWidth(150);
    nameColumn.setCellValueFactory(
       new Callback<CellDataFeatures<TableEntry,String>,ObservableValue<String>>()
       {
          @Override public ObservableValue<String>
          call(CellDataFeatures<TableEntry,String> p)
          {
             return p.getValue().getValue().nameProperty();
          }
       }
     );
    
    // Add this to support editing. Otherwise, not needed.
    nameColumn.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());

    /*
     * Create the value column. Set it up to support editing.
     */
    // short way
    TreeTableColumn<TableEntry,Double> valueColumn = new TreeTableColumn<>("Value");
    valueColumn.setPrefWidth(150);
    valueColumn.setCellValueFactory((p)-> 
          p.getValue().getValue().valueProperty().asObject()
       );

    // long way
//    TreeTableColumn<TableEntry,Double> valueColumn = new TreeTableColumn<>("Value");
//    valueColumn.setPrefWidth(150);
//    valueColumn.setCellValueFactory(
//       new Callback<CellDataFeatures<TableEntry,Double>,ObservableValue<Double>>()
//       {
//          @Override public ObservableValue<Double>
//          call(CellDataFeatures<TableEntry,Double> p)
//          {
//             // ?Note: SimpleDoubleProperty implements ObservableValue<Number>,
//             // so must use this workaround.
//             return p.getValue().getValue().valueProperty().asObject();
//          }
//       }
//     );
    
    // Add this to support editing. Otherwise, not needed.
    valueColumn.setCellFactory(
          TextFieldTreeTableCell.<TableEntry,Double>forTreeTableColumn(
                new DoubleStringConverter()));

    /*
     * Create the tree table view.
     */
    TreeTableView<TableEntry> treeTableView = new TreeTableView<>(root);
    treeTableView.setEditable(true);
    valueColumn.setEditable(true);
    nameColumn.setEditable(true);
    treeTableView.getColumns().add(idColumn);
    treeTableView.getColumns().add(nameColumn);
    treeTableView.getColumns().add(valueColumn);
    sceneRoot.getChildren().add(treeTableView);
    stage.setScene(scene);
    stage.show();
  }
}
