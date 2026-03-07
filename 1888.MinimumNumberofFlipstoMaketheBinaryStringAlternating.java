class Solution {
    public int minFlips(String s) {
        int n = s.length();
        int result =n;
        int s1count=0; //for 01010101
        int s2count=0; //for 10101010
        int i =0;
        int j=0;
        
        while(j<2*n){
            char exps1 = (j % 2 != 0) ? '0' : '1';
            char exps2 = (j % 2 != 0) ? '1' : '0';
            if(s.charAt(j%n) != exps1){
                s1count++;
            }
            if(s.charAt(j%n) != exps2){
                s2count++;
            }
            if(j-i+1>n){
                exps1 = (i % 2 != 0) ? '0' : '1';
                exps2 = (i % 2 != 0) ? '1' : '0';
                if(s.charAt(i%n)!=exps1){
                    s1count--;
                }
                if(s.charAt(i%n)!=exps2){
                    s2count--;
                }
                i++;
            }
            if(j-i+1==n){
                result = Math.min(result , Math.min(s1count,s2count));
            }
            j++;
        }return result;
    }
}
class MinimumNumberofFlipstoMaketheBinaryStringAlternating{
    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "101011";
        System.out.println(sol.minFlips(s));
    }
}