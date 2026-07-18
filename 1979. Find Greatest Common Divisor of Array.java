class Solution {
    public int findGCD(int[] nums) {
        // Arrays.sort(nums);
        // return gcd(nums[0],nums[nums.length-1]);
        int min =Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i =0;i<nums.length;i++){
            if(nums[i] < min){
                min =nums[i];
            }
            if(nums[i] > max){
                max = nums[i];
            }
        }
        return gcd(min,max);
    }
    private int gcd(int a , int b){
        int temp =0;
        while(b!=0){
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}