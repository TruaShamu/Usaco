import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        friendcross solver = new friendcross();
        solver.solve();
    }

    static class friendcross {
        public int[] arr;
        public int[] brr;
        public static int[] seq;

        public void solve() throws IOException {
            BufferedReader br = new BufferedReader(new FileReader("friendcross.in"));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("friendcross.out")));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            //Read input
            arr = new int[n];
            brr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }
            for (int i = 0; i < n; i++) {
                brr[i] = Integer.parseInt(br.readLine());
            }
            //Change value to x, y
            int[] newX = new int[n + 1];
            int[] newY = new int[n + 1];
            for (int i = 0; i < n; i++) {
                newX[arr[i]] = i + 1;
                newY[brr[i]] = i + 1;
            }

            seq = new int[n + 1]; //The corresponding y to the current x.
            for (int i = 1; i <= n; i++) {
                seq[newX[i]] = newY[i];
            }
            friendcross.SegmentTree root = new friendcross.SegmentTree(1, n);
            ArrayList<Event>[] events = new ArrayList[n + 1];
            //Events upperbound, lowerbound
            for (int i = 0; i <= n; i++) events[i] = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                int up = Math.min(n, i + k);
                int down = Math.max(0, i - k - 1);
                events[up].add(new friendcross.Event(i, +1));
                events[down].add(new friendcross.Event(i, -1));
            }
            long totalCross = 0;
            BIT x = new BIT(n);
            for (int i = 1; i <= n; i++) {
                x.update(seq[i], +1);
                totalCross += i - x.query(seq[i]);
            }
            long unfriendly = 0;
            for (int cow = 1; cow <= n; cow++) {
                root.update(newX[cow], +1);
                for (friendcross.Event e : events[cow]) {
                    unfriendly += e.sign * root.query(1, newX[e.cow], newY[e.cow]);
                }
            }
            pw.println(totalCross - unfriendly);
            pw.close();
        }

        static class Event {
            public int cow;
            public int sign;

            public Event(int cow, int sign) {
                this.cow = cow;
                this.sign = sign;
            }

        }

        static class SegmentTree {
            public int[] arr;
            public int[] pl;
            public int[] pr;
            public BIT bit;
            public int start;
            public int end;
            public friendcross.SegmentTree lChild;
            public friendcross.SegmentTree rChild;

            public SegmentTree(int start, int end) {
                this.start = start;
                this.end = end;
                arr = new int[end - start + 2];
                if (start == end) {
                    arr[1] = seq[start];
                } else {
                    int mid = (start + end) >> 1;
                    lChild = new friendcross.SegmentTree(start, mid);
                    rChild = new friendcross.SegmentTree(mid + 1, end);
                    pl = new int[lChild.arr.length];
                    pr = new int[rChild.arr.length];
                    int lidx = 1, ridx = 1;

                    int idx = 1;
                    int[] larr = lChild.arr, rarr = rChild.arr;
                    while (lidx < larr.length && ridx < rarr.length) {
                        if (larr[lidx] < rarr[ridx]) {
                            pl[lidx] = idx;
                            arr[idx++] = larr[lidx++];
                        } else {
                            pr[ridx] = idx;
                            arr[idx++] = rarr[ridx++];
                        }
                    }
                    while (lidx < larr.length) {
                        pl[lidx] = idx;
                        arr[idx++] = larr[lidx++];
                    }
                    while (ridx < rarr.length) {
                        pr[ridx] = idx;
                        arr[idx++] = rarr[ridx++];
                    }
                }
                bit = new BIT(end - start + 2);
            }

            public int query(int s, int e, int k) {
                if (start == s && end == e) {
                    if (k < arr[1]) return bit.count;
                    int lo = 1, hi = arr.length - 1;
                    while (lo < hi) {
                        int mid = (lo + hi + 1) / 2;
                        if (arr[mid] > k) hi = mid - 1;
                        else lo = mid;
                    }
                    return bit.count - bit.query(lo);
                }
                int mid = (start + end) >> 1;
                if (mid >= e) return lChild.query(s, e, k);
                else if (mid < s) return rChild.query(s, e, k);
                else return lChild.query(s, mid, k) + rChild.query(mid + 1, e, k);
            }

            public int update(int p, int val) {
                if (start == p && end == p) {
                    bit.update(1, +1);
                    return 1;
                }
                int mid = (start + end) >> 1;
                int apos = -1;
                if (mid >= p) apos = pl[lChild.update(p, val)];
                else apos = pr[rChild.update(p, val)];
                bit.update(apos, +1);
                return apos;
            }

        }

    }

    static class BIT {
        private int[] tree;
        private int N;
        public int count;

        public BIT(int N) {
            this.N = N;
            this.tree = new int[N + 1];
            this.count = 0;
        }

        public int query(int K) {
            int sum = 0;
            for (int i = K; i > 0; i -= (i & -i))
                sum += tree[i];
            return sum;
        }

        public void update(int K, int val) {
            this.count += val;
            for (int i = K; i <= N; i += (i & -i)) {
                tree[i] += val;
            }
        }

    }


}
