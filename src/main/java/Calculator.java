import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Calculator {

    public static Integer add(String input) throws Exception {
        String delimiters = "";
        Matcher matcher;
        String[] values;

        if(input == null || input.isEmpty()){
            return 0;
        }

        if(input.startsWith("//")){
            matcher = splitDelimitersAndValues(input);
            delimiters = matcher.group(1);
            ArrayList<String> cleanedDelimiters = extractDelimiters(delimiters);
            String regexSplitDelimiter = prepareDelimitersForSplitRegex(cleanedDelimiters);
            input = matcher.group(2);
            values = input.split(regexSplitDelimiter);
        } else {
            values = input.split("(,|\\n)+");
        }

        int[] intValues = convertIntoInt(values);

        if(intValues.length == 0){
            return 0;
        }

        return IntStream.of(intValues).sum();
    }

    /*
        Converts the values of the String input into array of int while handling negative values and values above 1000
     */
    private static int[] convertIntoInt(String[] values) throws Exception {
        int[] integerValues = new int[values.length];
        ArrayList<String> negativeValues = new ArrayList<String>();

        for(int i = 0; i < values.length; i++){
            if(!values[i].isEmpty() && Integer.parseInt(values[i]) < 1001){
                if(Integer.parseInt(values[i]) < 0){
                    negativeValues.add(values[i]);
                    continue;
                }

                integerValues[i] = Integer.parseInt(values[i]);
            }
        }

        if(!negativeValues.isEmpty()){
            throw new Exception("Negatives not allowed: " + negativeValues);
        }

        return integerValues;
    }

    /*
        Returns the delimiters and the values part of the input separated
     */
    private static Matcher splitDelimitersAndValues(String input) {
        String patternString = "\\/{2}(\\[.*\\])*\\n(.*)";

        Pattern pattern = Pattern.compile(patternString);

        Matcher matcher = pattern.matcher(input);
        boolean matches = matcher.matches();

        return matcher;
    }

    /*
        Extracts the delimiters without the []
     */
    private static ArrayList<String> extractDelimiters(String delimiters) {
        String delimiterPattern = "\\[([^\\]]+)\\]";
        Pattern pattern = Pattern.compile(delimiterPattern);

        Matcher matcher = pattern.matcher(delimiters);

        ArrayList<String> arrayOfDelimiters = new ArrayList<>();

        while(matcher.find()){
            arrayOfDelimiters.add(matcher.group(1));
        }

        return arrayOfDelimiters;
    }

    /*
        Returns regex ready to split the values input
     */
    private static String prepareDelimitersForSplitRegex(ArrayList<String> delimiters) {
        String optionalDelimiters = "(,|\\n";

        for (String delimiter: delimiters) {
            optionalDelimiters += "|";
            optionalDelimiters += Pattern.quote(delimiter);
        }

        optionalDelimiters += ")";

        return optionalDelimiters;
    }
}