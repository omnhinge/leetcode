// // 3312. Sorted GCD Pair Queries

// import java.util.Arrays;
// class Solution {
//     public int[] gcdValues(int[] nums, int[] queries) {
//         int f= 0,n=nums.length;
//         int size = n*(n-1)/2;
//         int gcdpairs[] = new int[size]; 
        
//         for(int i =0;i<n-1;i++){
//             for(int j =i+1;j<n;j++){
//                 gcdpairs[f] = gcd(nums[i],nums[j]);
//                 f++;
//             }
//         }
//         Arrays.sort(gcdpairs);
//         int[] ans = new int[queries.length];

//         for(int i = 0; i < queries.length; i++){
//         ans[i] = gcdpairs[queries[i]];
//         }

//         return ans;
//         // for(int i =0;i<n;i++){
//         //     nums[i] = gcdpairs[queries[i]];
//         // }
//         // return nums;
        
//         // for(int i =0;i<gcdpairs.length;i++){
//         //     System.out.println(gcdpairs[i]);
//         // }
//         // return gcdpairs;
    
//     }
//     private int gcd (int a , int b){
//         while(b !=0){
//             int temp = b;
//             b = a%b;
//             a = temp;
//         }
//         return a;
//     }

// }
// class Sorted_GCD_Pair_Queries{
//     public static void main(String args[]){
//         Solution sol = new Solution();
//         int[] arr = {4,4,2,1};
//         int[] que = {5,3,1,0};
//         int ans[] = sol.gcdValues(arr,que);
//         for(int i =0;i<ans.length;i++){
//             System.out.println(ans[i]);
//         }

//     }
// }

// class Solution {
//     public int[] gcdValues(int[] nums, long[] queries) {
//         int f= 0,n=nums.length;
//         long size = (long)n*(n-1)/2;
//         int gcdpairs[] = new int[(int)size]; 
        
//         for(int i =0;i<n-1;i++){
//             for(int j =i+1;j<n;j++){
//                 gcdpairs[f] = gcd(nums[i],nums[j]);
//                 f++;
//             }
//         }
//         Arrays.sort(gcdpairs);
//         int[] ans = new int[queries.length];

//         for(int i = 0; i < queries.length; i++){
//         ans[i] = gcdpairs[(int)queries[i]];
//         }

//         return ans;
//         // for(int i =0;i<n;i++){
//         //     nums[i] = gcdpairs[queries[i]];
//         // }
//         // return nums;
        
//         // for(int i =0;i<gcdpairs.length;i++){
//         //     System.out.println(gcdpairs[i]);
//         // }
//         // return gcdpairs;
    
//     }
//     private int gcd (int a , int b){
//         while(b !=0){
//             int temp = b;
//             b = a%b;
//             a = temp;
//         }
//         return a;
//     }

// }

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {

        int max = 0;

        for (int x : nums)
            max = Math.max(max, x);

        int[] freq = new int[max + 1];

        for (int x : nums)
            freq[x]++;

        int[] divisible = new int[max + 1];

        // Count numbers divisible by every divisor
        for (int d = 1; d <= max; d++) {
            for (int m = d; m <= max; m += d) {
                divisible[d] += freq[m];
            }
        }

        long[] pair = new long[max + 1];

        // Count pairs divisible by d
        for (int d = 1; d <= max; d++) {
            long k = divisible[d];
            pair[d] = k * (k - 1) / 2;
        }

        // Inclusion-Exclusion
        for (int d = max; d >= 1; d--) {
            for (int m = d * 2; m <= max; m += d) {
                pair[d] -= pair[m];
            }
        }

        // Prefix sums
        long[] prefix = new long[max + 1];

        for (int i = 1; i <= max; i++) {
            prefix[i] = prefix[i - 1] + pair[i];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            long q = queries[i] + 1;

            int l = 1, r = max;

            while (l < r) {

                int mid = (l + r) / 2;

                if (prefix[mid] >= q)
                    r = mid;
                else
                    l = mid + 1;
            }

            ans[i] = l;
        }

        return ans;
    }
}