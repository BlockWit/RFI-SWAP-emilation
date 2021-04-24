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
	
	
## BSC txs more than ETH test - skew test -success

[main] INFO com.blockwit.rfitest.model.TestEngine - Finished: 

[main] INFO com.blockwit.rfitest.model.TestEngine - transactions summary: 15728984

[main] INFO com.blockwit.rfitest.model.TestEngine - transactions ETH : 1072

[main] INFO com.blockwit.rfitest.model.TestEngine - transactions BSC : 15727912

[main] INFO com.blockwit.rfitest.model.TestEngine - swaps: 128704

[main] INFO com.blockwit.rfitest.model.TestEngine - swaps : ETH -> BSC : 126687

[main] INFO com.blockwit.rfitest.model.TestEngine - swaps : BSC -> ETH : 2017

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
	
	total balance: 999998859981001703
	
	free balance: 999943612299844995
	
	hold balance: 55247681156708

Chain BSC:

	addresses: 100
	
	total balance: 999944189003416659
	
	free balance: 14991257516
	
	hold balance: 999944174012159143
	
## BSC txs more than ETH test - skew test with big fee - 10%

[main] INFO com.blockwit.rfitest.model.TestEngine - Finished: 

[main] INFO com.blockwit.rfitest.model.TestEngine - transactions summary: 9714445

[main] INFO com.blockwit.rfitest.model.TestEngine - transactions ETH : 2175

[main] INFO com.blockwit.rfitest.model.TestEngine - transactions BSC : 9712270

[main] INFO com.blockwit.rfitest.model.TestEngine - swaps: 285555

[main] INFO com.blockwit.rfitest.model.TestEngine - swaps : ETH -> BSC : 284371

[main] INFO com.blockwit.rfitest.model.TestEngine - swaps : BSC -> ETH : 1184

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
	
	total balance: 999975451142570078
	
	free balance: 999867298713571556
	
	hold balance: 108152428998522
	
Chain BSC:

	addresses: 100
	
	total balance: 999878305514174068
	
	free balance: 3882944003
	
	hold balance: 999878301631230065
	
## Same as previous but with 2% fee summary

[main] INFO com.blockwit.rfitest.model.TestEngine - Finished: 

[main] INFO com.blockwit.rfitest.model.TestEngine - transactions summary: 9918895

[main] INFO com.blockwit.rfitest.model.TestEngine - transactions ETH : 668

[main] INFO com.blockwit.rfitest.model.TestEngine - transactions BSC : 9918227

[main] INFO com.blockwit.rfitest.model.TestEngine - swaps: 81105

[main] INFO com.blockwit.rfitest.model.TestEngine - swaps : ETH -> BSC : 79844

[main] INFO com.blockwit.rfitest.model.TestEngine - swaps : BSC -> ETH : 1261

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
	
	total balance: 999999280013104274
	
	free balance: 999964387563988555
	
	hold balance: 34892449115719
	

Chain BSC:

	addresses: 100
	
	total balance: 999964758370528576
	
	free balance: 16248834329
	
	hold balance: 999964742121694247
	
