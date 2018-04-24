package pesel;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PeselValidator {

    private static int[] weights = new int[] {9, 7, 3, 1, 9, 7, 3, 1, 9, 7};
    private static int[] daysInMonth = new int[] {31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static int[] centuries = new int[] {1900, 2000, 2100, 2200, 1800};

    public static List<ValidationErrors> validate(String pesel) {
        List<ValidationErrors> errors = new ArrayList<>();
        if(!hasCorrectLength(pesel)) {
            errors.add(ValidationErrors.INVALID_LENGTH);
            return errors;
        }
        if(!hasOnlyDigits(pesel)) {
            errors.add(ValidationErrors.NOT_ONLY_DIGITS);
            return errors;
        }
        if(!hasCorrectChecksum(pesel)) {
            errors.add(ValidationErrors.INVALID_CHECKSUM);
        }
        if(!hasCorrectBirthDate(pesel)) {
            errors.add(ValidationErrors.INVALID_DATE);
        }
        return errors;
    }

    public static boolean hasCorrectLength(String pesel) {
        return pesel.length() == 11;
    }

    public static boolean hasOnlyDigits(String pesel) {
        //return pesel.chars().allMatch(x ->Character.isDigit(x));
        return pesel.chars().allMatch(Character::isDigit);
    }

    public static boolean hasCorrectChecksum(String pesel) {
        int expectedChecksum = Integer.valueOf(pesel.substring(pesel.length()-1));
        int actualChecksum = IntStream.range(0, 10)
                .map(i -> weights[i] * Integer.valueOf(pesel.substring(i, i+1)))
                .sum() % 10;
        return expectedChecksum == actualChecksum;
    }

    public static boolean hasCorrectBirthDate(String pesel) {
        int yearDigits = Integer.valueOf(pesel.substring(0, 2));
        int monthDigits = Integer.valueOf(pesel.substring(2, 4));
        int dayDigits = Integer.valueOf(pesel.substring(4, 6));
        int month = monthDigits % 20;
        if(month == 0 || month > 12) {
            return false;
        }
        if(month != 2) {
            return dayDigits <= daysInMonth[month-1] && dayDigits != 0;
        }
        int year = yearDigits + centuries[monthDigits / 20];
        if(year % 4 != 0 || (year % 4 == 0 && year % 100 == 0 && year % 400 != 0)) {
            return dayDigits <= 28 && dayDigits != 0;
        }
        return dayDigits <= 29 && dayDigits != 0;
    }
}
