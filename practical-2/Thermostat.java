public class Thermostat{
    private String location;
    private int temperature;
    private static final int MIN = 16;
    private static final int MAX = 30;
    private static int activeCount = 0;

    public Thermostat(String location, int temperature){
        this.location = location;
        if (temperature >= MIN && temperature <= MAX) {
            this.temperature = temperature;
        } else {
            this.temperature = 22;
        }
        activeCount++;
    }

    public Thermostat(String location){
        this(location, 22);
    }

    public void raise(){
        if (temperature < MAX) {
            temperature++;
        }
        else{
            System.out.println("Temperature is already at 30.");
        }
    }

    public void lower(){
        if (temperature > MIN) {
            temperature--;
        }
        else{
            System.out.println("Temperature is already at 16.");
        }
    }

    public int getTemperature(){
        return temperature;
    }

    public static int getActiveCount(){
        return activeCount;
    }

    public static void main(String[] args){
        Thermostat t1 = new Thermostat("Living Room", 25);
        Thermostat t2 = new Thermostat("Bedroom");

        System.out.println("Initial Temperature: " + t1.getTemperature());

        System.out.println("Raising temperature...");
        for (int i = 1; i <= 10; i++) {
            t1.raise();
            System.out.println("Step " + i + ": " + t1.getTemperature());
        }

        System.out.println("Lowering temperature...");
        
        for (int i = 1; i <= 20; i++) {
            t1.lower();
            System.out.println("Step " + i + ": " + t1.getTemperature());
        }

        System.out.println("Active Thermostats: " + Thermostat.getActiveCount());

    }
}