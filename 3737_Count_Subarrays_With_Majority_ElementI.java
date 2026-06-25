class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int count =0;
        for(int i =0;i<nums.length;i++){
            //for counting the target element in subarrays
            int targetCount=0;
            for(int j =i;j<nums.length;j++){
                if(nums[j]==target){
                    targetCount++;
                }
                //length of subarray
                int length = j-i+1;
                if(targetCount *2 >length){
                    count++;
                }
            }
        }
        return count;

    }
}