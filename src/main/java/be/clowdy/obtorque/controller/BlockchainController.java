package be.clowdy.obtorque.controller;

import be.clowdy.obtorque.service.BlockchainService;
import be.clowdy.obtorque.transfer.BlockchainDTO;
import be.clowdy.obtorque.transfer.MessageDTO;
import be.clowdy.obtorque.transfer.MiningResultDTO;
import be.clowdy.obtorque.transfer.TransactionDTO;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Controller
@Path("/blockchain")
public class BlockchainController {

    private final BlockchainService blockchainService;

    @Autowired
    public BlockchainController(final BlockchainService blockchainService) {
        this.blockchainService = blockchainService;
    }

    @GET
    @Path("/mine")
    @Produces(MediaType.APPLICATION_JSON)
    public MiningResultDTO mine() {
        return blockchainService.mine();
    }

    @POST
    @Path("/transactions/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MessageDTO newTransaction(final TransactionDTO transaction) {
        Preconditions.checkNotNull(transaction);
        Preconditions.checkNotNull(transaction.getSender());
        Preconditions.checkNotNull(transaction.getRecipient());
        final int index = blockchainService.newTransaction(transaction);
        return MessageDTO.create()
                .message("Transaction will be added to Block " + index)
                .build();
    }

    @GET
    @Path("/chain")
    @Produces(MediaType.APPLICATION_JSON)
    public BlockchainDTO getBlockchain() {
        return blockchainService.getBlockchainDTO();
    }

    @GET
    @Path("/nodeinfo")
    public String getNodeIdentifier() {
        return blockchainService.getNodeIdentifier();
    }
}
