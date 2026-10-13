class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        
        Map<String, List<String>> group = new HashMap<>();

        for(String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);   // char[] -> String 변환하는 법: new String(char[] value)

            group.computeIfAbsent(key, k -> new ArrayList<>()).add(str);    // [Map] Java computeIfAbsent(): Key가 없으면 ArrayList 생성·저장 후 반환, 있으면 기존 List 반환
        }

        return new ArrayList<>(group.values()); // [Map] Java values(): 모든 Value를 Collection으로 반환
    }
}