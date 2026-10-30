package util;

import model.annotation.Audit;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditLogger {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void logInvocation(Object target, String methodName, Object... params) {
        try {
            for (Method m : target.getClass().getDeclaredMethods()) {
                if (m.getName().equals(methodName) && m.isAnnotationPresent(Audit.class)) {
                    Audit audit = m.getAnnotation(Audit.class);
                    String action = audit.action().isEmpty() ? methodName : audit.action();
                    System.out.printf("[AUDIT-LOG %s] Action: %-15s | Invoked on: %s%n",
                        SDF.format(new Date()), action, target.getClass().getSimpleName());
                    break;
                }
            }
        } catch (Exception ignored) {
        }
    }
}
