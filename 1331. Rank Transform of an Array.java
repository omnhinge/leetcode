// class Solution {
//     public int[] arrayRankTransform(int[] arr) {
//         HashMap<Integer , Integer > map = new HashMap<>();
//         TreeSet<Integer > set = new TreeSet<>();
//         for (int element : arr){
//             set.add(element);
//         }
//         int rank =1;
//         for(int num : set){
//             map.put(num ,rank);
//             rank++;
//         }
//         for(int i = 0;i<arr.length;i++){
//             arr[i] = map.get(arr[i]);
//         }return arr;
//     }
// }

//better solution
class Solution{
    public int[] arrayRankTransform(int[] arr){
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        HashMap<Integer , Integer> mp = new HashMap<>();

        int rank =1;
        for(int num : sorted){
            if(!mp.containsKey(num)){
                mp.put(num , rank++);
            }
        }
        int[] res = new int[arr.length];

        for(int i =0;i<arr.length;i++){
            res[i]= mp.get(arr[i]);
        }
        return res;
    }
}