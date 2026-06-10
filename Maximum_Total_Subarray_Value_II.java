// Maximum Total Subarray Value II

class Solution {
    public long maxTotalValue(int[] nums, int k) {
        long result = 0;
        long min = Long.MAX_VALUE;
        long max=Long.MIN_VALUE;
        if (nums.length <3) k =1;
        for(int j =0;j<k;j++){
        for(int i =j;i<nums.length;i++){
            min = Math.min(nums[i],min);
            max = Math.max(nums[i],max);
            
        }
        result = result + (max-min);
    }
        return result;
    }
}
public class Maximum_Total_Subarray_Value_II{
    public static void main(String[] args) {
        int arr[] = {11,8};
        int k =2;
        Solution sol = new Solution();
        System.out.println(sol.maxTotalValue(arr,k));
    }
}