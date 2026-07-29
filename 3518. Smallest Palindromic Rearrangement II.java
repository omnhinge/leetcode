class Solution {
    private static final long LIMIT = 1_000_001L;
    private long[] fact;

    public String smallestPalindrome(String s, int k) {
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        String mid = "";
        int[] half = new int[26];
        int halfLen = 0;

        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) == 1) {
                mid = String.valueOf((char) ('a' + i));
            }
            half[i] = cnt[i] / 2;
            halfLen += half[i];
        }

        buildFacts(halfLen);

        long total = countPermutations(half, halfLen);
        if (k > total) {
            return "";
        }

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {
            for (int ch = 0; ch < 26; ch++) {
                if (half[ch] == 0) continue;

                half[ch]--;

                long ways = countPermutations(half, halfLen - pos - 1);

                if (ways < k) {
                    k -= ways;
                    half[ch]++;
                } else {
                    left.append((char) ('a' + ch));
                    break;
                }
            }
        }

        StringBuilder right = new StringBuilder(left).reverse();
        return left.toString() + mid + right.toString();
    }

    private void buildFacts(int n) {
        fact = new long[n + 1];
        fact[0] = 1;

        for (int i = 1; i <= n; i++) {
            fact[i] = Math.min(LIMIT, fact[i - 1] * i);
        }
    }

    private long countPermutations(int[] freq, int total) {
        long res = combinations(total, freq);
        return Math.min(res, LIMIT);
    }

    private long combinations(int total, int[] freq) {
        long ans = 1;
        int remaining = total;

        for (int f : freq) {
            if (f == 0) continue;

            ans *= nCrLimited(remaining, f);
            ans = Math.min(ans, LIMIT);

            remaining -= f;
        }

        return ans;
    }

    private long nCrLimited(int n, int r) {
        if (r > n) return 0;

        r = Math.min(r, n - r);

        long res = 1;

        for (int i = 1; i <= r; i++) {
            res = res * (n - r + i) / i;

            if (res > LIMIT) {
                return LIMIT;
            }
        }

        return res;
    }
}