public class UserRecord {
    @Column(name = "user_id")
    private String id;

    @Column(name = "full_name")
    private String name;

    @Column(name = "email_address")
    private String email;

    @Override
    public String toString() {
        return "UserRecord[id=" + id + ", name=" + name + ", email=" + email + "]";
    }
}
