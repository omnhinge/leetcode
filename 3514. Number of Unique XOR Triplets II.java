class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int max = 0;

        for (int x : nums) {
            max = Math.max(max, x);
        }

        int size = 1;
        while (size <= max) {
            size <<= 1;
        }

        boolean[] present = new boolean[size];

        for (int x : nums) {
            present[x] = true;
        }

        boolean[] two = new boolean[size];

        // All possible XOR values using 2 elements
        for (int a = 0; a < size; a++) {
            if (!present[a]) continue;

            for (int b = 0; b < size; b++) {
                if (present[b]) {
                    two[a ^ b] = true;
                }
            }
        }

        boolean[] three = new boolean[size];

        // All possible XOR values using 3 elements
        for (int x = 0; x < size; x++) {
            if (!two[x]) continue;

            for (int a = 0; a < size; a++) {
                if (present[a]) {
                    three[x ^ a] = true;
                }
            }
        }

        int ans = 0;

        for (boolean possible : three) {
            if (possible) ans++;
        }

        return ans;
    }
}