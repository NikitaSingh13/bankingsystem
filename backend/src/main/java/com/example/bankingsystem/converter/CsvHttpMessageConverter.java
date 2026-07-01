package com.example.bankingsystem.converter;

import com.example.bankingsystem.annotation.CsvColumn;
import com.example.bankingsystem.dto.response.CsvResponse;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Converts CSV response objects into CSV content.
 */
@Component
public class CsvHttpMessageConverter
        extends AbstractHttpMessageConverter<CsvResponse> {

    private static final String DATE_PATTERN = "dd-MM-yyyy";

    private static final MediaType TEXT_CSV =
            new MediaType("text", "csv", StandardCharsets.UTF_8);

    /**
     * Constructs the CSV HTTP message converter.
     */
    public CsvHttpMessageConverter() {
        super(TEXT_CSV);
    }

    /**
     * Formats a value for CSV output.
     *
     * @param value value to format
     * @return formatted value
     */
    private String formatValue(
            final Object value) {

        if (value == null) {
            return "";
        }

        if (value instanceof Date date) {
            return new SimpleDateFormat(DATE_PATTERN).format(date);
        }

        return value.toString();
    }

    /**
     * Returns CSV fields ordered by their column annotation.
     *
     * @param type row type
     * @return ordered CSV fields
     */
    private List<Field> getCsvFields(
            final Class<?> type) {

        return Arrays.stream(type.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(CsvColumn.class))
                .sorted(Comparator.comparingInt(field ->
                        field.getAnnotation(CsvColumn.class).order()))
                .peek(field -> field.setAccessible(true))
                .toList();
    }

    /**
     * Reads a field value from a row object.
     *
     * @param field field to read
     * @param row row object
     * @return field value
     */
    private Object readFieldValue(
            final Field field,
            final Object row) {

        try {
            return field.get(row);

        } catch (final IllegalAccessException exception) {
            throw new IllegalArgumentException(
                    "CSV field cannot be accessed.",
                    exception);
        }
    }

    /**
     * Reading CSV requests is not supported.
     *
     * @param clazz target class
     * @param inputMessage request body
     * @return never returns
     */
    @Override
    protected CsvResponse readInternal(
            final Class<? extends CsvResponse> clazz,
            final HttpInputMessage inputMessage) {

        throw new UnsupportedOperationException(
                "Reading CSV is not supported.");
    }

    /**
     * Returns whether the supplied class is supported.
     *
     * @param clazz the class to test
     * @return true if the class can be converted to CSV
     */
    @Override
    protected boolean supports(
            final Class<?> clazz) {

        return CsvResponse.class.isAssignableFrom(clazz);
    }

    /**
     * Writes the CSV header row.
     *
     * @param fields CSV fields
     * @param csvPrinter CSV printer
     * @throws IOException when writing fails
     */
    private void writeHeader(
            final List<Field> fields,
            final CSVPrinter csvPrinter)
            throws IOException {

        final List<String> headers = fields.stream()
                .map(field -> field.getAnnotation(CsvColumn.class).header())
                .toList();

        csvPrinter.printRecord(headers);
    }

    /**
     * Writes the supplied response object as CSV.
     *
     * @param response the response object
     * @param outputMessage the HTTP output message
     * @throws IOException when CSV generation fails
     */
    @Override
    protected void writeInternal(
            final CsvResponse response,
            final HttpOutputMessage outputMessage)
            throws IOException {

        final Collection<?> rows = response.getCsvRows();

        try (
                Writer writer =
                        new OutputStreamWriter(
                                outputMessage.getBody(),
                                StandardCharsets.UTF_8);

                CSVPrinter csvPrinter =
                        new CSVPrinter(
                                writer,
                                CSVFormat.DEFAULT)
        ) {

            final List<Field> fields =
                    getCsvFields(response.getCsvRowType());

            writeHeader(fields, csvPrinter);
            writeRows(rows, fields, csvPrinter);

            csvPrinter.flush();
        }
    }

    /**
     * Writes CSV data rows.
     *
     * @param rows CSV row objects
     * @param fields CSV fields
     * @param csvPrinter CSV printer
     * @throws IOException when writing fails
     */
    private void writeRows(
            final Collection<?> rows,
            final List<Field> fields,
            final CSVPrinter csvPrinter)
            throws IOException {

        for (final Object row : rows) {
            writeSingleRow(row, fields, csvPrinter);
        }
    }

    /**
     * Writes one CSV data row.
     *
     * @param row CSV row object
     * @param fields CSV fields
     * @param csvPrinter CSV printer
     * @throws IOException when writing fails
     */
    private void writeSingleRow(
            final Object row,
            final List<Field> fields,
            final CSVPrinter csvPrinter)
            throws IOException {

        try {
            final List<String> values = fields.stream()
                    .map(field -> readFieldValue(field, row))
                    .map(this::formatValue)
                    .toList();

            csvPrinter.printRecord(values);

        } catch (final IllegalArgumentException exception) {
            throw new IOException(
                    "Unable to generate CSV.",
                    exception);
        }
    }
}
