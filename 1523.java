class Solution {
    public int countOdds(int low, int high) {
        int space =high -low;
        space  = space/2;
        
        if(low %2 !=0 && high %2!=0) return space+1;
        else if(low %2 ==0 && high %2!=0) return space+1;
        else if(low %2 !=0 && high %2==0) return space+1;
        else return space;
    }
}