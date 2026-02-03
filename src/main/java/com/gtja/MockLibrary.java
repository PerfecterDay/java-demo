package com.gtja;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

interface TestInterface {
    int calculate(int a, int b);

    String calculate(String a, String b);

    String getValue();
}

public class MockLibrary implements InvocationHandler {
    Map<String, Object> registry = new HashMap<>();
    String workingKey;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String key = keyGenerator(method.getName(),args);

        if (!registry.containsKey(key)) {
            throw new IllegalArgumentException();
        }

        return registry.get(key);
    }

    public MockLibrary when(String method, Object[] args) {
        workingKey = keyGenerator(method, args);
        return this;
    }

    private String keyGenerator(String methodName, Object[] args) {
        if (args == null) {
            methodName += "_null";
        } else {
            for (Object arg : args) {
                methodName = methodName.concat("_" + arg.toString());
            }
        }
        return methodName;
    }


    public void thenReturn(Object val) {
        registry.put(workingKey, val);
    }

    public static void main(String[] args) {
        MockLibrary handler = new MockLibrary();
        TestInterface ref = (TestInterface) Proxy.newProxyInstance(
                MockLibrary.class.getClassLoader(),
                new Class[]{TestInterface.class}, handler);

        handler.when("calculate", new Object[]{1, 2}).thenReturn(3);
        handler.when("calculate", new Object[]{"a", "b"}).thenReturn("ab");
//        handler.when("calculate", new Object[]{null, null}).thenReturn("x");

//        System.out.println(ref.calculate(1, 2)); // prints 3
//        System.out.println(ref.calculate("a", "b")); // prints "ab"
        System.out.println(ref.getValue()); // prints "ab"
    }
}
