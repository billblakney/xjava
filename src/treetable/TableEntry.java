package treetable;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableEntry
{
   private SimpleIntegerProperty id;

   private SimpleStringProperty name;

   private SimpleDoubleProperty value;

   public TableEntry()
   {
   }

   public TableEntry(Integer id, String name, double value)
   {
     this.id = new SimpleIntegerProperty(id);
     this.name = new SimpleStringProperty(name);
     this.value = new SimpleDoubleProperty(value);
   }

   public SimpleIntegerProperty idProperty() {
     if (id == null) {
       id = new SimpleIntegerProperty(this, "id");
     }
     return id;
   }

   public SimpleStringProperty nameProperty() {
     if (name == null) {
       name = new SimpleStringProperty(this, "name");
     }
     return name;
   }

   public SimpleDoubleProperty valueProperty() {
     if (value == null) {
       value = new SimpleDoubleProperty(this, "value");
     }
     return value;
   }

   public Integer getId() {
     return id.get();
   }

   public void setId(Integer fName) {
     id.set(fName);
   }

   public String getName() {
     return name.get();
   }

   public void setName(String fName) {
     name.set(fName);
   }

   public double getValue() {
     return value.get();
   }

   public void setValue(double fName) {
     value.set(fName);
   }
}
