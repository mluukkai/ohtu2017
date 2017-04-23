import static org.junit.Assert.*;
import org.junit.Test;
import ohtu.Multiplier;

public class MultiplierTest {


    @Test
    public void kertominenToimii(){
      Multiplier v = new Multiplier(5);
      assertEquals(0,v.multipliedBy(0));
      assertEquals(5,v.multipliedBy(1));
      assertEquals(29,v.multipliedBy(6));
    }
}
