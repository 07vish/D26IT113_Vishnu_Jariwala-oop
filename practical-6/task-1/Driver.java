public class Driver {
    public static void main(String[] args) {
        System.out.println("=== Interface Remote Control & Lambdas ===");
        Switchable[] appliances = { new Fan(), new Light() };

        System.out.println("--- Toggling Appliances ---");
        for (Switchable s : appliances) {
            System.out.println("Testing " + s.getName() + " initial state: " + (s.isOn() ? "ON" : "OFF"));
            s.toggle();
            System.out.println("State after toggle: " + (s.isOn() ? "ON" : "OFF"));
            s.toggle();
            System.out.println("State after second toggle: " + (s.isOn() ? "ON" : "OFF"));
            System.out.println();
        }

        System.out.println("--- Evaluating Power Rules at Hour 23 (11 PM) ---");
        // 1. Anonymous Inner Class
        PowerRule nightRuleAnon = new PowerRule() {
            @Override
            public boolean allow(Switchable device, int hour) {
                // Disallow fans after 10 PM
                return hour < 22 || !(device instanceof Fan);
            }
        };

        // 2. Lambda Expression
        PowerRule nightRuleLambda = (device, hour) -> (hour >= 6 && hour <= 23);

        int currentHour = 23;
        for (Switchable dev : appliances) {
            System.out.println(dev.getName() + " allowed by Anonymous Rule? " + nightRuleAnon.allow(dev, currentHour));
            System.out.println(dev.getName() + " allowed by Lambda Rule?    " + nightRuleLambda.allow(dev, currentHour));
        }
    }
}
