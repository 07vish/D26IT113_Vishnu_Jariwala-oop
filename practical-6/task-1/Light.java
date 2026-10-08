public class Light implements Switchable {
    private boolean state = false;
    @Override public void on() { state = true; System.out.println("Light turned ON (Illuminating)"); }
    @Override public void off() { state = false; System.out.println("Light turned OFF"); }
    @Override public boolean isOn() { return state; }
    @Override public String getName() { return "Smart LED Light"; }
}
