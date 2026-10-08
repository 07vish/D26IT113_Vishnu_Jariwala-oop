public interface Switchable {
    void on();
    void off();
    boolean isOn();
    String getName();

    default void toggle() {
        if (isOn()) {
            off();
        } else {
            on();
        }
    }
}
