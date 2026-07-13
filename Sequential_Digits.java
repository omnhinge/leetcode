import java.util.ArrayList;
import java.util.List;
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        int[] outputs = {12,23,34,45,56,67,78,89,123,234,345,456,567,678,789,1234,2345,3456,4567, 5678,6789,12345,23456,34567,45678,56789,123456,234567,345678,456789,1234567,2345678,3456789,12345678,23456789,123456789};
        List<Integer> ans = new ArrayList<>();
        int n = 0;

// Skip numbers smaller than low
        while (n < outputs.length && outputs[n] < low) {
              n++;
            }

// Add numbers until high
        while (n < outputs.length && outputs[n] <= high) {
        ans.add(outputs[n]);
            n++;
        }
        return ans;
    }
}
class Sequential_Digits{
    public static void main(String args[]){
        Solution sol = new Solution();
        System.out.println(sol.sequentialDigits(100,300));
    }
}