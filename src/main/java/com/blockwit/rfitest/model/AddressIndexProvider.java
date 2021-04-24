package com.blockwit.rfitest.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AddressIndexProvider {

    private long curIndex = 0;

    public long next() {
        return curIndex++;
    }

}
