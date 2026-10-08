import java.util.List;

public class Driver {
    public static void main(String[] args) {
        System.out.println("=== Reflection Form Validation ===");
        SignupForm invalidForm = new SignupForm("SuperLongDeveloperUsernameExceedingLimit", "");
        SignupForm validForm = new SignupForm("vishnu", "vishnu@charusat.ac.in");

        System.out.println("Validating Invalid Form:");
        List<String> errors = Validator.validate(invalidForm);
        for (String err : errors) {
            System.out.println("  [VALIDATION ERROR] " + err);
        }

        System.out.println("\nValidating Valid Form:");
        List<String> noErrors = Validator.validate(validForm);
        if (noErrors.isEmpty()) {
            System.out.println("  Form is 100% valid. No errors detected.");
        }
    }
}
