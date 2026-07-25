class Solution {
    public int maxProduct(int n) {
        int max = 0;
        int max2 = 0;

        while (n > 0) {
            int temp = n % 10;

            if (temp >= max) {
                max2 = max;
                max = temp;
            } else if (temp > max2) {
                max2 = temp;
            }

            n /= 10;
        }

        return max * max2;
    }
}