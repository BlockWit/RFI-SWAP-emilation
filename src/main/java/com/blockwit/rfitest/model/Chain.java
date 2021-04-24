package com.blockwit.rfitest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Slf4j
public class Chain {

    // 9 223 372 036 854 775 807
    public final static int decimals = 9;

    public final static double burnK = 0.01;

    public final static double stakeK = 0.01;

    public final static double commonK = burnK + stakeK;

    public final static double guaranteeK = 0;

    public final static int HOLD_ADDR_INDEX = 0;

    public final static int OWNER_ADDR_INDEX = 1;

    private String name;

    private Map<Integer, ChainAddress> addresses = new HashMap<>();

    private long totalSupply = 0;

    private Chain(String name, long count) {
        this.name = name;
        totalSupply += count;
    }

    public static Chain createChainWithFreeBalance(String name, long count) {
        Chain chain = new Chain(name, count);
        chain.addresses.put(0, new ChainAddress(HOLD_ADDR_INDEX, 0, chain));
        chain.addresses.put(1, new ChainAddress(OWNER_ADDR_INDEX, count, chain));
        return chain;
    }

    public static Chain createChainWithHoldBalance(String name, long count) {
        Chain chain = new Chain(name, count);
        chain.addresses.put(0, new ChainAddress(HOLD_ADDR_INDEX, count, chain));
        chain.addresses.put(1, new ChainAddress(OWNER_ADDR_INDEX, 0, chain));
        return chain;
    }

    public TransactionInfo transfer(int fromIdx, int toIdx, long amount) {
        ChainAddress from = addresses.get(fromIdx);
        if (from == null) {
            log.error("Address " + fromIdx + " in " + name + " chain not found!");
            return null;
        }
        long guaranteeBalance = balanceGuarantee(amount);
        if (from.getBalance() < balanceGuarantee(amount)) {
            log.error("Address " + fromIdx + " balance " + from.getBalance() + " in chain " + name +
                    " less than guarantee balance " + guaranteeBalance + " !");
            return null;
        }
        ChainAddress to = addresses.get(toIdx);
        if (to == null) {
//            log.warn("Address " + toIdx + " in chain " + name +
//                    " not found! Has been created!");
            to = new ChainAddress(toIdx, 0, this);
            addresses.put(toIdx, to);
        }

        FeeCalc feeCalc = new FeeCalc(amount);

        from.decBalance(amount);
        to.incBalance(feeCalc.toTransfer);
        totalSupply -= feeCalc.burnFee;

        if (feeCalc.stakeFee == 0) {
            TransactionInfo info = new TransactionInfo(this, from, to, amount, feeCalc.toTransfer, feeCalc.fee);
            return info;
        }

        long rewardBucket = feeCalc.stakeFee;
        ChainAddress curAddress = null;
        Iterator<ChainAddress> addressesIter = addresses.values().iterator();
        while (addressesIter.hasNext()) {
            curAddress = addressesIter.next();
            long balance = curAddress.getBalance();
            if (balance == 0)
                continue;
            double rewardK = ((double) balance) / ((double) totalSupply);
            if (rewardK == 0)
                continue;
            long addressReward = (long) (rewardBucket * rewardK);
            rewardBucket -= addressReward;
            curAddress.incBalance(addressReward);
        }
        curAddress.incBalance(rewardBucket);

        TransactionInfo info = new TransactionInfo(this, from, to, amount, feeCalc.toTransfer, feeCalc.fee);
        return info;
    }

    public SwapTransactionInfo swapTo(int fromIdx, int toIdx, long amount, Chain chain) {
        long guaranteeBalance = balanceGuarantee(amount);
        ChainAddress localHoldAddress = chain.addresses.get(HOLD_ADDR_INDEX);
        if (localHoldAddress.getBalance() < guaranteeBalance) {
            log.error("Hold balance " + localHoldAddress.getBalance() + " in chain " + chain.name +
                    " less than guarantee balance " + guaranteeBalance + " !");
            assert (localHoldAddress.getBalance() >= guaranteeBalance);
            return null;
        }
        TransactionInfo transactionInfo1 = transfer(fromIdx, HOLD_ADDR_INDEX, amount);
        TransactionInfo transactionInfo2 = chain.transfer(HOLD_ADDR_INDEX, toIdx, amount);

        return new SwapTransactionInfo(transactionInfo1, transactionInfo2);
    }

    private static long balanceGuarantee(long amount) {
        return (long) (amount + amount * guaranteeK);
    }

    public String toString() {
        return "Chain " + name + ":\n" +
                "\taddresses: " + addresses.size() + "\n" +
                "\ttotal balance: " + totalSupply + "\n" +
                "\tfree balance: " + (totalSupply - addresses.get(HOLD_ADDR_INDEX).getBalance()) + "\n" +
                "\thold balance: " + addresses.get(HOLD_ADDR_INDEX).getBalance() + "\n";
    }

}
