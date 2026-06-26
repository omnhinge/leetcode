class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        
        int[] freq = new int[2*n+1];
        int offset =n;
        long totalSubarrays =0;
        int currentPrefix =0;
        long smallerCount =0;
        

        // initialize the empty prefix (sum =00)
        freq[0+offset] =1;
        for(int num : nums){
            if(num == target){
                smallerCount+= freq[currentPrefix + offset];
                currentPrefix++;
            }else{
                currentPrefix --;
                smallerCount -= freq[currentPrefix + offset];
            }
            totalSubarrays += smallerCount;
            freq[currentPrefix + offset]++;
        }
        return totalSubarrays;
    }
}
// class Solution {
//     public long countMajoritySubarrays(int[] nums, int target) {
//         long count =0;
//         for(int i =0;i<nums.length;i++){
//             //for counting the target element in subarrays
//             int targetCount=0;
//             for(int j =i;j<nums.length;j++){
//                 if(nums[j]==target){
//                     targetCount++;
//                 }
//                 //length of subarray
//                 int length = j-i+1;
//                 if(targetCount *2 >length){
//                     count++;
//                 }
//             }
//         }
//         return count;
//     }
// }