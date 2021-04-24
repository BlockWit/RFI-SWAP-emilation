package com.blockwit.rfitest.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class TransactionInfo {

    Chain chain;

    ChainAddress chainAddressFrom;

    ChainAddress chainAddressTo;

    long amountFrom;

    long amountTo;

    long fee;

    public String toString() {
        return "Transfer in " + chain.getName() + " from " + chainAddressFrom.getId() + " send " + amountFrom + " to " + chainAddressTo.getId() +
                " receive " + amountTo + " with fee " + fee;
    }

}
