import static org.junit.Assert.*;
import org.junit.Test;

public class JavaTest{
   protected int value1, value2;

   // test method to add two values
   @Test
   public void testAdd(){
      int value1 = 3;
      int value2 = 4;
      double result = value1 + value2;
      assertTrue(result == 7);
   }
}