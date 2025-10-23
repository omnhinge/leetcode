class Solution {
    public boolean hasSameDigits(String s) {
        int n = s.length();
        byte[] digits = new byte[n];

        for (int i = 0; i < n; i++)
            digits[i] = (byte)(s.charAt(i) - '0');

        while (n > 2) {
            for (int i = 0; i < n - 1; i++) {
                int sum = digits[i] + digits[i + 1];
                digits[i] = (byte)(sum >= 10 ? sum - 10 : sum);
            }
            n--;
        }

        return digits[0] == digits[1];
    }
}
