WIP
This problem can be solved with DSU.
To speed things up, we can sort queries and edges by relevance descending (we can keep the edges with larger K, and add in the edges with smaller K).

So what can be done  is:

1. Sort Queries Descending
2. Sort Edges Descending
3. Make arrays to store the connected component of each node, and number of nodes in each connected components.
4. For each query, process edges where edge relevance >= query relevance.
5. For processing each edge(a, b): Find the connected component of each node, and merge it with DSU.
6. Save answer in array and print
