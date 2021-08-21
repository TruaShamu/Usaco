### USACO Hamming Codes Notes
[Problem Statement Link](https://train.usaco.org/usacoprob2?a=UWYfHlK0FqA&S=hamming)

Since we only have '**B**' bits, we know that the largest codeword that could be part of the answer is **2^B-1**.

And because **B** is from **(1 < B <= 8)**, we can bruteforce to find the answer.

1. We need to find the difference in bits (the hamming distance) between all numbers in the range of **(0 to (2^B - 1) .)**

This can be done with bitwise, by looping through each index of the codewords in binary and checking whether the bits are the same or not. This matrix will be called '**diff**'.

For instance, this is what **diff** will look like when **B=2** :

|- | I=0|I=1|I=2|I=3|
|--------|--------|----------|---------|---------|
|J=0|  0 | 1 |1|2
|J=1|    1|   0 |2|1|
|J=2| 1 | 2 |0|1
|J=3| 2|  1 |1|0

So, if  codeword **I** is **1** and codeword **J** is **3**,  the hamming distance would be **1**.

Next, we have to recursively find the earliest codeword with a hamming distance (from all other recorded codewords) that is greater than **D**.

We will start with 0 as the first codeword, and make an array **'ans'** to store our current list of codewords found. We also have to record the last codeword added, **'prev'** (in decimal) and the number of valid codewords **'num'** we have found so far.

What we have to do is: **loop through all possible codewords  'next'  that are bigger than 'prev'.**
We will have to compare **'next'** to all the values in **'num'.** If for all values of 'num', **diff[next][num] >= D**
then add 'next' to 'num' and recurse until we have **N** values in 'num'.

Then print out the contents of **'num'**, and we are done.

