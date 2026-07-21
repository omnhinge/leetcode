class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int ones = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '1') {
                ones++;
            }
        }

        String t = "1" + s + "1";

        int maxGain = 0;
        int leftZeros = 0;

        int i = 0;

        while (i < t.length()) {

            // Count zero block
            if (t.charAt(i) == '0') {

                int zeros = 0;

                while (i < t.length() && t.charAt(i) == '0') {
                    zeros++;
                    i++;
                }

                leftZeros = zeros;
            }

            // Count 1 block
            else {
                while (i < t.length() && t.charAt(i) == '1') {
                    i++;
                }

                // If a zero block exists before this 1-block
                // and another zero block exists after it
                if (leftZeros > 0 && i < t.length() && t.charAt(i) == '0') {

                    int j = i;
                    int rightZeros = 0;

                    while (j < t.length() && t.charAt(j) == '0') {
                        rightZeros++;
                        j++;
                    }

                    maxGain = Math.max(maxGain, leftZeros + rightZeros);
                }
            }
        }

        return ones + maxGain;
    }
}