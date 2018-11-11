package be.clowdy.obtorque.service.mapper;

import be.clowdy.obtorque.domain.Transaction;
import be.clowdy.obtorque.transfer.TransactionDTO;

public final class TransactionMapper {

    private TransactionMapper() {

    }

    public static TransactionDTO map(final Transaction transaction) {
        final TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setSender(transaction.getSender());
        transactionDTO.setRecipient(transaction.getRecipient());
        transactionDTO.setAmount(transaction.getAmount());
        return transactionDTO;
    }
}
