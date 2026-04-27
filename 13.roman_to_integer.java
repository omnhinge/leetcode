class Solution {
    public int romanToInt(String s) {
        HashMap<Character, Integer> r2intmap = new HashMap<>();
        r2intmap.put('I',1);
        r2intmap.put('V',5);
        r2intmap.put('X',10);
        r2intmap.put('L',50);
        r2intmap.put('C',100);
        r2intmap.put('D',500);
        r2intmap.put('M',1000);
        int ans =0;
        for(int i = 0;i<s.length();i++){
            if(i+1<s.length() && r2intmap.get(s.charAt(i)) < r2intmap.get(s.charAt(i+1))){
                ans += r2intmap.get(s.charAt(i+1)) -  r2intmap.get(s.charAt(i)); 
                i++;
            }else {
                ans +=r2intmap.get(s.charAt(i));
            }
        }return ans;
    }
}