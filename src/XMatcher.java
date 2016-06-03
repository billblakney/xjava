import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMatcher
{
   public static void main(String[] args)
   {
      simpleSearch();
      simpleSearchWithGroup();
      simpleSearchWithGroups();
      bewareTheGreedyDotStar();
      bewareTheGreedyDotStarWorkaround();
   }
   
   /**
    * Prints the summary of a match result.
    * @param aTitle
    * @param aLine
    * @param aPattern
    * @param aMatcher
    */
   static void printGroups(String aTitle,String aLine,String aPattern,Matcher aMatcher)
   {
      System.out.println("# " + aTitle);
      System.out.println("search string.... " + aLine);
      System.out.println("search pattern... " + aPattern);
      System.out.println("group(): " + aMatcher.group());

      /*
       * TRICKY:
       * Note that the value returned by groupCount doesn't include the
       * initial group(0), which always contains the whole match. So really
       * there are groupCount()+1 items in group().
       */
      for (int i = 0; i < aMatcher.groupCount()+1; i++)
      {
         System.out.println("group("+ i + "): " + aMatcher.group(i));
      }
      System.out.println("");
   }

   /**
    * Demonstrate basic Matcher operation.
    * No groups are defined in the search string, but group(0) will contain
    * the entire expression that was matched.
    */
   static void simpleSearch()
   {
      String tTitle = "simple search";
      String tLine = "abcd-000-efg";
      String tPatternString = "\\d+";
      Pattern tPattern = Pattern.compile(tPatternString);
      Matcher tMatcher = tPattern.matcher(tLine);
      if (tMatcher.find())
      {
         printGroups(tTitle,tLine,tPatternString,tMatcher);
      }
      else
      {
         System.out.println("Match not found!!!");
      }
   }

   /**
    * Demonstrate basic Matcher operation with a group.
    */
   static void simpleSearchWithGroup()
   {
      String tTitle = "search for a group of alpha surrounded by brackets";
      String tLine = "[someword]";
      String tPatternString = "\\[([a-zA-Z]+)\\]";
      Pattern tPattern = Pattern.compile(tPatternString);
      Matcher tMatcher = tPattern.matcher(tLine);
      if (tMatcher.find())
      {
         printGroups(tTitle,tLine,tPatternString,tMatcher);
      }
      else
      {
         System.out.println("Match not found!!!");
      }
   }

   /**
    * Demonstrate basic Matcher operation with multiple groups.
    */
   static void simpleSearchWithGroups()
   {
      String tTitle = "search for a group of alpha surrounded by brackets";
      String tLine = "[someword]";
      String tPatternString = "(\\[)([a-zA-Z]+)(\\])";
      Pattern tPattern = Pattern.compile(tPatternString);
      Matcher tMatcher = tPattern.matcher(tLine);
      if (tMatcher.find())
      {
         printGroups(tTitle,tLine,tPatternString,tMatcher);
      }
      else
      {
         System.out.println("Match not found!!!");
      }
   }


   /**
    * Demonstrate the greediness of the .*.
    */
   static void bewareTheGreedyDotStar()
   {
      String tTitle = "Beware the greedy .*";
      String tLine = "[header]  [another_header]";
      String tPatternString = "\\[(.*)\\]";
      Pattern tPattern = Pattern.compile(tPatternString);
      Matcher tMatcher = tPattern.matcher(tLine);
      if (tMatcher.find())
      {
         printGroups(tTitle,tLine,tPatternString,tMatcher);
      }
      else
      {
         System.out.println("Match not found!!!");
      }
   }

   /**
    * Show a way to avoid the greediness problem of .*.
    */
   static void bewareTheGreedyDotStarWorkaround()
   {
      String tTitle = "Workaround for the greedy .*";
      String tLine = "[header]  [another_header]";
      String tPatternString = "\\[([^\\[\\]]+)\\]";
      Pattern tPattern = Pattern.compile(tPatternString);
      Matcher tMatcher = tPattern.matcher(tLine);
      while (tMatcher.find())
      {
         printGroups(tTitle,tLine,tPatternString,tMatcher);
      }
   }
}