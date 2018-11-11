package be.clowdy.obtorque.transfer;

import com.google.common.base.Preconditions;

import java.util.Collection;
import java.util.Collections;

public class MiningResultDTO {
    private String message;
    private long index;
    private Collection<TransactionDTO> transactions;
    private long nonce;
    private String previousHash;

    public static Builder create() {
        return new Builder();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(final long index) {
        this.index = index;
    }

    public Collection<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(final Collection<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(final long nonce) {
        this.nonce = nonce;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(final String previousHash) {
        this.previousHash = previousHash;
    }

    public static class Builder {
        private final MiningResultDTO miningResultDTO;

        private Builder() {
            this.miningResultDTO = new MiningResultDTO();
        }

        public Builder message(final String message) {
            miningResultDTO.message = message;
            return this;
        }

        public Builder index(final long index) {
            miningResultDTO.index = index;
            return this;
        }

        public Builder transactions(final Collection<TransactionDTO> transactions) {
            miningResultDTO.transactions = Collections.unmodifiableCollection(transactions);
            return this;
        }

        public Builder nonce(final long nonce) {
            miningResultDTO.nonce = nonce;
            return this;
        }

        public Builder previousHash(final String previousHash) {
            miningResultDTO.previousHash = previousHash;
            return this;
        }

        public MiningResultDTO build() {
            Preconditions.checkNotNull(miningResultDTO.previousHash);
            return miningResultDTO;
        }
    }
}
