package iterate;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.TreeMap;

/*
 * Demonstrate iteration over containers.
 * 
 * Note: Uses SmallClass
 * TODO fill out. this is just an incomplete start.
 */
public class Iterate
{
   static public void main(String[] args)
   {
      System.out.println("Hello world!");
      for (int i = 0; i < args.length; i++)
      {
         System.out.println(args[i]);
      }
      iterateOverVector();
      iterateOverTreeMap();
   }

   static public void iterateOverVector()
   {
      Vector<SmallClass> v = new Vector<SmallClass>();
      v.add(new SmallClass(1, 11, "ones"));
      v.add(new SmallClass(2, 22, "twos"));

      for (SmallClass s : v)
      {
         // System.out.println(s.a + "," + s.b + "," + s.name);
         System.out.println(s.toString());
      }
   }

   static public void iterateOverTreeMap()
   {
      TreeMap<String, SmallClass> tm = new TreeMap<String, SmallClass>();
      tm.put("oneone", new SmallClass(1, 11, "ones"));
      tm.put("twotwo", new SmallClass(2, 22, "twos"));
      tm.put("tretre", new SmallClass(3, 33, "tres"));

      SmallClass sc2 = tm.get("twotwo");
      System.out.println("looking for sc2: " + sc2.toString());

      // TreeMap<String,SmallClass>::iterator it = tm.iterator();
      // Iterator it = tm.keySet().iterator();
      for (String key : tm.keySet())
      {
         SmallClass sc = tm.get(key);
         System.out.println(key + ":" + tm.get(key));
      }

      for (Map.Entry<String, SmallClass> entry : tm.entrySet())
      {
         String s = entry.getKey();
         SmallClass sc = entry.getValue();
         System.out.println("key,value=" + s + "::" + sc.toString());
      }

      /*
       * Iterator entries = tm.entrySet().iterator(); //Iterator<String> it =
       * tm.keySet().iterator(); while( entries.hasNext() ){ SmallClass sc =
       * tm.get(it); System.out.println("key,value=" + it + "::" +
       * sc.toString()); }
       */
   }
}
