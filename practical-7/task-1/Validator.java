import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Validator {
    public static List<String> validate(Object obj) {
        List<String> errors = new ArrayList<>();
        if (obj == null) return errors;

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            try {
                Object val = f.get(obj);
                String strVal = (val != null) ? val.toString().trim() : null;

                if (f.isAnnotationPresent(NotBlank.class)) {
                    NotBlank ann = f.getAnnotation(NotBlank.class);
                    if (strVal == null || strVal.isEmpty()) {
                        errors.add(f.getName() + ": " + ann.message());
                    }
                }

                if (f.isAnnotationPresent(MaxLength.class)) {
                    MaxLength ann = f.getAnnotation(MaxLength.class);
                    if (strVal != null && strVal.length() > ann.value()) {
                        errors.add(f.getName() + ": " + ann.message() + " (Max: " + ann.value() + ", Found: " + strVal.length() + ")");
                    }
                }
            } catch (IllegalAccessException e) {
                errors.add("Failed to inspect field: " + f.getName());
            }
        }
        return errors;
    }
}
