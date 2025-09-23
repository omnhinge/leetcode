public class Sqrt {
    public static void main (String args[]){
        Solution obj = new Solution();
        System.out.println(obj.mySqrt(2147395600));
        System.out.println(obj.mySqrt(4));
    }
}
class Solution {
    public int mySqrt(long x) {
        long i =0;
        while((i*i) <= x){
            
            i++;
        }
        return Int(i-1);
    }
}