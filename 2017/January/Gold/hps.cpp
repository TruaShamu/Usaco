#include <bits/stdc++.h>
using namespace std;
using ll = long long;

using vi = vector<int>;
using vvi = vector<vi>;
#define pb push_back
#define all(x) begin(x), end(x)
#define sz(x) (int) (x).size()

using pi = pair<int, int>;
#define f first
#define s second
#define mp make_pair

void setIO(string name = "") {
	cin.tie(0)->sync_with_stdio(0);
	if (sz(name)) {
		freopen((name + ".in").c_str(), "r", stdin);
		freopen((name + ".out").c_str(), "w", stdout);
	}
}

const int MAXN = 1e5 + 5;
const int MAXK = 20 + 5;
int main() {
	setIO("hps");
	int N; int K;
	cin >> N >> K;
	vector<vector<vi>> dp(N + 1, vector<vi>(K + 1, vi(4)));
	int fjMove[MAXN];
	// Encode hoof paper scissors as numbers
	for (int i = 0; i < N; i++) {
		char inp; cin >> inp;
		if (inp == 'H') fjMove[i] = 0;
		if (inp == 'P') fjMove[i] = 1;
		if (inp == 'S') fjMove[i] = 2;
	}

	/* DP[i][j][k] is number of maximum wins, between 0 and ith game.
	   Given Bessie changed moves j times, and her current move is k.*/
	for (int i = 0; i <= N; i++) {
		for (int changes = 0; changes <= K; changes++) {
			for (int cur = 0; cur < 3; cur++) {
				// Default
				if (i == 0) {
					dp[i][changes][cur] = 0;
				}
				else {
					// If she won the current game.
					int curWin = fjMove[i - 1] == cur ? 1 : 0;
					// From previous game.
					if (changes == 0) {
						dp[i][changes][cur] = dp[i - 1][changes][cur];
					}
					// If she has switched
					else {
						// Other 2 possible moves for the previous game.
						int prev1 = (cur + 1) % 3;
						int prev2 = (cur + 2) % 3;
						// Update with best value.
						dp[i][changes][cur] = max(dp[i - 1][changes][cur],
							dp[i - 1][changes - 1][prev1]);
						dp[i][changes][cur] = max(dp[i][changes][cur],
							dp[i - 1][changes - 1][prev2]);

					}
					// Add results of the current game.
					dp[i][changes][cur] += curWin;
				}

			}
		}
	}
	// The max games won, from all 3 ending moves.
	int ans = max(dp[N][K][0], max(dp[N][K][1], dp[N][K][2]));
	cout << ans << "\n";

}
