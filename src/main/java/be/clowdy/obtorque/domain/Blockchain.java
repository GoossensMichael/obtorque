package be.clowdy.obtorque.domain;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Blockchain {

    private static final Gson gson = new Gson();

    private Collection<Transaction> currentTransactions;
    private final List<Block> chain;

    public Blockchain() {
        // Init the block chain and the first list of transactions to process.
        chain = new ArrayList<>();
        initCurrentTransactions();
        // Create the genesis block.
        newBlock("1", 1L);
    }

    private void initCurrentTransactions() {
        currentTransactions = new ArrayList<>();
    }

    public int newTransaction(final String sender, final String recipient, final double amount) {
        final Transaction transaction = Transaction.create()
                .sender(sender)
                .recipient(recipient)
                .amount(amount)
                .build();
        currentTransactions.add(transaction);
        return chain.size();
    }

    public Block newBlock(final String previousHash, final long nonce) {
        final Block block = Block.create()
                .index(chain.size() + 1)
                .timestamp(System.currentTimeMillis())
                .transactions(currentTransactions)
                .nonce(nonce)
                .previousHash(previousHash)
                .build();
        initCurrentTransactions();
        chain.add(block);
        return block;
    }

    public long proofOfWork(final String previousHash) {
        long nonce = 0L;
        while (!validProof(previousHash, nonce)) {
            ++nonce;
        }
        return nonce;
    }

    private static boolean validProof(final String previousHash, final long nonce) {
        final String guess = previousHash + nonce;
        final String guessHash = hash(guess);
        return guessHash.endsWith("0000");
    }

    public static String hash(final Block block) {
        return hash(gson.toJson(block));
    }

    public static String hash(final String source) {
        return Hashing.sha256().hashString(source, StandardCharsets.UTF_8).toString();
    }

    public Collection<Block> getChain() {
        return chain;
    }

    public Block getLastBlock() {
        return chain.get(chain.size() - 1);
    }
}
