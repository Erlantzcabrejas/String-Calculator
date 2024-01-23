import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Calculator {

    public static Integer add(String input) throws Exception {
        String optionalDelimiter = "";
        Matcher matcher;
        String[] values;

        if(input == null || input.isEmpty()){
            return 0;
        }

        if(input.startsWith("//")){
            matcher = checkIfDifferentDelimiterExists(input);
            optionalDelimiter = matcher.group(1);
            input = matcher.group(2);
            values = input.split("(,|\\n|"+optionalDelimiter+")+");
        }else{
            values = input.split("(,|\\n)+");
        }

        int[] intValues = convertIntoInt(values);

        if(intValues.length == 0){
            return 0;
        }

        if(intValues.length > 1){
            return IntStream.of(intValues).sum();
        }

        return intValues[0];
    }

    public static int[] convertIntoInt(String[] values) throws Exception {
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
            throw new Exception("Negatives not allowed: "+negativeValues.toString());
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