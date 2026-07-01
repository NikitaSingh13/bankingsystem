package com.example.bankingsystem.dto.response;

import java.util.Collection;

/**
 * Represents a response that can expose rows for CSV export.
 */
public interface CsvResponse {

    /**
     * Returns the type used for CSV row metadata.
     *
     * @return CSV row type
     */
    Class<?> getCsvRowType();

    /**
     * Returns the rows that should be written to CSV.
     *
     * @return CSV row objects
     */
    Collection<?> getCsvRows();
}
