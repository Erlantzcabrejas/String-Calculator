import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    @Test
    public void testBlankValue() {
        Assert.assertNotNull(Calculator.add(""));
        Assert.assertTrue(Calculator.add("") == 0);
        Assert.assertTrue(Calculator.add(null) == 0);
    }

    @Test
    public void testSingleValue() {
        Assert.assertTrue(Calculator.add("3") == 3);
        Assert.assertTrue(Calculator.add("7") == 7);
        Assert.assertTrue(Calculator.add("9") == 9);
    }

    @Test
    public void testTwoValues() {
        Assert.assertTrue(Calculator.add("4,9") == 13);
        Assert.assertTrue(Calculator.add("9,7") == 16);
        Assert.assertTrue(Calculator.add("9,7") == 16);
    }
}
