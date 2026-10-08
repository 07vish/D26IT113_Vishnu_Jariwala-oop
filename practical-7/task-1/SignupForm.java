public class SignupForm {
    @NotBlank(message = "Username is mandatory")
    @MaxLength(value = 10, message = "Username cannot exceed 10 characters")
    private String username;

    @NotBlank(message = "Email is mandatory")
    private String email;

    public SignupForm(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
