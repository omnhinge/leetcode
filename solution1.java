public class solution1 {
    public static int maximumLength(int[] nums) {
        int ans =0;
        for(int i=0;i<nums.length-1;i++){
            if((((nums[i]%2==0) && (nums[i+1]%2==0)) || ((nums[i]%2!=0) && (nums[i+1]%2!=0)))){
                ans =ans+1;
            }

        }return ans;
    }

    public static void main (String args[]){
        int[] arr = {1,2,1,1,2,1,2};
        System.out.println(maximumLength(arr));
        int[] arr2 = {1,2,3,4};
        System.out.println(maximumLength(arr2));
    }
}
