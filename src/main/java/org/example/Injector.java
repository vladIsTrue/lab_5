package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * A utility class for dependency injection.
 */
public class Injector {

    /**
     * Injects dependencies into the specified object by initializing fields marked
     * with the {@link AutoInjectable} annotation.
     *
     * @param object The object to inject dependencies into.
     * @param <T>    The type of the object.
     * @return The object with injected dependencies.
     */
    public <T> T inject(T object) {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                Class<?> fieldType = field.getType();
                Object fieldValue = createInstanceForType(fieldType);
                setField(object, field, fieldValue);
            }
        }

        return object;
    }

    /**
     * Creates an instance for the specified type by reading the class name
     * from a properties file.
     *
     * @param type The type for which to create an instance.
     * @return An instance of the specified type.
     * @throws RuntimeException If an instance cannot be created.
     */
    private Object createInstanceForType(Class<?> type) {
        Properties properties = loadPropertiesFile("src/main/resources/config.properties");
        String className = properties.getProperty(type.getName());

        try {
            Class<?> implClass = Class.forName(className);
            return implClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance for type " + type, e);
        }
    }

    /**
     * Loads properties from a file.
     *
     * @param filePath The path to the properties file.
     * @return The loaded properties.
     * @throws RuntimeException If the properties file cannot be loaded.
     */
    private Properties loadPropertiesFile(String filePath) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
        return properties;
    }

    /**
     * Sets the value of a field in the specified object.
     *
     * @param object The object containing the field.
     * @param field  The field to set.
     * @param value  The value to set.
     * @throws RuntimeException If the field value cannot be set.
     */
    private void setField(Object object, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(object, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to set field value", e);
        }
    }
}
