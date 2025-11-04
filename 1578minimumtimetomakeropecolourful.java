class Solution {
    public int minCost(String colors, int[] neededTime) {
        int cost = 0;
        for(int i =0;i<colors.length();){
        int max =0,sum=0;
        char ch =colors.charAt(i);

        while(i<colors.length() && ch==colors.charAt(i)){
            max = Math.max(max ,neededTime[i]);
            sum +=neededTime[i];
            i++;
        }cost += (sum -max);
        }return cost;
    }
}