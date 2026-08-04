class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
            if(num > max) max = num;
            if(num < min) min = num;
        }
        for(int i = min ; i<=max;i++){
            if(!set.contains(i)){
                ans.add(i);
            }
        }
        return ans;
    }
}