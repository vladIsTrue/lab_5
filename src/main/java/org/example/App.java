package org.example;

/**
 * An entry point for the application to demonstrate dependency injection.
 */
public class App {

    /**
     * The main method that initializes an instance of {@link SomeBean} using an {@link Injector}
     * and invokes the {@code foo} method on it.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SomeBean sb = new Injector().inject(new SomeBean());
        sb.foo();
    }
}
