class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        for(int ast : asteroids){
            if(mass < ast){
                return false;
            }
            else if(mass >= 100000){
                return true;
            }
            mass += ast;
        }return true;
    }
}122. Best Time to Buy and Sell Stock II