import java.util.*;

class Solution {
    
    Map<String, Character> sharp = new HashMap<>();

    static class Music {
        String name;
        int time;
        int idx;

        Music(String name, int time, int idx) {
            this.name = name;
            this.time = time;
            this.idx = idx;
        }
    }

    public String solution(String m, String[] musicinfos) {

        sharp.put("C#", '1');
        sharp.put("D#", '2');
        sharp.put("F#", '3');
        sharp.put("G#", '4');
        sharp.put("A#", '5');

        PriorityQueue<Music> pq = new PriorityQueue<>((m1, m2) -> {
            if(m1.time == m2.time) {
                return m1.idx - m2.idx;
            }
            return m2.time - m1.time;
        });

        for(int i = 0; i < musicinfos.length; i++) {
            String[] mi = musicinfos[i].split(",");

            int time = calculateTime(mi[0], mi[1]);         // 재생 시간
            String name = mi[2];                            // 음악 제목
            String melodies = checkMelody(mi[3]);           // 멜로디

            StringBuilder playing = new StringBuilder();    // 재생 시간 내 들리는 멜로디

            for(int j = 0; j < time; j++) {
                playing.append(melodies.charAt(j % melodies.length()));
            }

            if(playing.toString().contains(checkMelody(m))) {
                pq.offer(new Music(name, time, i));
            }
        }

        if(pq.isEmpty()) {
            return "(None)";
        }

        return pq.poll().name;
    }

    int calculateTime(String start, String end) {
        int shour = Integer.parseInt(start.substring(0, 2));
        int smin = Integer.parseInt(start.substring(3, 5));

        int ehour = Integer.parseInt(end.substring(0, 2));
        int emin = Integer.parseInt(end.substring(3, 5));

        return ehour * 60 + emin - (shour * 60 + smin);
    }

    String checkMelody(String melody) {
        StringBuilder melodies = new StringBuilder();

        for(int i = 0; i < melody.length(); i++) {
            if(i < melody.length() - 1 && melody.charAt(i + 1) == '#') {
                melodies.append(sharp.get(melody.substring(i, i + 2)));
                i++;
            } else {
                melodies.append(melody.charAt(i));
            }
        }

        return melodies.toString();
    }
}