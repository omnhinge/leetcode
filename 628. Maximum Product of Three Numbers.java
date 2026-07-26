class Solution {
    public int maximumProduct(int[] nums) {
        if(nums.length == 3){
            for(int i =1;i<3;i++){
                nums[0] = nums[0]*nums[i];
            }
            return nums[0];
        }
        else {
            int min = Integer.MAX_VALUE;
            int min2 = Integer.MAX_VALUE;
            int first = Integer.MIN_VALUE;
            int second = Integer.MIN_VALUE;
            int third = Integer.MIN_VALUE;
            for(int current : nums){
                if(current > first ){
                    third = second;
                    second = first;
                    first = current;
                }
                else if(current >second){
                    third = second;
                    second = current;
                }
                else if(current > third){
                    third = current;
                }
                if(current < min){
                    min2 =min;
                    min = current;
                }
                else if(current < min2){
                    min2 =current;
                }
            }
            return Math.max(first*second*third , first*min*min2);
        }
    }
}