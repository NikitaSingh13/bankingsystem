package com.example.bankingsystem.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a field as exportable in CSV output.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CsvColumn {

    /**
     * Returns the CSV column header.
     *
     * @return column header
     */
    String header();

    /**
     * Returns the CSV column order.
     *
     * @return column order
     */
    int order();
}
