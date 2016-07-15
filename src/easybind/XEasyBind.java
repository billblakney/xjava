package easybind;

import javafx.beans.binding.Binding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Vector;
import org.fxmisc.easybind.EasyBind;
import org.fxmisc.easybind.monadic.MonadicBinding;

public class XEasyBind
{
   public class Item
   {
      private SimpleIntegerProperty id;
      private SimpleStringProperty name;
      private SimpleIntegerProperty score;
      
      public Item(int id,String name,int score)
      {
         this.id = new SimpleIntegerProperty(this,"id",id);
         this.name = new SimpleStringProperty(this,"name",name);
         this.score = new SimpleIntegerProperty(this,"score",score);
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

      public SimpleIntegerProperty scoreProperty() {
         if (score == null) {
            score = new SimpleIntegerProperty(this, "score");
         }
         return score;
      }
      
      public Integer getScore() {
         return score.get();
      }
   }
   
   Vector<Item> items;
   
   ObservableList<SimpleIntegerProperty> xscores;

   public XEasyBind()
   {
      Vector<Item> items = new Vector<Item>();
      for (int i = 0; i < 5; i++)
      {
         items.add(new Item(i,"item" + Integer.toString(i),10+i));
      }
      
      ObservableList<Item> observableItems = FXCollections.observableList(items);
      
      xscores = EasyBind.map(observableItems, Item::scoreProperty);
      
      for (SimpleIntegerProperty tInt: xscores)
      {
         System.out.println("score=" + tInt.intValue());
      }

      
      //works
      Binding<Integer> sum = EasyBind.combine(
            xscores,
            stream -> stream.mapToInt(Number::intValue).sum()); //mypreferred
//            stream -> stream.mapToInt(i -> i.intValue()).sum()); //equiv
      
//This modification works, though.
//      MonadicBinding<Object> sum = EasyBind.combine(
//            xscores,
//            stream -> stream.reduce((a, b) -> a.intValue() + b.intValue()).orElse(0));
//original from https://github.com/TomasMikula/EasyBind, but doesn't compile
//      Binding<Integer> sum = EasyBind.combine(
//            xscores,

      System.out.println("SUM=" + sum.getValue().toString());

   }

   public static void main(String[] args)
   {
      XEasyBind eb = new XEasyBind();
   }

}
