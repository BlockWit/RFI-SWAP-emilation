package com.blockwit.rfitest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChainAddress {

    private int id;

    private long balance = 0;

    private Chain chain;

    public void incBalance(long count) {
        this.balance += count;
    }

    public void decBalance(long count) {
        this.balance -= count;
    }

}
