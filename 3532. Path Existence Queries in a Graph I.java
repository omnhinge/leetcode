class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int comp[] = new int[n];
        for(int i =1;i< n;i++){
            int diff = Math.abs(nums[i] - nums[i-1]);
            if(diff <= maxDiff){
                comp[i] = comp[i-1];
            }else comp[i] =i;
        }
        boolean ans[] = new boolean[queries.length];
        for(int i =0;i<queries.length;i++){
            int node1= queries[i][0] , node2 = queries[i][1];
            if(comp[node1]== comp[node2]){
                ans[i] = true;
            }
        }
        return ans;
    }
}
//space = O(n)
// time = O(n)