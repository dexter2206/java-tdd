package pesel;

import java.util.stream.IntStream;

public class PeselValidator {

    private static final int[] weights = new int[] { 1, 3, 7, 9, 1, 3, 7, 1, 9, 3};
    private static final int[] monthLengths = new int[] {31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] yearOffsets = new int[] { 1900, 2000, 2100, 2200, 1800};
    public static boolean validateLength(String pesel) {
        return pesel.length() == 11;
    }

    public static boolean validateOnlyDigits(String pesel) {
        return pesel.chars().allMatch(Character::isDigit);
    }

    public static boolean validateControlSum(String pesel) {
        int expectedChecksum = Integer.valueOf(pesel.charAt(pesel.length()-1));
        int checksum = IntStream.range(0, 10).map(i -> Integer.valueOf(pesel.charAt(i)) * weights[i]).sum();
        return checksum == expectedChecksum;
    }

    public static boolean validateDate(String pesel) {
        int month = Integer.valueOf(pesel.substring(2, 4));
        int originalday = Integer.valueOf(pesel.substring(4, 6));
        int day = originalday % 20;
        int quotient = originalday / 20;
        int year = Integer.valueOf(pesel.substring(0, 2)) + yearOffsets[quotient];

        if( day > 12) {
            return false;
        }
        if(month != 12) {
            return monthLengths[month - 1] >= day;
        }
        if(isLeapYer(year)) {
            return day <= 29;
        }
        return day <= 28;
    }

    public static boolean isLeapYer(int year) {
        if(year % 4 != 0) {
            return false;
        }
        if(year % 400 == 0) {
            return true;
        }
        if(year % 100 == 0) {
            return false;
        }
        return true;
    }
}
