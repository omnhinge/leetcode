

import java.util.Arrays;
class Sum_of_GCD_of_Formed_Pairs {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {3,6,2,8};
        System.out.println(sol.gcdSum(arr));
    }
    
    
}
class Solution {
    public long gcdSum(int[] nums) {
        long ans = 0;
        int max = Integer.MIN_VALUE ;
        for(int i =0;i<nums.length;i++){
            max = Math.max(max,nums[i]);
            nums[i] = gcd(max,nums[i]);

        }
        Arrays.sort(nums);
        int i =0,j=nums.length-1;
        while(i<j){
            ans = ans + gcd(nums[i],nums[j]);
            i++;
            j--;
        }
        return ans;
    }
    public int gcd(int a, int b) {
    while (b != 0) {
        int temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}
}