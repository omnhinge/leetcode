import java.util.*;

class Solution {

    private int[] segTree;
    private int[] gain;

    private void build(int node, int left, int right) {
        if (left == right) {
            segTree[node] = gain[left];
            return;
        }

        int mid = (left + right) >> 1;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        segTree[node] = Math.max(segTree[node * 2], segTree[node * 2 + 1]);
    }

    private int queryTree(int node, int left, int right, int ql, int qr) {
        if (ql > right || qr < left) return 0;

        if (ql <= left && right <= qr) {
            return segTree[node];
        }

        int mid = (left + right) >> 1;

        return Math.max(
            queryTree(node * 2, left, mid, ql, qr),
            queryTree(node * 2 + 1, mid + 1, right, ql, qr)
        );
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {

        int n = s.length();

        // Prefix count of ones
        int[] prefixOnes = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixOnes[i + 1] = prefixOnes[i] + (s.charAt(i) == '1' ? 1 : 0);
        }

        // Run Length Encoding
        List<Integer> start = new ArrayList<>();
        List<Integer> end = new ArrayList<>();
        List<Integer> len = new ArrayList<>();
        List<Character> type = new ArrayList<>();

        int i = 0;

        while (i < n) {
            int j = i;

            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }

            start.add(i);
            end.add(j - 1);
            len.add(j - i);
            type.add(s.charAt(i));

            i = j;
        }

        int m = len.size();

        gain = new int[m];

        for (i = 1; i < m - 1; i++) {
            if (type.get(i) == '1'
                    && type.get(i - 1) == '0'
                    && type.get(i + 1) == '0') {

                gain[i] = len.get(i - 1) + len.get(i + 1);
            }
        }

        segTree = new int[Math.max(1, 4 * m)];

        if (m > 0) {
            build(1, 0, m - 1);
        }

        // segment index for each position
        int[] segId = new int[n];

        for (i = 0; i < m; i++) {
            for (int p = start.get(i); p <= end.get(i); p++) {
                segId[p] = i;
            }
        }

        List<Integer> answer = new ArrayList<>();

        for (int[] q : queries) {

            int l = q[0];
            int r = q[1];

            int ones = prefixOnes[r + 1] - prefixOnes[l];

            int L = segId[l];
            int R = segId[r];

            while (L < m && start.get(L) < l) {
                L++;
            }

            while (R >= 0 && end.get(R) > r) {
                R--;
            }

            int bestGain = 0;

            if (L <= R && m > 0) {
                bestGain = queryTree(1, 0, m - 1, L, R);
            }

            answer.add(ones + bestGain);
        }

        return answer;
    }
}