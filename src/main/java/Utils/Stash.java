package Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stash {
    private static Stash ourInstance = new Stash();
    private Map<String, Object> stash;
    private List<String> discountNames;

    public static Stash getInstance() {
        return ourInstance;
    }

    private Stash() {
        this.stash = new HashMap<>();
        this.discountNames = new ArrayList<>();
    }

    public void put(String key, Object value) {
        stash.put(key, value);
    }

    public Object get(String key) {
        return stash.get(key);
    }

    public List<String> getDiscountNames() {
        return discountNames;
    }

    public void putDiscountName(String name) {
        discountNames.add(name);
    }
}
