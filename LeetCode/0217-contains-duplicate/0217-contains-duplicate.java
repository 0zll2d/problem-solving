class Solution {
    public boolean containsDuplicate(int[] nums) {

        Set<Integer> st = new HashSet<>();

        for(int num : nums) {
            st.add(num);    // [Set] Java는 추가 add, C++은 insert
        }
        
        return st.size() != nums.length;    // [Set] Java와 C++은 size
    }
}