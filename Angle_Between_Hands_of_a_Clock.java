class Angle_Between_Hands_of_a_Clock{
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.angleClock(3,15));
    }
}
// class Solution {
//     public double angleClock(int hour, int minutes) {
//         if (hour == 12){hour =0;}
//         double min_degree =minutes *6;
//         double mino_degree =(double(minutes)/2);
//         double hour_degree = hour *30 + mino_degree;
//         double result = 0;
//         System.out.println(hour_degree);
//         System.out.println(min_degree);
//         if(min_degree > hour_degree){
//             result = min_degree - hour_degree;
//         }
//         else {
//             result = hour_degree - min_degree;
//         }
//         return result %180;
//     }
// }

class Solution {
    public double angleClock(int hour, int minutes) {

        double minuteAngle = minutes * 6;
        double hourAngle = (hour % 12) * 30 + minutes * 0.5;

        double diff = Math.abs(hourAngle - minuteAngle);

        return Math.min(diff, 360 - diff);
    }
}