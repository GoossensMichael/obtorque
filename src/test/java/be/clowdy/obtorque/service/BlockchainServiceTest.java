package be.clowdy.obtorque.service;

import be.clowdy.obtorque.domain.Block;
import be.clowdy.obtorque.domain.Blockchain;
import be.clowdy.obtorque.transfer.MiningResultDTO;
import be.clowdy.obtorque.transfer.TransactionDTO;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class BlockchainServiceTest {

    @Test
    public void testProof() {
        final Blockchain blockchain = new Blockchain();
        final BlockchainService blockchainService = new BlockchainService(blockchain);
        final TransactionDTO transactionDTO = new TransactionDTO();
        {
            transactionDTO.setSender("sender");
            transactionDTO.setRecipient("recipient");
            transactionDTO.setAmount(0.);
        }
        blockchainService.newTransaction(transactionDTO);

        final Block previousBlock = blockchain.getLastBlock();
        final MiningResultDTO miningResult = blockchainService.mine();
        final String hashSource = miningResult.getPreviousHash() + miningResult.getNonce();
        final String hash = Blockchain.hash(hashSource);
        MatcherAssert.assertThat("Hash ends with four zeroes", hash.substring(hash.length() - 4), Matchers.equalTo("0000"));
        MatcherAssert.assertThat("Previous hash is equal to hashing the previous block", miningResult.getPreviousHash(), Matchers.equalTo(Blockchain.hash(previousBlock)));
    }
}
