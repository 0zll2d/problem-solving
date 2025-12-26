class Solution {
    public int solution(int num) {
        return collatz(num, 0);
    }
    
    private int collatz(long n, int count) {
        if (n == 1) {
            return count;
        }
        if (count == 500) {
            return -1;
        }
        
        if (n % 2 == 0) {
            return collatz(n / 2, count + 1);
        } else {
            return collatz(n * 3 + 1, count + 1);
        }
    }
}

// Overflow 방지를 위한 int → long 변환