import java.util.ArrayList;
import java.util.List;

public class ItemService {
    private final List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item");
        }
        for (Item existing : items) {
            if (existing.getId().equalsIgnoreCase(item.getId())) {
                throw new IllegalStateException("Duplicate item ID: " + item.getId());
            }
        }
        items.add(item);
    }

    public Item findById(String id) {
        for (Item item : items) {
            if (item.getId().equalsIgnoreCase(id)) return item;
        }
        return null;
    }

    public boolean removeItem(String id) {
        return items.removeIf(item -> item.getId().equalsIgnoreCase(id));
    }

    public int getItemCount() {
        return items.size();
    }

    public List<Item> getAllItems() {
        return new ArrayList<>(items);
    }
}
