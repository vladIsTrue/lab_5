package org.example;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Marks fields in a class that should be automatically injected by an Injector.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoInjectable {
}
