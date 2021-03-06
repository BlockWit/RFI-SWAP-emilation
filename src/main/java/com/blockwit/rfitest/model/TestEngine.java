package com.blockwit.rfitest.model;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
public class TestEngine {

    public void test() {
        Random rnd = new Random();

        long total = 1_000_000_000_000_000_000L;
        Chain ethChain = Chain.createChainWithFreeBalance("ETH", total);
        Chain bscChain = Chain.createChainWithHoldBalance("BSC", total);

        String chainStatesBefore = ethChain + "\n" + bscChain;


        int limit = 10000000;
        int txCount = 0;

        int minIdx = 1;
        int maxIdx = 100;

        List<TransactionInfo> transactionInfoList = new ArrayList<>();
        List<SwapTransactionInfo> swapTransactionInfoList = new ArrayList<>();

        while(txCount < limit) {
            long amount = longRnd(total / 1000000000000L, total / 1000000000L, rnd);
            int fromIdx = intRnd(minIdx, maxIdx, rnd);
            int toIdx = intRnd(minIdx, maxIdx, rnd);
            boolean isSwap = intRnd(1, 10, rnd) == 1;
            if (isSwap) {
                //boolean swapDir = rnd.nextBoolean();
                boolean swapDir = intRnd(1, 1000, rnd) != 1;
                SwapTransactionInfo transactionInfo = trySwap(swapDir ? ethChain : bscChain,
                        swapDir ? bscChain : ethChain,
                        fromIdx,
                        toIdx,
                        amount);
                if (transactionInfo != null) {
                    txCount++;
                    swapTransactionInfoList.add(transactionInfo);
                }
            } else {
                boolean chainFirst = intRnd(1, 1000, rnd) == 1;
                TransactionInfo transactionInfo = tryTransfer(chainFirst ? ethChain : bscChain,
                        fromIdx,
                        toIdx,
                        amount);
                if (transactionInfo != null) {
                    txCount++;
                    transactionInfoList.add(transactionInfo);
                }
            }
        }

        String chainStatesAfter = ethChain + "\n" + bscChain;

        log.info("Finished: ");
        log.info("transactions summary: " + transactionInfoList.size());
        log.info("transactions " + ethChain.getName() + " : " + transactionInfoList.stream()
                .filter(t -> t.chain.getName().equals(ethChain.getName())).collect(Collectors.toList()).size());
        log.info("transactions " + bscChain.getName() + " : " + transactionInfoList.stream()
                .filter(t -> t.chain.getName().equals(bscChain.getName())).collect(Collectors.toList()).size());
        log.info("swaps: " + swapTransactionInfoList.size());

        int bsc2eth = 0;
        int eth2bsc = 0;
        for (SwapTransactionInfo swapTransactionInfo : swapTransactionInfoList) {
            if (swapTransactionInfo.from.chain.getName().equals(ethChain.getName()))
                eth2bsc++;
            else
                bsc2eth++;
        }

        log.info("swaps : " + ethChain.getName() + " -> " + bscChain.getName() + " : " + eth2bsc);
        log.info("swaps : " + bscChain.getName() + " -> " + ethChain.getName() + " : " + bsc2eth);

        log.info("Chain states:");
        log.info("Before: \n" + chainStatesBefore);
        log.info("After: \n" + chainStatesAfter);
    }

    public static TransactionInfo tryTransfer(Chain chain, int fromIdx, int toIdx, long amount) {
        return checkFromBalance(chain, fromIdx, amount) ?
                chain.transfer(fromIdx, toIdx, amount) :
                null;
    }

    public static SwapTransactionInfo trySwap(Chain chain1, Chain chain2, int fromIdx, int toIdx, long amount) {
        return checkFromBalance(chain1, fromIdx, amount) ?
                chain1.swapTo(fromIdx, toIdx, amount, chain2) :
                null;
    }

    public static boolean checkFromBalance(Chain chain, int addrIdx, long amount) {
        ChainAddress chainAddress = chain.getAddresses().get(addrIdx);
        if (chainAddress == null)
            return false;
        return chainAddress.getBalance() >= amount;
    }

    public static int intRnd(int min, int max, Random rnd) {
        return rnd.nextInt(max - min) + min;
    }

    public static long longRnd(long min, long max, Random rnd) {
        return (long) ((Math.random() * (max - min)) + min);
    }

}

