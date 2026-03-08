// 1980 
//1980. Find Unique Binary String
// what we got String of binary 
// we will use 
class Solution {
    public String findDifferentBinaryString(String[] nums) {
        StringBuilder sb = new StringBuilder();
        for(int i =0 ;i<nums.length;i++){
            if(nums[i].charAt(i)=='0'){
                sb.append('1');
            }
            else{sb.append('0');}
        }
        return sb.toString();
    }
}
class FindUniqueBinaryString{
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] nums = {"101","010","111"};
        System.out.println(s.findDifferentBinaryString(nums));
    }
}