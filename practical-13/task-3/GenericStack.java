import java.util.EmptyStackException;

public class GenericStack<T> {
    private Object[] elements;
    private int top;
    private static final int DEFAULT_CAPACITY = 10;

    public GenericStack() {
        this(DEFAULT_CAPACITY);
    }

    public GenericStack(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive: " + initialCapacity);
        }
        this.elements = new Object[initialCapacity];
        this.top = 0;
    }

    public void push(T item) {
        ensureCapacity();
        elements[top++] = item;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T item = (T) elements[--top];
        elements[top] = null; // Prevent memory leak
        return item;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (T) elements[top - 1];
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public int size() {
        return top;
    }

    private void ensureCapacity() {
        if (top == elements.length) {
            Object[] newElements = new Object[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Practical 13 - Task 3: Generic Array-Backed Stack");
        System.out.println("==================================================");

        // Instance 1: GenericStack with String elements (Undo History)
        System.out.println("1. Testing GenericStack<String> (Document Undo Action Stack):");
        GenericStack<String> undoStack = new GenericStack<>(3);
        undoStack.push("Insert Title");
        undoStack.push("Bold Text");
        undoStack.push("Insert Table");
        undoStack.push("Change Color"); // Triggers capacity expansion

        System.out.println("   Stack size: " + undoStack.size());
        System.out.println("   Top action (peek): " + undoStack.peek());
        System.out.println("   Popping all actions in LIFO order:");
        while (!undoStack.isEmpty()) {
            System.out.println("     Undoing -> " + undoStack.pop());
        }

        // Handle empty case gracefully
        try {
            System.out.print("   Attempting pop on empty stack: ");
            undoStack.pop();
        } catch (EmptyStackException e) {
            System.out.println("Handled EmptyStackException safely!");
        }

        // Instance 2: GenericStack with Integer elements (RPN Calculator / Call Stack)
        System.out.println("\n2. Reusing GenericStack<Integer> (Transaction IDs / Call Stack):");
        GenericStack<Integer> idStack = new GenericStack<>(5);
        idStack.push(1001);
        idStack.push(1002);
        idStack.push(1003);

        System.out.println("   Stack size: " + idStack.size());
        System.out.println("   Popping IDs:");
        while (!idStack.isEmpty()) {
            System.out.println("     Resolved ID -> " + idStack.pop());
        }
    }
}
