package com.adidas.bdd.api.common;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScenarioContext {

    public static UUID correlationId = UUID.randomUUID();

    public static final ScenarioContext INSTANCE;

    static {
        INSTANCE = new ScenarioContext();
    }

    private ThreadLocal<Map<String, UUID>> threadLocalValue = new ThreadLocal<>();

    public void set(String key, UUID value) {
        if (threadLocalValue.get() == null) {
            threadLocalValue.set(new HashMap<String, UUID>());
        }
        threadLocalValue.get().put(key, value);
    }

    public UUID get(String key) {
        return threadLocalValue.get().get(key);
    }
}
