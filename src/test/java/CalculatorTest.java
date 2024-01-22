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
        Assert.assertTrue(Calculator.add("15,15") == 30);
    }

    @Test
    public void testCommaAndNewLineDelimiterInput() {
        Assert.assertTrue(Calculator.add( ",1") == 1);
        Assert.assertTrue(Calculator.add( "1,") == 1);
        Assert.assertTrue(Calculator.add( "1,,,,,,7,,,,8") == 16);
        Assert.assertTrue(Calculator.add( "1\n2,3") == 6);
        Assert.assertTrue(Calculator.add("4\n9") == 13);
        Assert.assertTrue(Calculator.add( "1,\n8") == 9);
    }

    @Test
    public void testNewOptionalDelimiterInput(){
        Assert.assertTrue(Calculator.add("//;\n1;2") == 3);
        Assert.assertTrue(Calculator.add("//%\n7%8%9%10") == 34);
        Assert.assertTrue(Calculator.add("//1\n71819110") == 24);
        Assert.assertTrue(Calculator.add("//1\n1111111") == 0);
    }
}
