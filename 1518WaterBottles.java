class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int remain=numBottles,count =numBottles;
        while(remain >= numExchange){
            int newBottles = remain / numExchange;
            count += newBottles;
            remain= remain % numExchange + newBottles;
        }return count ;
    }
}