package treetable;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Vector;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.CheckBoxTreeTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.DoubleStringConverter;

/*
 * Mainly demonstrates creating different types of columns of a treetable,
 * including making the columns editable.
 */
public class XTreeTableView2 extends Application implements EventHandler<ActionEvent>
{
  Vector<TreeItem<TableEntry>> group1 = new Vector<TreeItem<TableEntry>>();
  Vector<TreeItem<TableEntry>> group2 = new Vector<TreeItem<TableEntry>>();

  TreeItem<TableEntry> tChild4;

  public static void main(String[] args)
  {
    Application.launch(args);
  }

  @Override
  public void start(Stage stage)
  {
    final Scene scene = new Scene(new Group(), 400, 300);
    Group sceneRoot = (Group) scene.getRoot();
    
    /*
     * Create the root item.
     */
    TreeItem<TableEntry> root = new TreeItem<>(new TableEntry());
    root.setExpanded(true);
    
    TreeItem<TableEntry> groupA = new TreeItem<>(
          new TableEntry(0,"Group A",10.0,new BigDecimal("9.99"),true));
    
    TreeItem<TableEntry> groupB = new TreeItem<>(
          new TableEntry(0,"Group B",99.0,new BigDecimal("0.00"),true));
    /*
     * Create the child items, and add them to root.
     */
    TreeItem<TableEntry> tItem;
    int i = 1;
    
    tItem = new TreeItem<>(
          new TableEntry(i++,"Joe",10.0,new BigDecimal("9.99"),true));
    group1.add(tItem);

    tItem = new TreeItem<>(
          new TableEntry(i++,"Mary",99.0,new BigDecimal("7.99"),true));
    group1.add(tItem);

    tItem = new TreeItem<>(
          new TableEntry(i++,"Harris",45.3,new BigDecimal("11.99"),true));
    group1.add(tItem);

    tItem = new TreeItem<>(
          new TableEntry(i++,"Tanya",33.3,new BigDecimal("33.99"),true));
    group2.add(tItem);

    tItem = new TreeItem<>(
          new TableEntry(i++,"Nancy",0.2,new BigDecimal("1.09"),true));
    group2.add(tItem);

    groupA.getChildren().setAll(group1);

    groupB.getChildren().setAll(group2);
    
    root.getChildren().setAll(groupA,groupB);

    /*
     * Create the id column.
     */
    TreeTableColumn<TableEntry,Integer> idColumn = new TreeTableColumn<>("Id");
    idColumn.setPrefWidth(100);
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
    nameColumn.setPrefWidth(100);
    nameColumn.setCellValueFactory(
       new Callback<CellDataFeatures<TableEntry,String>,ObservableValue<String>>()
       {
          @Override public ObservableValue<String>
          call(CellDataFeatures<TableEntry,String> p)
          {
             return p.getValue().getValue().someStringProperty();
          }
       }
     );
    
    // Add this to support editing. Otherwise, not needed.
    nameColumn.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());

    /*
     * Create the value column. Set it up to support editing.
     */
    TreeTableColumn<TableEntry,Double> valueColumn = new TreeTableColumn<>("Value");
    valueColumn.setPrefWidth(100);

    boolean useShortWay = false;
    if (useShortWay == true)
    {
       valueColumn.setCellValueFactory((p)-> 
       p.getValue().getValue().someDoubleProperty().asObject()
             );
    }
    else
    {
       valueColumn.setCellValueFactory(
             new Callback<CellDataFeatures<TableEntry,Double>,ObservableValue<Double>>() {
                @Override public ObservableValue<Double>
                call(CellDataFeatures<TableEntry,Double> p)
                {
                   // ?Note: SimpleDoubleProperty implements ObservableValue<Number>,
                   // so must use this workaround.
                   return p.getValue().getValue().someDoubleProperty().asObject();
                }
             });
    }
    
    // Add this to support editing. Otherwise, not needed.
    valueColumn.setCellFactory(
          TextFieldTreeTableCell.<TableEntry,Double>forTreeTableColumn(
                new DoubleStringConverter()));

    /*
     * Create the big decimal column. Set it up to support editing.
     */
    TreeTableColumn<TableEntry,BigDecimal> bonusColumn =
          new TreeTableColumn<>("Bonus");
    bonusColumn.setPrefWidth(100);
    bonusColumn.setCellValueFactory((p)-> {
          return p.getValue().getValue().someBigDecimalProperty(); });
    
    // Add this to support editing. Otherwise, not needed.
    boolean useUpdateItem = true;
    if (!useUpdateItem)
    {
       bonusColumn.setCellFactory(
          TextFieldTreeTableCell.<TableEntry,BigDecimal>forTreeTableColumn(
             new BigDecimalStringConverter()));
    }
    else
    {
       bonusColumn.setCellFactory((p)-> {
          return new TextFieldTreeTableCell<TableEntry,BigDecimal>(
                new BigDecimalStringConverter()) // need the converter to edit
          {
             @Override
             public void updateItem(BigDecimal item, boolean empty) {
                super.updateItem(item, empty);
                DecimalFormat df = new DecimalFormat("#,###.0000");
                if(empty || item == null){
                   setText("");
                } else {
                   setText(df.format(item));
                   this.setTextFill(Color.WHITE);
                   setStyle("-fx-background-color: blue");
                }
             }
             @Override public void startEdit() {

                super.startEdit();
// could this block be useful? how?
//                if (isEditing()) {
//                   textField.textProperty().addListener(rtf);
//                   if(getItem()!=null)
//                    textField.setText(getItem().toString());
//                      setGraphic(textField);
//                      textField.selectAll();
//                }
             }
          };}
       );
    }
    
    /*
     * Create the boolean column.
     */
    TreeTableColumn<TableEntry,Boolean> awardColumn =
          new TreeTableColumn<>( "Award" );
    awardColumn.setCellValueFactory((p)-> p.getValue().getValue().someBooleanProperty());
    awardColumn.setCellFactory(CheckBoxTreeTableCell.forTreeTableColumn(awardColumn));
    
    /*
     * Create the tree table view.
     */
    TreeTableView<TableEntry> treeTableView = new TreeTableView<>(root);
    treeTableView.setShowRoot(false);
    treeTableView.setEditable(true);
    nameColumn.setEditable(true);
    valueColumn.setEditable(true);
    bonusColumn.setEditable(true);
    awardColumn.setEditable(true);
    treeTableView.getColumns().add(idColumn);
    treeTableView.getColumns().add(nameColumn);
    treeTableView.getColumns().add(valueColumn);
    treeTableView.getColumns().add(bonusColumn);
    treeTableView.getColumns().add(awardColumn);
    
    Button dataButton = new Button("Data");
    dataButton.setOnAction(this);   

    sceneRoot.getChildren().add(treeTableView);
    sceneRoot.getChildren().add(dataButton);
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void handle(ActionEvent e)
  {
     System.out.println("HELLO");
     System.out.println(tChild4.toString());
  }
}
