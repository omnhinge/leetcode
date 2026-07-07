class Solution {
    public long sumAndMultiply(int n) {
        long ans = 0;
        int sum =0;
        int temp =n;
        int rev =0;
        while(temp > 0){
           int digit = temp %10;
            if(digit != 0){
            sum = sum + digit;
            rev = rev *10 +digit;
            }
            temp /=10;
        }
        while(rev > 0){
            int digit = rev %10;
            ans = ans *10 +digit;
            rev /=10;
        }
        return sum*ans;
    }
}