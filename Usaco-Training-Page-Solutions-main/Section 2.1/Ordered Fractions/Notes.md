## Ordered Fractions:

Details:
USACO Gateway Question 2.1.2.

[Problem Statement Link](https://train.usaco.org/usacoprob2?a=aYHn1LaUn7R&S=frac1)

The goal is to find all reduced fractions with denominators smaller or
equal to an integer N, and print the fractions in ascending order by
decimal.

Example: Maximum Denominator = 5 

### Part 1: Original Thoughts

***Step 1: Matrix***

Create a matrix of decimals and put all fractions inside. For instance,
for fractions with max denominators up to 5, the matrix would look like
this:

| |Numerator:1 |**Numerator:2**|**Numerator:3**|**Numerator:4**|**Numerator:5**
:-----:|:-----:|:-----:|:-----:|:-----:|:-----:
Denominator:1|1.0|-|-|-|-
Denominator:2|0.5|1.0|-|-|-
Denominator:3|0.333|0.667|1.0|-|-
Denominator:4|0.25|0.5|0.75|0.1|-
Denominator:5|0.2|0.4|0.6|0.8|1

 

***Step 2: Table***

Make a table holding with each cell holding fractions of each
denominator (using the matrix  to look up values), and sort once by
decimal.

(In this example, the table would look like this after initializing.:)

**Index:**|**Numerator:**|**Denominator:**|**Decimal:**
:-----:|:-----:|:-----:|:-----:
1|1|5|0.2
2|1|4|0.25
3|1|3|0.33
4|1|2|0.5
5|1|1|1

***Step 3: Removing Smallest Fractions***

Then, remove fraction with the smallest decimal one by one and print
(the fraction at the top of the table) and insert new fraction
(numerator+1 / denominator).

***Step 4: Moving Fractions / Traverse***

Move: After inserting new fraction F, check the table, comparing F’s
decimal and shifting F’s position down the table while the next fraction
has a smaller decimal than F.

Same Decimal: If fraction F meets a fraction F1 with the same decimal,
compare the denominators.

***Step 4.5: What To Do If Same Decimal***

If F has a larger fraction, delete F1, insert  new fraction
(F1.numerator +1 / F1.denominator), and redo the Move step for the new
fraction.

If F1 has a larger fraction, delete F, insert new fraction (F.numerator
+1 / F..denominator), and redo the Move step for the new fraction.

### Part 2: New Parts & Edits.

1.  Abandoned table, because it works in sequences

2.  Used queue instead of table.

            Made functions push, pop, remove original, and moveNext.

***Remove Original:***

Removes a fraction and PUSHES the next fraction with the same
denominator (numerator+1)/(denominator) into the queue.

***Push:***

Pushes a fraction ‘oFraction’ into a queue. Then moves oFraction into
proper position through queue until there is a fraction with a decimal
greater than oFraction’s decimal. If there is a fraction Fraction1 with
the same decimal as oFraction, it compares the denominator of the two
fractions, keeping the fraction with the smaller denominator, and
deletes the other fraction and replace the next if necessary--pushing
another fraction (numerator+1/ denominator\*) into the queue instead. 

***Pop:***

Pops the first (and smallest fraction) in the queue and pushes
(numerator+1/denominator).

***MoveNext:***

Checks whether to continue pushing (numerator+1/denominator) and
increases the numerator of the fraction by 1 for Pushing.

Part 3: Official USACO Answers:

Link:
([*https://train.usaco.org/usacoanal2?a=aYHn1LaUn7R&S=frac1*](https://train.usaco.org/usacoanal2?a=aYHn1LaUn7R&S=frac1))

Use Farey Sequence / Stem Brocot Tree to start from endpoints 0/1 and
1/1 and recursively generate midpoints by calculating  (numerator1
+numerator2) / (denominator1 + denominator2).
