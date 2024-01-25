import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertThrows;

public class CalculatorTest {


    @Test
    public void testBlankValue() throws Exception {
        Assert.assertTrue(Calculator.add("") == 0);
    }

    @Test
    public void testNullValue() throws Exception {
        Assert.assertTrue(Calculator.add(null) == 0);
    }

    @Test
    public void testSingleValue() throws Exception {
        Assert.assertTrue(Calculator.add("9") == 9);
    }

    @Test
    public void testTwoValues() throws Exception {
        Assert.assertTrue(Calculator.add("15,15") == 30);
    }

    @Test
    public void testNewLineInput() throws Exception {
        Assert.assertTrue(Calculator.add("4\n9") == 13);
    }

    @Test
    public void testCommaAndNewLineDelimiterInput() throws Exception {
        Assert.assertTrue(Calculator.add( "1\n2,3") == 6);
    }

    @Test
    public void testStartsWithDelimiterInput() throws Exception {
        Assert.assertTrue(Calculator.add( ",1") == 1);
    }

    @Test
    public void testEndsWithDelimiterInput() throws Exception {
        Assert.assertTrue(Calculator.add( "1,") == 1);
    }

    @Test
    public void testExtraDelimitersAreIgnoredInput() throws Exception {
        Assert.assertTrue(Calculator.add( "1,,,,,,7,,,,8") == 16);
    }

    @Test
    public void testNewOptionalDelimiterInput() throws Exception {
        Assert.assertTrue(Calculator.add("//[;]\n1;2") == 3);
    }

    @Test
    public void testNewOptionalNumberAsDelimiterInput() throws Exception {
        Assert.assertTrue(Calculator.add("//[1]\n71819110") == 24);
    }

    @Test
    public void testAllNegativeValuesInput() {
        Exception exception = assertThrows(Exception.class, () -> {
            Calculator.add("-1,-2,-3,-4,-5");
        });

        ArrayList<String> negativeValues = new ArrayList<String>();
        negativeValues.add("-1");
        negativeValues.add("-2");
        negativeValues.add("-3");
        negativeValues.add("-4");
        negativeValues.add("-5");

        String expectedMessage = "Negatives not allowed: " + negativeValues.toString();
        String actualMessage = exception.getMessage();

        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testHalfNegativeValuesInput() {
        Exception exception = assertThrows(Exception.class, () -> {
            Calculator.add("-11,27,-34,4,-99");
        });

        ArrayList<String> negativeValues = new ArrayList<String>();

        negativeValues.add("-11");
        negativeValues.add("-34");
        negativeValues.add("-99");

        String expectedMessage = "Negatives not allowed: " + negativeValues.toString();
        String actualMessage = exception.getMessage();

        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testValuesHigherThan1000() throws Exception {
        Assert.assertTrue(Calculator.add("//[%]\n7%8000%9000%10") == 17);
    }

    @Test
    public void testAnyLengthDelimiters() throws Exception {
        Assert.assertTrue(Calculator.add("//[|||]\n1|||2|||3") == 6);
    }

    @Test
    public void testManyDelimitersSingleLength() throws Exception {
        Assert.assertTrue(Calculator.add("//[|][%]\n1|2%3") == 6);
    }

    @Test
    public void testManyDelimitersAnyLength() throws Exception {
        Assert.assertTrue(Calculator.add("//[|||][%%%]\n1|||2%%%3") == 6);
    }

    @Test
    public void testManyDelimitersDifferentLengths() throws Exception {
        Assert.assertTrue(Calculator.add("//[||][%%%][!!!!!!!]\n1!!!!!!!2||3%%%200") == 206);
    }

}
