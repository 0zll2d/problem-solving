import java.util.*;
import java.io.*;

class Solution {
    
    class Task {
        String name;
        String start;
        int hour;
        int min;
        int playtime;
        int starttime;
        int endtime;    // 과제 끝난 시각
        
        Task(String name, String start, String playtime) {
            this.name = name;
            this.start = start;
            this.hour = Integer.parseInt(start.substring(0, 2));
            this.min = Integer.parseInt(start.substring(3, 5));
            this.playtime = Integer.parseInt(playtime);
            this.starttime = this.hour * 60 + this.min;
            this.endtime = starttime + this.playtime;
        }
        
        void setPlaytime(int playtime) {
            this.playtime = playtime;
        }
    }
    
    public String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        
        PriorityQueue<Task> pq = new PriorityQueue<>((t1, t2) -> t1.start.compareTo(t2.start)); // 새로 시작해야 하는 과제 목록
        Deque<Task> stk = new ArrayDeque<>();   // 잠시 멈춘 과제(Stack)
        
        for(String[] plan: plans) {
            pq.offer(new Task(plan[0], plan[1], plan[2]));
        }
        
        while(pq.size() > 1) {
            Task now = pq.poll();
            
            if(now.endtime <= pq.peek().starttime) {
                answer.add(now.name);
                
                int diff = pq.peek().starttime - now.endtime;   // 다음 새로운 과제 시작 시간과 지금 과제 끝나는 시간의 차이 계산
                
                // 시간의 차이가 잠시 멈춘 과제의 남은 시간 보다 작으면 남은 과제 재개
                while(!stk.isEmpty() && diff > 0) {
                    Task task = stk.pop();
                    
                    if(task.playtime <= diff) {
                        answer.add(task.name);   
                        diff -= task.playtime;
                    } else {
                        // Stack 최상단 남은 시간: 30분이고 diff: 10분이면, 10분만 재개
                        task.setPlaytime(task.playtime - diff);
                        stk.push(task);
                        diff = 0;
                    }
                }
                
            } else {
                int leftPlaytime = now.playtime - (pq.peek().starttime - now.starttime);    // 남은 시간 계산
                now.setPlaytime(leftPlaytime);
                stk.push(now);
            }
            
        }
        
        answer.add(pq.poll().name);
        
        while(!stk.isEmpty()) {
            answer.add(stk.pop().name);
        }
        
        return answer.toArray(String[]::new);
    }
}