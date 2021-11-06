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

const int MAXN = 10005;
int A[MAXN];
int dp[MAXN];
int main() {
	setIO("teamwork");
	int N; int K;
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> A[i];
	}
	
	for (int r = 1; r < N; r++) {
		int maxVal = A[r];
		for (int l = r; l >= 0 && (r + 1 - l) <= K; l--) {
			maxVal = max(maxVal, A[l]);
			if (l == 0) {
				dp[r] = max(dp[r], maxVal * (r + 1 - l));
			}
			else dp[r] = max(dp[r], dp[l - 1] + maxVal * (r + 1 - l));
		}
	}
	cout << dp[N - 1] << "\n";


}
