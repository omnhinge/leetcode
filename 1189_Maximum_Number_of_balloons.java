class Solution {
    public int maxNumberOfBalloons(String text) {
        Map<Character , Integer> map = new HashMap();
        for(char i : text.toCharArray()){
            if(i=='b' || i == 'a' || i=='l' || i=='o' || i=='n'){
                map.put(i,map.getOrDefault(i,0)+1);
            }
        }
        int single = Math.min(map.getOrDefault('b',0),Math.min(map.getOrDefault('a',0),map.getOrDefault('n',0)));
        int doublee = Math.min(map.getOrDefault('l',0),map.getOrDefault('o',0));
        return Math.min(doublee/2 ,single);
    }
}
//balloon
//oo
//ll