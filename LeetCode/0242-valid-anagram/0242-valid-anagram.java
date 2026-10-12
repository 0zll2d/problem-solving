class Solution {
    public boolean isAnagram(String s, String t) {
        
        // Map 자료구조로 개수 비교
        Map<Character, Integer> smap = new HashMap<>();
        Map<Character, Integer> tmap = new HashMap<>();

        for(char c : s.toCharArray()) {
            smap.put(c, smap.getOrDefault(c, 0) + 1);   // [Map] 삽입 Java는 put(k, v), C++은 insert({k, v})
        }

        for(char c : t.toCharArray()) {
            tmap.put(c, tmap.getOrDefault(c, 0) + 1);   // [Map] 초기값 세팅 Java는 getOrDefault(k, 0)
        }

        return smap.equals(tmap);   // [Map] 2개 key-value 동일 비교 Java는 map1.equals(map2), C++은 map1 == map2
    }
}