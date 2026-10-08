public class CustomResource implements AutoCloseable {
    private final String resourceName;

    public CustomResource(String resourceName) {
        this.resourceName = resourceName;
        System.out.println("Resource [" + resourceName + "] successfully opened.");
    }

    public void performAction(boolean throwError) throws Exception {
        if (throwError) {
            throw new IllegalStateException("Critical failure during operation on " + resourceName);
        }
        System.out.println("Resource [" + resourceName + "] performed scheduled task successfully.");
    }

    @Override
    public void close() {
        System.out.println("Resource [" + resourceName + "] closed gracefully via try-with-resources.");
    }
}
