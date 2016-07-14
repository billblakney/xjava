package treetable;

import java.math.BigDecimal;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableEntry
{
   private SimpleIntegerProperty id;

   private SimpleStringProperty name;

   private SimpleDoubleProperty value;

   private SimpleObjectProperty<BigDecimal> amount;

   public TableEntry()
   {
   }

   public TableEntry(Integer id, String name, double value, BigDecimal amount)
   {
     this.id = new SimpleIntegerProperty(id);
     this.name = new SimpleStringProperty(name);
     this.value = new SimpleDoubleProperty(value);
     this.amount = new SimpleObjectProperty(amount);
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

   public SimpleObjectProperty<BigDecimal> amountProperty() {
     if (amount == null) {
       amount = new SimpleObjectProperty<BigDecimal>(this, "0.0");
     }
     return amount;
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

   public BigDecimal getAmount() {
     return amount.getValue();
   }

   public void setAmount(BigDecimal fName) {
     amount.set(fName);
   }
}
