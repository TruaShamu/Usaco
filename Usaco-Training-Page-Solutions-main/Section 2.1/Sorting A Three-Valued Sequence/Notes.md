### USACO Sorting A Three-Valued Sequence Notes
[Problem Statement Link](https://train.usaco.org/usacoprob2?a=UWYfHlK0FqA&S=sort3)

First, read in the original array **'input',** and make a copy of the array and sort it. This copy will be called **'sorted'**.
We need to make a table to store the frequency of what value we have, and what value we need.

>**INPUT**: 
>9
>2 2 1 3 3 3 2 3 1

When sorted, the array will look like so:
>1 1 2 2 2 3 3 3

We can see for instance, that we have two 2's that we want to convert into 1's,  one 1 that we would like to convert into 2, and so on and so forth. In a table, it would look like this:
(Columns contain what we have, and rows contain what we need.)
| -|Need Type 1| Need Type 2 |Need Type 3| 
|--------------|-------------|-----------|------
| Have Type 1  |  0          |          1|1
| Have Type 2  |    2        |   0       |1
| Have Type 3  | 0           |    2      |0

#### Analyzing Swaps:
There are 2 types of swaps: 
Direct Swap: We have a 2 that we wish to convert into a 1, and a 1 that we want to convert into a 2.
This swaps 2 elements and costs 1 move.

Indirect Swap:  We have a 1 that we wish to convert to 2, a 2 that we want to convert into 3, and a 3 we want to convert to a 1. This swaps 3 elements to their correct positions and costs 2 moves.

Therefore, first we can use the table to first calculate the number of direct swaps.
Then, fixing each left-over slot takes 2/3 of a move, so we can calculate total number of moves needed.
