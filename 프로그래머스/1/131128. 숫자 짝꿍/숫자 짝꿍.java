import java.util.*;

class Solution {
    public String solution(String X, String Y) {
        String[] sX = X.split("");
        Map<String, Integer> mX = new HashMap<>();

        for(int i = 0; i < sX.length; i++){
            mX.put(sX[i], mX.getOrDefault(sX[i], 0) + 1);
        }

        String[] sY = Y.split("");
        Map<String, Integer> mY = new HashMap<>();

        for(int i = 0; i < sY.length; i++){
            mY.put(sY[i], mY.getOrDefault(sY[i], 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        for(String key : mX.keySet()){
            if(mY.containsKey(key)){
                int count = Math.min(mX.get(key), mY.get(key));
                sb.append(key.repeat(count));
            }
        }

        // 짝꿍이 존재하지 않음
        if(sb.length() == 0){
            return "-1";
        }

        char[] arr = sb.toString().toCharArray();
        Arrays.sort(arr);

        // 짝꿍이 0으로만 구성
        if(arr[arr.length - 1] == '0'){
            return "0";
        }

        String answer = new String(arr);

        return new StringBuilder(answer).reverse().toString();
    }
}