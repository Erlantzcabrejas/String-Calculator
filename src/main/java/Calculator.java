import java.util.stream.IntStream;

public class Calculator {
    static Integer add(String number){
        if(number == null || number.isEmpty()){
            return 0;
        }

        String[] values = number.split(",");
        int[] intValues = convertIntoInt(values);

        if(intValues.length > 1){
            return IntStream.of(intValues).sum();
        }

        return Integer.parseInt(number);
    }

    public static int[] convertIntoInt(String[] values){
        int[] integerValues = new int[values.length];

        for(int i = 0; i < values.length; i++){
            integerValues[i] = Integer.parseInt(values[i]);
        }

        return integerValues;
    }
}
