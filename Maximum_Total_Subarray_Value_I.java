import java.math.*;
class Solution {
    public long maxTotalValue(int[] nums, int k) {
        long min = Long.MAX_VALUE;
        long max=Long.MIN_VALUE;
        for(int i =0;i<nums.length;i++){
            min = Math.min(nums[i],min);
            max = Math.max(nums[i],max);
        }
        return k*(max-min);
    }
}
class Maximum_Total_Subarray_Value_I{
    public static void main(String[] args) {
        Solution sol = new Solution();
        int arr[] = {701025805,484014287,486484825,479659005,127752519,497392660,905035207,885813233,36336196,83624455,562558760,504283643,414557507,340461196,75269772,787067318,310705037,994901461,509673195,908722607,69228965,239220571,719440526,986897320};
        int k =78;
        System.out.println(sol.maxTotalValue(arr, k));
        
    }
}
//[701025805,484014287,486484825,479659005,127752519,497392660,905035207,885813233,36336196,83624455,562558760,504283643,414557507,340461196,75269772,787067318,310705037,994901461,509673195,908722607,69228965,239220571,719440526,986897320]