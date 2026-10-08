import java.lang.reflect.Field;

public class Driver {
    public static void main(String[] args) throws Exception {
        System.out.println("=== Reflection CSV/Table Column Mapping ===");
        String[] headers = { "user_id", "full_name", "email_address" };
        String[] dataRow = { "USR_9901", "Kavya Patel", "kavya@charusat.edu.in" };

        UserRecord record = new UserRecord();
        Field[] fields = record.getClass().getDeclaredFields();

        for (Field f : fields) {
            if (f.isAnnotationPresent(Column.class)) {
                Column col = f.getAnnotation(Column.class);
                String targetColName = col.name();

                for (int i = 0; i < headers.length; i++) {
                    if (headers[i].equalsIgnoreCase(targetColName)) {
                        f.setAccessible(true);
                        f.set(record, dataRow[i]);
                        break;
                    }
                }
            }
        }

        System.out.println("Hydrated Object via Reflection:");
        System.out.println("  " + record);
    }
}
