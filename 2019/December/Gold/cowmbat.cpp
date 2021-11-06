#define _CRT_SECURE_NO_DEPRECATE
#include <bits/stdc++.h> // see /general/running-code-locally
using namespace std;
using ll = long long;
using vi = vector<int>;
#define pb push_back
#define all(x) begin(x), end(x)
#define sz(x) (int) (x).size()
using pi = pair<int, int>;
#define f first
#define s second
#define mp make_pair

void setIO(string name = "") {
	cin.tie(0)->sync_with_stdio(0); // see /general/fast-io
	if (sz(name)) {
		freopen((name + ".in").c_str(), "r", stdin); // see /general/input-output
		freopen((name + ".out").c_str(), "w", stdout);
	}
}


const int MAXN = 1e5 + 5;
const int MAXL = 26;
int cost[MAXL][MAXL];
int dp[MAXN][MAXL];
int freq[MAXN][MAXL];
int res[MAXN];

int count(int start, int end, int let) {
	return freq[end + 1][let] - freq[start][let];
}

int main() {
	setIO("cowmbat");
	int N; int M; int K;
	cin >> N >> M >> K;

	string s;
	cin >> s;

	for (int i = 0; i < M; i++) {
		for (int j = 0; j < M; j++) {
			cin >> cost[i][j];
		}
	}

  // Calculate min cost from i to j.
	for (int k = 0; k < M; k++) {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				cost[i][j] = min(cost[i][j], cost[i][k] + cost[k][j]);
			}
		}
	}

   // freq[i][j] is frequency of the letter j from range (0...i)
	for (int i = 1; i <= N; i++) {
		for (int j = 0; j < M; j++) {
			freq[i][j] = freq[i - 1][j];
		}
		freq[i][s[i - 1] - 'a']++;
	}

	memset(dp, 0x3f, sizeof dp);
	memset(res, 0x3f, sizeof res);
	res[0] = 0;

	// If (N < 2K) then there is 1 unique letter streak.

	for (int idx = K - 1; (idx < 2 * K - 1) && (idx < N); idx++) {
		for (int let = 0; let < M; let++) {
			dp[idx][let] = 0;
			for (int i = 0; i < M; i++) {
				//Calculate cost of converting each letter to "let".
				dp[idx][let] += (cost[i][let] * count(0, idx, i));
			}
		}

		//Find a letter "j" with minimum cost to change all the letters to.
		res[idx] = dp[idx][0];
		for (int j = 1; j < M; j++) {
			res[idx] = min(res[idx], dp[idx][j]);
		}
	}


	//If (N>=2K) there is >=1 unique letters.
	for (int idx = (2 * K - 1); idx < N; idx++) {
		for (int j = 0; j < M; j++) {
			dp[idx][j] = dp[idx - 1][j];

			//Change cost
			if (s[idx] - 'a' != j) {
				dp[idx][j] += cost[s[idx] - 'a'][j];
			}
		}

		for (int let1 = 0; let1 < M; let1++) {
			int add = 0;
			for (int let2 = 0; let2 < M; let2++) {
				//Find cost of a length k streak that ends at idx.
				add += cost[let2][let1] * count(idx - K + 1, idx, let2);
			}
			//Continue with same letter, or make a new streak
			dp[idx][let1] = min(dp[idx][let1], res[idx - K] + add);
		}
		//Update
		res[idx] = dp[idx][0];
		for (int j = 1; j < M; j++) {
			res[idx] = min(res[idx], dp[idx][j]);
		}

	}

	cout << res[N - 1];
}
