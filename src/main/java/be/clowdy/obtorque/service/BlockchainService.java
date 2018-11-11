package be.clowdy.obtorque.service;

import be.clowdy.obtorque.domain.Block;
import be.clowdy.obtorque.domain.Blockchain;
import be.clowdy.obtorque.service.mapper.TransactionMapper;
import be.clowdy.obtorque.transfer.BlockchainDTO;
import be.clowdy.obtorque.transfer.MiningResultDTO;
import be.clowdy.obtorque.transfer.TransactionDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public final class BlockchainService {

    private final String nodeIdentifier;
    private final Blockchain blockchain;

    public BlockchainService() {
        this(new Blockchain());
    }

    public BlockchainService(final Blockchain blockchain) {
        this.nodeIdentifier = UUID.randomUUID().toString().replace("-", "");
        this.blockchain = blockchain;
    }

    public BlockchainDTO getBlockchainDTO() {
        return BlockchainDTO.create()
                .chain(this.blockchain.getChain())
                .build();
    }

    public int newTransaction(final TransactionDTO transaction) {
        return blockchain.newTransaction(transaction.getSender(), transaction.getRecipient(), transaction.getAmount());
    }

    public MiningResultDTO mine() {
        // Reward the miner. This node receives 1 coin out of nowhere!
        blockchain.newTransaction("0", nodeIdentifier, 1);

        final Block lastBlock = blockchain.getLastBlock();
        final String previousHash = Blockchain.hash(lastBlock);
        final long nonce = blockchain.proofOfWork(previousHash);
        final Block newBlock = blockchain.newBlock(previousHash, nonce);
        final Collection<TransactionDTO> transactions =
                newBlock.getTransactions().stream().map(TransactionMapper::map).collect(Collectors.toList());
        return MiningResultDTO.create()
                .message("New block forged")
                .index(newBlock.getIndex())
                .transactions(transactions)
                .nonce(newBlock.getNonce())
                .previousHash(newBlock.getPreviousHash())
                .build();
    }

    public String getNodeIdentifier() {
        return nodeIdentifier;
    }
}
