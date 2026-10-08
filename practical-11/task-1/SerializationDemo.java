import java.io.*;

public class SerializationDemo {
    public static class Student implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        private final String studentId;
        private final String name;
        private final transient String temporaryPassword; // Transient field not persisted

        public Student(String studentId, String name, String temporaryPassword) {
            this.studentId = studentId;
            this.name = name;
            this.temporaryPassword = temporaryPassword;
        }

        @Override
        public String toString() {
            return "Student[ID=" + studentId + ", Name=" + name + ", Password=" + temporaryPassword + "]";
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Object Serialization & Transient Fields ===");
        Student[] batch = {
            new Student("D26IT113", "Vishnu Jariwala", "SecretPass123"),
            new Student("25IT001", "Aarav Patel", "AlphaBravo99")
        };

        File file = new File("students.ser");

        System.out.println("Original Objects Before Serialization:");
        for (Student s : batch) System.out.println("  " + s);

        // Serialize
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(batch);
            System.out.println("\nSuccessfully serialized array to " + file.getName());
        } catch (IOException e) {
            System.out.println("Serialization error: " + e.getMessage());
        }

        // Deserialize
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Student[] restored = (Student[]) ois.readObject();
            System.out.println("\nDeserialized Objects from Disk:");
            for (Student s : restored) {
                System.out.println("  " + s + " -> Notice: transient password is null as expected!");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Deserialization error: " + e.getMessage());
        } finally {
            if (file.exists()) file.delete();
        }
    }
}
