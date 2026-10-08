@FunctionalInterface
public interface PowerRule {
    boolean allow(Switchable device, int hour);
}
