// import java.util.*;
// public class maxDifference {

//     public int maxDifference(String s) {
//         int n = s.length();
//         int max =0,small = Integer.MAX_VALUE, large = Integer.MIN_VALUE;
//         int count = 0;
//         for(int i =1;i<n;i++){
            
//             if(s.charAt(i-1) == s.charAt(i)){
//                 count++;
                
//             }
//             small = Math.min(small, count);
//             large = Math.max(large, count);
//         }return large - small;
//     }
//     public static void main(String[] args) {
//         String s = "aabbcc";
//         System.out.println("Max Difference: " + maxDifference(s)); // Example usage
//     }
// }
import java.util.*;

public class maxDifference {

    public static int maxDifference(String s) {
        int n = s.length();
        if (n == 0) return 0;

        List<Integer> groupLengths = new ArrayList<>();
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                groupLengths.add(count);
                count = 1;
            }
        }
        groupLengths.add(count); // Add the last group

        int small = Collections.min(groupLengths);
        int large = Collections.max(groupLengths);
        return large - small;
    }

    public static void main(String[] args) {
        String s = "aaabbccc";
        System.out.println("Max Difference: " + maxDifference(s)); // Output: 0
    }
}
