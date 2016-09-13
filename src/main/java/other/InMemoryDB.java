package other;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class InMemoryDB {

    private static InMemoryDB instance;
    private InMemoryDB() {}
    public static InMemoryDB getInstance() {
        if (instance == null) {
            instance = new InMemoryDB();
        }

        return instance;
    }

    /**
     * The name-value nameValueMap
     */
    private Map<String, String> nameValueMap = new HashMap<String, String>();

    /**
     * The value-count nameValueMap
     */
    private Map<String, Integer> valueCountMap = new HashMap<String, Integer>();

    /**
     * Number of current transactions
     */
    private int transactions = 0;

    /**
     * Stack of previous name-value nameValueMap. Each nameValueMap in the stack is for one transaction.
     */
    private Stack<Map<String, String>> prevValuesStack = new Stack<Map<String, String>>();

    /**
     * Stack of previous value-count nameValueMap. Each nameValueMap in the stack is for one transaction.
     */
    private Stack<Map<String, Integer>> prevCountsStack = new Stack<Map<String, Integer>>();

    public void set(String name, String value) {
        String prevValue = nameValueMap.get(name);
        if (prevValue != null) {
            if (!prevValue.equals(value)) {
                nameValueMap.put(name, value);
                minusCount(prevValue);
                addCount(value);
            }
        } else {
            nameValueMap.put(name, value);
            addCount(value);
        }

        if (transactions > 0) {
            Map<String, String> prevValues = prevValuesStack.peek();
            if (prevValues.get(name) == null) {
                prevValues.put(name, prevValue);
            }
        }
    }

    public String get(String name) {
        return nameValueMap.get(name);
    }

    public void delete(String name) {
        String prevValue = nameValueMap.get(name);
        if (prevValue != null) {
            nameValueMap.remove(name);
            minusCount(prevValue);
        }

        if (transactions > 0) {
            Map<String, String> prevValues = prevValuesStack.peek();
            if (prevValues.get(name) == null) {
                prevValues.put(name, prevValue);
            }
        }
    }

    public int count(String value) {
        Integer result = valueCountMap.get(value);
        if (result != null) {
            return result;
        }
        return 0;
    }

    private void addCount(String value) {
        Integer count = valueCountMap.get(value);
        if (count != null) {
            valueCountMap.put(value, count + 1);
        } else {
            valueCountMap.put(value, 1);
        }

        if (transactions > 0) {
            Map<String, Integer> prevCounts = prevCountsStack.peek();
            if (prevCounts.get(value) == null) {
                prevCounts.put(value, count);
            }
        }
    }

    private void minusCount(String value) {
        Integer count = valueCountMap.get(value);
        if (count != null && count > 0) {
            valueCountMap.put(value, count - 1);
        } else {
            valueCountMap.put(value, 0);
        }

        if (transactions > 0) {
            Map<String, Integer> prevCounts = prevCountsStack.peek();
            if (prevCounts.get(value) == null) {
                prevCounts.put(value, count);
            }
        }
    }

    public void begin() {
        transactions ++;
        prevValuesStack.push(new HashMap<String, String>());
        prevCountsStack.push(new HashMap<String, Integer>());
    }

    public String rollback() {
        if (transactions > 0) {
            transactions --;
        } else {
            return "NO TRANSACTION";
        }

        if (!prevValuesStack.isEmpty()) {
            Map<String, String> prevValues = prevValuesStack.pop();
            for (String key : prevValues.keySet()) {
                nameValueMap.put(key, prevValues.get(key));
            }
        }
        if (!prevCountsStack.isEmpty()) {
            Map<String, Integer> prevCounts = prevCountsStack.pop();
            for (String key : prevCounts.keySet()) {
                valueCountMap.put(key, prevCounts.get(key));
            }
        }
        return "";
    }


    public void commit() {
        transactions = 0;
        prevValuesStack.clear();
        prevCountsStack.clear();
    }

    public void init() {
        nameValueMap.clear();
        valueCountMap.clear();
        transactions = 0;
        prevValuesStack.clear();
        prevCountsStack.clear();
    }

    public void end() {
        init();
    }
}
