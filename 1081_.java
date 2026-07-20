class Solution {
    public String smallestSubsequence(String s) {
        int[] last = new int[26];
        boolean[] used = new boolean[26];

        // Store last occurrence of every character
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        StringBuilder stack = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // Already included in our answer
            if (used[ch - 'a']) {
                continue;
            }

            // Remove bigger characters if they appear again later
            while (stack.length() > 0) {
                char top = stack.charAt(stack.length() - 1);

                if (top > ch && last[top - 'a'] > i) {
                    stack.deleteCharAt(stack.length() - 1);
                    used[top - 'a'] = false;
                } else {
                    break;
                }
            }

            stack.append(ch);
            used[ch - 'a'] = true;
        }

        return stack.toString();
    }
}