class Solution {
    public int maxFrequencyElements(int[] nums) {
                HashMap<Integer, Integer> hp = new HashMap<>();
        
        // Count frequencies
        for (int num : nums) {
            hp.put(num, hp.getOrDefault(num, 0) + 1);
        }
        
        // Find max frequency
        int maxFreq = 0;
        for (int freq : hp.values()) {
            maxFreq = Math.max(maxFreq, freq);
        }
        
        // Sum frequencies of elements that have maxFreq
        int result = 0;
        for (int freq : hp.values()) {
            if (freq == maxFreq) {
                result += freq;
            }
        }
        
        return result;
    }
}