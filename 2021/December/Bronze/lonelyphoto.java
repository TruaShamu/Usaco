import java.io.*;

public class lonelyPhotos {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] s = br.readLine().toCharArray();

		long ans = 0;
		for (int i = 0; i < N; i++) {
			long lhs = 0;
			if (i > 0 && s[i - 1] != s[i]) {
				lhs++;
				for (int K = i - 2; K >= 0 && s[K] == s[i - 1]; K--) {
					lhs++;
				}
			}

			long rhs = 0;
			if (i + 1 < N && s[i + 1] != s[i]) {
				rhs++;
				for (int K = i + 2; K < N && s[K] == s[i + 1]; K++) {
					rhs++;
				}
			}

			ans += lhs * rhs + Math.max(lhs - 1, 0) + Math.max(rhs - 1, 0);
		}
		System.out.println(ans);
	}
}
