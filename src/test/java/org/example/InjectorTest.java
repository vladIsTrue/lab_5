package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit tests for the {@link Injector} class.
 */
public class InjectorTest extends TestCase {

    /**
     * Constructs a new test case with the given name.
     *
     * @param testName The name of the test case.
     */
    public InjectorTest(String testName) {
        super(testName);
    }

    /**
     * Creates a test suite containing all test cases in this class.
     *
     * @return The test suite containing all test cases.
     */
    public static Test suite() {
        return new TestSuite(InjectorTest.class);
    }

    /**
     * Tests the {@link Injector#inject(Object)} method to ensure that it correctly injects
     * dependencies into fields annotated with {@link AutoInjectable}.
     */
    public void testInject_ShouldInjectDependencies_WhenFieldsAnnotatedWithAutoInjectable() {
        // Arrange
        Injector injector = new Injector();
        SomeBean someBean = new SomeBean();

        // Act
        SomeBean result = injector.inject(someBean);

        // Assert
        assertNotNull(result);
        assertNotNull(result.field1);
        assertNotNull(result.field2);
        assertTrue(result.field1 instanceof SomeInterface);
        assertTrue(result.field2 instanceof SomeOtherInterface);
    }

    /**
     * Tests the {@link Injector#inject(Object)} method to ensure that it injects the correct
     * implementations when the properties file is configured.
     */
    public void testInject_ShouldInjectCorrectImplementations_WhenPropertiesFileIsConfigured() {
        // Arrange
        Injector injector = new Injector();
        SomeBean someBean = new SomeBean();

        // Act
        SomeBean result = injector.inject(someBean);

        // Assert
        assertNotNull(result);
        assertEquals("org.example.SomeImpl", result.field1.getClass().getName());
        assertEquals("org.example.SODoer", result.field2.getClass().getName());
    }
}
