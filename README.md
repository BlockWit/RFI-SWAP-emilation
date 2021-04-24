# RFI-SWAP-emilation
Java model to emulate swap between two chains for tokens with RRI

The main goal of test to check insufficient for swap HOLD balance state. It's when HOLD balance on other side chain does not contains enough tokens to transfer from current chain free balance.

1. 1:4 swap transactions per transfer transactions (If you want to test hold priority transfer you should increase swap transactions. Swap transactions should be greater than transfer.)
1. Test fails if you see "ERROR - Hold balance .. in chain .... less"

## First emulation - success:

[main] INFO com.blockwit.rfitest.model.TestEngine - Finished: 

[main] INFO com.blockwit.rfitest.model.TestEngine - transactions: 289230

[main] INFO com.blockwit.rfitest.model.TestEngine - swaps: 36123

[main] INFO com.blockwit.rfitest.model.TestEngine - swaps : ETH -> BSC : 18921

[main] INFO com.blockwit.rfitest.model.TestEngine - swaps : BSC -> ETH : 17202

[main] INFO com.blockwit.rfitest.model.TestEngine - Chain states:

[main] INFO com.blockwit.rfitest.model.TestEngine - Before: 

Chain ETH:

	addresses: 2
	
	total balance: 1000000000000000000
	
	free balance: 1000000000000000000
	
	hold balance: 0


Chain BSC:

	addresses: 2
	
	total balance: 1000000000000000000
	
	free balance: 0
	
	hold balance: 1000000000000000000

[main] INFO com.blockwit.rfitest.model.TestEngine - After: 

Chain ETH:

	addresses: 100
	
	total balance: 998880888226048583
	
	free balance: 998084651290115352

	hold balance: 796236935933231

Chain BSC:

	addresses: 100
	
	total balance: 999037555813117460
	
	free balance: 34429716248510
	
	hold balance: 999003126096868950
	
	
## BSC txs more than ETH test

[main] INFO com.blockwit.rfitest.model.TestEngine - Finished: 

[main] INFO com.blockwit.rfitest.model.TestEngine - transactions: 1745773

[main] INFO com.blockwit.rfitest.model.TestEngine - swaps: 226764

[main] INFO com.blockwit.rfitest.model.TestEngine - swaps : ETH -> BSC : 215475

[main] INFO com.blockwit.rfitest.model.TestEngine - swaps : BSC -> ETH : 11289

[main] INFO com.blockwit.rfitest.model.TestEngine - Chain states:

[main] INFO com.blockwit.rfitest.model.TestEngine - Before: 

Chain ETH:

	addresses: 2
	
	total balance: 1000000000000000000
	
	free balance: 1000000000000000000
	
	hold balance: 0
	

Chain BSC:

	addresses: 2
	
	total balance: 1000000000000000000
	
	free balance: 0
	
	hold balance: 1000000000000000000

[main] INFO com.blockwit.rfitest.model.TestEngine - After: 

Chain ETH:

	addresses: 100
	
	total balance: 999917949378743914
	
	free balance: 999532172474690864
	
	hold balance: 385776904053050
	

Chain BSC:

	addresses: 100
	
	total balance: 999989075288598127
	
	free balance: 384886421847614
	
	hold balance: 999604188866750513
