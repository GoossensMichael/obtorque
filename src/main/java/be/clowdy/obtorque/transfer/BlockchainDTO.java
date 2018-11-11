package be.clowdy.obtorque.transfer;

import be.clowdy.obtorque.domain.Block;
import com.google.common.base.Preconditions;

import java.util.Collection;
import java.util.Collections;

public final class BlockchainDTO {
    private Collection<Block> chain;
    private int length;

    private BlockchainDTO() {

    }

    public static Builder create() {
        return new Builder();
    }

    public Collection<Block> getChain() {
        return chain;
    }

    public int getLength() {
        return length;
    }

    public static class Builder {

        private final BlockchainDTO blockchain;

        public Builder() {
            blockchain = new BlockchainDTO();
        }

        public Builder chain(final Collection<Block> chain) {
            blockchain.chain = Collections.unmodifiableCollection(chain);
            blockchain.length = blockchain.chain.size();
            return this;
        }

        public BlockchainDTO build() {
            Preconditions.checkNotNull(blockchain.chain);
            return blockchain;
        }
    }
}
