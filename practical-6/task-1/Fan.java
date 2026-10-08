public class Fan implements Switchable {
    private boolean state = false;
    @Override public void on() { state = true; System.out.println("Fan switched ON (Oscillating)"); }
    @Override public void off() { state = false; System.out.println("Fan switched OFF"); }
    @Override public boolean isOn() { return state; }
    @Override public String getName() { return "Ceiling Fan"; }
}
