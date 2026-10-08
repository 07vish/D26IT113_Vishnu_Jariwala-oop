import java.util.regex.Pattern;

public class PasswordChecker {
    private static final Pattern UPPER_CASE = Pattern.compile(".*[A-Z].*");
    private static final Pattern DIGIT = Pattern.compile(".*[0-9].*");
    private static final Pattern SPECIAL_CHAR = Pattern.compile(".*[^a-zA-Z0-9].*");

    public static boolean checkLength(String pw) {
        return pw != null && pw.length() >= 8;
    }

    public static boolean checkUpperCase(String pw) {
        return pw != null && UPPER_CASE.matcher(pw).matches();
    }

    public static boolean checkDigit(String pw) {
        return pw != null && DIGIT.matcher(pw).matches();
    }

    public static boolean checkSpecialChar(String pw) {
        return pw != null && SPECIAL_CHAR.matcher(pw).matches();
    }

    public static String strength(String pw) {
        int passed = 0;
        if (checkLength(pw)) passed++;
        if (checkUpperCase(pw)) passed++;
        if (checkDigit(pw)) passed++;
        if (checkSpecialChar(pw)) passed++;

        if (passed <= 1) return "Weak (" + passed + "/4)";
        if (passed <= 3) return "Medium (" + passed + "/4)";
        return "Strong (4/4)";
    }
}
