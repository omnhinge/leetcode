class Solution {
    public int maxNumberOfBalloons(String text) {
// sol_1        Map<Character , Integer> map = new HashMap();
//         for(char i : text.toCharArray()){
//             if(i=='b' || i == 'a' || i=='l' || i=='o' || i=='n'){
//                 map.put(i,map.getOrDefault(i,0)+1);
//             }
//         }
//         int single = Math.min(map.getOrDefault('b',0),Math.min(map.getOrDefault('a',0),map.getOrDefault('n',0)));
//         int doublee = Math.min(map.getOrDefault('l',0),map.getOrDefault('o',0));
//         return Math.min(doublee/2 ,single);
//     }
// }
//balloon
//oo
//ll
//b , a ,n

//sol 2 96.67% beats
    int b_count =0;
    int a_count =0;
    int n_count =0;
    int l_count =0;
    int o_count =0;
    for(char i : text.toCharArray()){
        if(i=='b') b_count++;
        else if(i=='a') a_count++;
        else if(i=='l') l_count++;
        else if(i=='o') o_count++;
        else if(i=='n') n_count++;
    }
    return Math.min(Math.min(b_count,a_count),Math.min(n_count,Math.min(o_count/2,l_count/2)));
    }
}