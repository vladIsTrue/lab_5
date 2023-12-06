package org.example;

/**
 * A sample class with fields marked for automatic injection using the {@link AutoInjectable} annotation.
 */
public class SomeBean {

    /**
     * Field marked for automatic injection with an implementation of {@link SomeInterface}.
     */
    @AutoInjectable
    SomeInterface field1;

    /**
     * Field marked for automatic injection with an implementation of {@link SomeOtherInterface}.
     */
    @AutoInjectable
    SomeOtherInterface field2;

    /**
     * Performs some actions using the injected dependencies.
     */
    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }
}
