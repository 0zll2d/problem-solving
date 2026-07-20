import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        List<String> cache = new ArrayList<>();
        
        for(String city : cities) {
            int idx = cache.indexOf(city.toLowerCase());
            
            cache.add(city.toLowerCase());  
            
            if(idx == -1) {             // cache miss
                if(cache.size() > cacheSize) {
                    cache.remove(0);
                }
                answer += 5;
            } else {                    // cache hit
                cache.remove(idx);
                answer += 1;
            }
        }
        
        return answer;
    }
}