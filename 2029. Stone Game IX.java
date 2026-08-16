class Solution {
    public boolean stoneGameIX(int[] stones) {
        // taking remainder only
        int count0=0,count1=0,count2=0; 
        for(int i =0;i<stones.length;i++){
            stones[i] = stones[i]%3;
            if(stones[i] ==0){
                count0++;
            }else if(stones[i] == 1){
                count1++;
            }
            else {count2++;}
        }
        if(count0%2==0){
            return count1>=1 && count2 >=1;
        }
        return count1-count2 >2 || count2-count1 >2;
    }
}