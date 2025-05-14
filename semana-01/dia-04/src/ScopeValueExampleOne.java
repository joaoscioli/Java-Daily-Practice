public class ScopeValueExampleOne {
    public static final ScopedValue<User> LOGGED_IN_USER = ScopedValue.newInstance();

    public static void main(String[] args) {
        User user = new User("João", 25);
        ScopedValue.where(LOGGED_IN_USER, user).run(() -> {
            System.out.println("User: " + LOGGED_IN_USER.get());
        });
    }
}
