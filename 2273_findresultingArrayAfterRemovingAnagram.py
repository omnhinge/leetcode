class Solution:
    def removeAnagrams(self, words: List[str]) -> List[str]:
        result = [words[0]]
        for word in words[1:]:
            if sorted(word) != sorted(result[-1]):
                result.append(word)
        return result
        