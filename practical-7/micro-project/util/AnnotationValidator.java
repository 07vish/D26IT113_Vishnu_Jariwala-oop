package util;

import model.annotation.Positive;
import model.annotation.MaxLength;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AnnotationValidator {
    public static String[] validate(Object obj) {
        if (obj == null) return new String[0];
        List<String> errors = new ArrayList<>();
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field f : fields) {
            f.setAccessible(true);
            try {
                Object value = f.get(obj);

                if (f.isAnnotationPresent(Positive.class)) {
                    Positive pos = f.getAnnotation(Positive.class);
                    if (value instanceof Number num) {
                        if (num.longValue() <= 0) {
                            errors.add("Field '" + f.getName() + "' (value: " + num + "): " + pos.message());
                        }
                    }
                }

                if (f.isAnnotationPresent(MaxLength.class)) {
                    MaxLength ml = f.getAnnotation(MaxLength.class);
                    if (value != null && value.toString().length() > ml.value()) {
                        errors.add("Field '" + f.getName() + "' (length: " + value.toString().length() + "): " + ml.message());
                    }
                }
            } catch (IllegalAccessException e) {
                errors.add("Error inspecting field: " + f.getName());
            }
        }
        return errors.toArray(new String[0]);
    }
}
