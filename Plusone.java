class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++; 
                return digits;
            }
            digits[i] = 0; 
        }

        int[] result = new int[n + 1];
        result[0] = 1;
        return result;
    }
}
public class Plusone {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] good = {1, 2, 3};
        int[] dosa = sol.plusOne(good);
        String ab = "ananya";
        String[] a = {"ananya","bhujade","om"};
        char[] alphabet = new char[26];
        for (int digit : dosa) {
            System.out.print(digit + " ");
        }
    }
}