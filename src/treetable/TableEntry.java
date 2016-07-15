package treetable;

import java.math.BigDecimal;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableEntry
{
   private SimpleIntegerProperty id;

   private SimpleStringProperty name;

   private SimpleDoubleProperty rating;

   private SimpleObjectProperty<BigDecimal> bonus;
   
   private SimpleBooleanProperty award;

   public TableEntry()
   {
   }

   public TableEntry(Integer id, String name, double value, BigDecimal bonus,
         boolean award)
   {
     this.id = new SimpleIntegerProperty(id);
     this.name = new SimpleStringProperty(name);
     this.rating = new SimpleDoubleProperty(value);
     this.bonus = new SimpleObjectProperty(bonus);
     this.award = new SimpleBooleanProperty(award);
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

   public SimpleDoubleProperty ratingProperty() {
     if (rating == null) {
       rating = new SimpleDoubleProperty(this, "value");
     }
     return rating;
   }

   public SimpleObjectProperty<BigDecimal> bonusProperty() {
     if (bonus == null) {
       bonus = new SimpleObjectProperty<BigDecimal>(this, "0.0");
     }
     return bonus;
   }

   public SimpleBooleanProperty awardProperty() {
     if (award == null) {
       award = new SimpleBooleanProperty(this, "award");
     }
     return award;
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
     return rating.get();
   }

   public void setValue(double fName) {
     rating.set(fName);
   }

   public BigDecimal getBonus() {
     return bonus.getValue();
   }

   public void setBonus(BigDecimal fName) {
     bonus.set(fName);
   }

   public Boolean getAward() {
     return award.getValue();
   }

   public void setAward(Boolean fName) {
     award.set(fName);
   }
}
