# Obtorque
Obtorque is an implementation of a blockchain for learning purposes.

Lots of credits go to Daniel van Flymen https://hackernoon.com/learn-blockchains-by-building-one-117428612f46 which features an implementation in Python.

## Building
mvn clean install

## Running
Execute the main class file be.clowdy.obtorque.Obtorque

## Usage
Several HTTP interfaces are being exposed with jax-rs.

### /blockchain/transactions/new
Creates a new transaction, Requires sender, recipient and amount as parameters.

```
{
   "sender":"randomSenderId",
   "recipient":"randomRecipientId",
   "amount":42}
```

### /blockchain/mine
Mines a new block

### /blockchain/chain
Gets the complete blockchain to the frontend (not such a smart idea for a real blockchain)

### /blockchain/nodeinfo
Returns the identification of the current node

