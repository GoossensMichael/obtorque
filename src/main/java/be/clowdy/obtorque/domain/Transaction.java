package be.clowdy.obtorque.domain;

import com.google.common.base.Preconditions;

public final class Transaction {
    private String sender;
    private String recipient;
    private double amount;

    private Transaction() {

    }

    public static Builder create() {
        return new Builder();
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public double getAmount() {
        return amount;
    }

    public static class Builder {

        private final Transaction transaction;

        public Builder() {
            transaction = new Transaction();
        }

        public Builder sender(final String sender) {
            transaction.sender = sender;
            return this;
        }

        public Builder recipient(final String recipient) {
            transaction.recipient = recipient;
            return this;
        }

        public Builder amount(final double amount) {
            transaction.amount = amount;
            return this;
        }

        public Transaction build() {
            Preconditions.checkNotNull(transaction.sender);
            Preconditions.checkNotNull(transaction.recipient);
            return transaction;
        }
    }
}
