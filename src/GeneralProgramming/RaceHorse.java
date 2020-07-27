package GeneralProgramming;

import java.util.ArrayList;
import java.util.Arrays;

public class RaceHorse {

    public static class Horse{
        int speed;
        public Horse(int speed){
            this.speed = speed;
        }

        public int getSpeed() {
            return speed;
        }
    }

    public static class HorseRanch{
        int speed;
        ArrayList<Horse> horses;

        public HorseRanch(){
            horses = new ArrayList<>();
        }

        public boolean addHorse(Horse h){
            return horses.add(h);
        }

        public ArrayList<Horse> getAllHorse(){
            return horses;
        }
    }


    public class HorsePair{
        int speed;
        int ranking;
        String name;

        public HorsePair( int speed, int ranking, String name){
            this.speed = speed;
            this.ranking = ranking;
            this.name = name;
        }
    }

    public static int[] getTheFirstNHorses(int tracks, ArrayList<Horse> horses ){
      int[] ranking = new int[tracks];

      int[] trackHorses = new int[tracks];
      Horse[][] allHorses = new Horse[tracks][tracks];
      for(int i=0; i<25; i++){
          int row = i / 5;
          int col = i % 5;
          allHorses[row][col] = horses.get(i);
      }

      return ranking;

    };




    public static void main(String[] args) {
        // build the horse ranch
        HorseRanch horseRanch = new HorseRanch();
        for (int i=25; i>0; i--){
            horseRanch.addHorse(new Horse(i));
        }


    }


}
