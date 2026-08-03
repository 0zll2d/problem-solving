class Solution {
    
    public String solution(String m, String[] musicinfos) {

        m = m.replaceAll("C#", "c")
                .replaceAll("D#", "d")
                .replaceAll("F#", "f")
                .replaceAll("G#", "g")
                .replaceAll("A#", "a");

        int maxTime = -1;
        String answer = "(None)";

        for(String musicinfo : musicinfos) {
            String[] mi = musicinfo.split(",");

            int time = calculateTime(mi[0], mi[1]);
            String name = mi[2];
            String melody = mi[3];

            melody = melody.replaceAll("C#", "c")
                        .replaceAll("D#", "d")
                        .replaceAll("F#", "f")
                        .replaceAll("G#", "g")
                        .replaceAll("A#", "a");

            StringBuilder playing = new StringBuilder();

            for(int i = 0; i < time; i++) {
                playing.append(melody.charAt(i % melody.length()));
            }

            if(playing.toString().contains(m)) {
                if(maxTime < time) {
                    maxTime = time;
                    answer = name;
                }
            }

        }

        return answer;
    }

    int calculateTime(String start, String end) {
        int shour = Integer.parseInt(start.substring(0, 2));
        int smin = Integer.parseInt(start.substring(3, 5));

        int ehour = Integer.parseInt(end.substring(0, 2));
        int emin = Integer.parseInt(end.substring(3, 5));

        return ehour * 60 + emin - (shour * 60 + smin);
    }
}