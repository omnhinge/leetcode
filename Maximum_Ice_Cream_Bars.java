import java.util.Arrays;
class Solution {
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int count=0;
        for(int i =0;i<costs.length;i++){
            if(costs[i] > coins){
               break;
                
            }
            coins = coins - costs[i];
            count++;
            
        }
        
        return count;
    }
}
public class Maximum_Ice_Cream_Bars{
    public static void main (String[] args){
        Solution sol = new Solution();
        int[] costs = {1,6,3,1,2,5};
        int coins = 20;
        System.err.println(sol.maxIceCream(costs, coins));
    }
}