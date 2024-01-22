import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Calculator {

    public static Integer add(String input){
        String optionalDelimiter = "";
        Matcher matcher;

        if(input == null || input.isEmpty()){
            return 0;
        }

        if(input.startsWith("//")){
            matcher = checkIfDifferentDelimiterExists(input);
            optionalDelimiter = matcher.group(1);
            input = matcher.group(2);
        }

        String[] values = input.split("(,|\\n|"+optionalDelimiter+")+");
        int[] intValues = convertIntoInt(values);

        if(intValues.length == 0){
            return 0;
        }

        if(intValues.length > 1){
            return IntStream.of(intValues).sum();
        }

        return intValues[0];
    }

    public static int[] convertIntoInt(String[] values){
        int[] integerValues = new int[values.length];

        for(int i = 0; i < values.length; i++){
            if(!values[i].isEmpty()){
                integerValues[i] = Integer.parseInt(values[i]);
            }
        }

        return integerValues;
    }

    public static Matcher checkIfDifferentDelimiterExists(String input){
        String patternString = "/{2}(.)\\n(.*)";

        Pattern pattern = Pattern.compile(patternString);

        Matcher matcher = pattern.matcher(input);
        boolean matches = matcher.matches();
        String delimiter = matcher.group(1);
        String rest = matcher.group(2);

        return matcher;
    }

}
