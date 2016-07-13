package iterate;


public class SmallClass {
   public int a;
   public int b;
   public String name;
   
   public SmallClass(){
      a = b = 0;
      name = "";
   }
   
   public SmallClass(int a, int b, String name){
      this.a = a;
      this.b = b;
      this.name = name;
   }
   
   public String toString(){
      return name + ":" + a + "," + b; 
   }
}

