class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int s = spells.length,p=potions.length;
        int spell =0;
        Arrays.sort(potions);
        for(int i=0;i<s;i++){
            spell = spells[i];
            spells[i]=p-mini(spell,potions,success);
        }
        return spells;
    }
    int mini (int spell,int[] potions,long success){
        int left =0,right = potions.length-1;
        while(left<=right){
            int mid = (left+right)/2;
            if((long)potions[mid]*spell>=success){
                right = mid -1;
            }else {
                left = mid+1;
            }
        }
        return left;
    }
}