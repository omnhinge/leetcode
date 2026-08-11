class Solution {
    public int missingInteger(int[] nums) {
     int total = nums[0];
     for(int i =1;i<nums.length;i++){
        if(nums[i]==nums[i-1]+1){
            total += nums[i];
        }else {
            break;
        }
    }   
    Set<Integer> numset = new HashSet<>();
    for(int num :  nums){
        numset.add(num);
    }
    while(numset.contains(total)){
        total++;
    }
    return total;
    }
}