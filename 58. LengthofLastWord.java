class Solution {
    public int lengthOfLastWord(String s) {
        int count =0;
        String[] parts = s.split(" ");          
        return parts[parts.length-1].length();
        
    }
}
