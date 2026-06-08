class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int least =0;int bro=0;int pCount =0;
        for(int num : nums ){
            if(num < pivot){
                least++;
            }else if(num > pivot){
                bro++;
            }else{pCount++;}
        }
        int i =0;
        int j = least;
        int k = least+pCount;
        int res[] = new int[nums.length];
        for(int num : nums ){
            if(num < pivot){
                res[i] = num;
                i++;
            }else if(num > pivot){
                res[k] = num;
                k++;
            }else{
                res[j] = num;
                j++;
            }
        }
        return res;
    }
}