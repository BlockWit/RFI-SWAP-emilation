package com.blockwit.rfitest.model;

public class FeeCalc {

    public long fee;
    public long toTransfer;
    public long stakeFee;
    public long burnFee;

    public FeeCalc(long amount) {
        this.fee = (long) (amount * Chain.commonK);
        this.toTransfer = amount - fee;
        this.stakeFee = (long) (fee * Chain.stakeK);
        this.burnFee = fee - stakeFee;
    }

}
