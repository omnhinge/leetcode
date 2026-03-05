// 98% space complexity 
class Solution {
    public int minOperations(String s) {
       
        return Math.min(oatone(s), oatzero(s));
    }
    private int oatone(String s){
        int count = 0;
        for(int i=0;i<s.length();i++){
            if(i%2==0 && s.charAt(i)!='0') count++;
            else if(i%2==1 && s.charAt(i)!='1') count++;
        }
        return count;
    }
    private int oatzero(String s){
        int count = 0;
        for(int i=0;i<s.length();i++){
            if(i%2==0 && s.charAt(i)!='1') count++;
            else if(i%2==1 && s.charAt(i)!='0') count++;
        }
        return count;
    }
}
public class MinimumChangesToMakeAlternatingBinaryString{
    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "0100";
        System.out.println(sol.minOperations(s)); // Output: 1
    }
}