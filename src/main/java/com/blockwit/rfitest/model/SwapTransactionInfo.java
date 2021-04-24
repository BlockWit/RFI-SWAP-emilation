package com.blockwit.rfitest.model;

public class SwapTransactionInfo {

    TransactionInfo from;

    TransactionInfo to;

    public SwapTransactionInfo(TransactionInfo from, TransactionInfo to) {
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "Swap: " + from.toString() + " <=> " + to.toString();
    }

}
