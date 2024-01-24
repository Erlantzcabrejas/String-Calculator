import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertThrows;

public class CalculatorTest {


    @Test
    public void testBlankValue() throws Exception {
        Assert.assertNotNull(Calculator.add(""));
        Assert.assertTrue(Calculator.add("") == 0);
        Assert.assertTrue(Calculator.add(null) == 0);
    }

    @Test
    public void testSingleValue() throws Exception {
        Assert.assertTrue(Calculator.add("3") == 3);
        Assert.assertTrue(Calculator.add("7") == 7);
        Assert.assertTrue(Calculator.add("9") == 9);
    }

    @Test
    public void testTwoValues() throws Exception {
        Assert.assertTrue(Calculator.add("4,9") == 13);
        Assert.assertTrue(Calculator.add("9,7") == 16);
        Assert.assertTrue(Calculator.add("15,15") == 30);
    }

    @Test
    public void testCommaAndNewLineDelimiterInput() throws Exception {
        Assert.assertTrue(Calculator.add( ",1") == 1);
        Assert.assertTrue(Calculator.add( "1,") == 1);
        Assert.assertTrue(Calculator.add( "1,,,,,,7,,,,8") == 16);
        Assert.assertTrue(Calculator.add( "1\n2,3") == 6);
        Assert.assertTrue(Calculator.add("4\n9") == 13);
        Assert.assertTrue(Calculator.add( "1,\n8") == 9);
    }

    @Test
    public void testNewOptionalDelimiterInput() throws Exception {
        Assert.assertTrue(Calculator.add("//[;]\n1;2") == 3);
        Assert.assertTrue(Calculator.add("//[%]\n7%8%9%10") == 34);
        Assert.assertTrue(Calculator.add("//[1]\n71819110") == 24);
        Assert.assertTrue(Calculator.add("//[1]\n1111111") == 0);
    }

    @Test
    public void testNegativeValuesInput(){

        //First Case
        Exception exception = assertThrows(Exception.class, () -> {
            Calculator.add("-1,-2,-3,-4,-5");
        });

        ArrayList<String> negativeValues = new ArrayList<String>();
        negativeValues.add("-1");
        negativeValues.add("-2");
        negativeValues.add("-3");
        negativeValues.add("-4");
        negativeValues.add("-5");

        String expectedMessage = "Negatives not allowed: "+negativeValues.toString();
        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.equals(expectedMessage));

        //Second Case
        exception = assertThrows(Exception.class, () -> {
            Calculator.add("1,-2,3,-4,5");
        });

        negativeValues = new ArrayList<String>();
        negativeValues.add("-2");
        negativeValues.add("-4");

        expectedMessage = "Negatives not allowed: "+negativeValues.toString();
        actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.equals(expectedMessage));


        //Third Case
        exception = assertThrows(Exception.class, () -> {
            Calculator.add("-11,27,-34,4,-99");
        });

        negativeValues = new ArrayList<String>();
        negativeValues.add("-11");
        negativeValues.add("-34");
        negativeValues.add("-99");

        expectedMessage = "Negatives not allowed: "+negativeValues.toString();
        actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void testValuesHigherThan1000() throws Exception {
        Assert.assertTrue(Calculator.add("1001,2") == 2);
        Assert.assertTrue(Calculator.add("//[;]\n1;10000") == 1);
        Assert.assertTrue(Calculator.add("//[%]\n7%8000%9000%10") == 17);
    }

    @Test
    public void testAnyLengthDelimiters() throws Exception {
        Assert.assertTrue(Calculator.add("//[|||]\n1|||2|||3") == 6);
    }
}
