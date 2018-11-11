package be.clowdy.obtorque.domain;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Collection;

public final class Block {
    private long index;
    private long timestamp;
    private Collection<Transaction> transactions;
    private long nonce;
    private String previousHash;

    private Block() {

    }

    public static Builder create() {
        return new Builder();
    }

    public long getIndex() {
        return index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Collection<Transaction> getTransactions() {
        return transactions;
    }

    public long getNonce() {
        return nonce;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public static class Builder {

        private final Block block;

        public Builder() {
            this.block = new Block();
        }

        public Builder index(final long index) {
            block.index = index;
            return this;
        }

        public Builder timestamp(final long timestamp) {
            block.timestamp = timestamp;
            return this;
        }

        public Builder transactions(final Collection<Transaction> transactions) {
            block.transactions = transactions;
            return this;
        }

        public Builder nonce(final long nonce) {
            block.nonce = nonce;
            return this;
        }

        public Builder previousHash(final String previousHash) {
            block.previousHash = previousHash;
            return this;
        }

        public Block build() {
            if (block.transactions == null) {
                block.transactions = new ArrayList<>();
            }
            Preconditions.checkNotNull(block.previousHash);
            return block;
        }
    }
}
