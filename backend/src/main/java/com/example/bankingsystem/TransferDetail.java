package com.example.bankingsystem;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents transfer information exposed outside
 * the service layer.
 */
public class TransferDetail {

    private BigDecimal amount;

    private Date date;

    private String fromToAccountNumber;

    private Long transactionId;

    private TransferType type;

    /**
     * Constructs transfer detail using the given information.
     *
     * @param transferTransactionId transaction identifier
     * @param transferAmount transfer amount
     * @param transferDate transfer date
     * @param transferFromToAccountNumber source account number
     * @param transferType transfer type
     */
    public TransferDetail(
            final Long transferTransactionId,
            final BigDecimal transferAmount,
            final Date transferDate,
            final String transferFromToAccountNumber,
            final TransferType transferType) {

        amount = transferAmount;
        date = transferDate;
        fromToAccountNumber = transferFromToAccountNumber;
        transactionId = transferTransactionId;
        type = transferType;
    }

    /**
     * Constructs transfer detail with default values.
     */
    public TransferDetail() {

    }

    /**
     * Returns the transfer amount.
     *
     * @return transfer amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Returns the transfer date.
     *
     * @return transfer date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the account number.
     *
     * @return account number
     */
    public String getFromToAccountNumber(){
        return fromToAccountNumber;
    }

    /**
     * Returns the transaction identifier.
     *
     * @return transaction identifier
     */
    public Long getTransactionId() {
        return transactionId;
    }

    /**
     * Returns the transfer type.
     *
     * @return transfer type
     */
    public TransferType getType() {
        return type;
    }

    /**
     * Sets the transfer amount.
     *
     * @param transferAmount transfer amount
     */
    public void setAmount(
            final BigDecimal transferAmount) {

        amount = transferAmount;
    }

    /**
     * Sets the transfer date.
     *
     * @param transferDate transfer date
     */
    public void setDate(
            final Date transferDate) {

        date = transferDate;
    }

    /**
     * Sets the source account number.
     *
     * @param transferFromToAccountNumber source account number
     */
    public void setSourceAccountNumber(
            final String transferFromToAccountNumber) {
        fromToAccountNumber = transferFromToAccountNumber;
    }

    /**
     * Sets the transaction identifier.
     *
     * @param transferTransactionId transaction identifier
     */
    public void setTransactionId(
            final Long transferTransactionId) {

        transactionId = transferTransactionId;
    }

    /**
     * Sets the transfer type.
     *
     * @param transferType transfer type
     */
    public void setType(
            final TransferType transferType) {

        type = transferType;
    }
}