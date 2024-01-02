package OOP;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class Raffle {
    ToyStore currentToys;
    ParticipantQueue currentParticipants;
    double lossWeight = 0;
    int lossId;

    WeightCalc cc = new WeightCalc();
    Raffle.QuantityCalc qc = new Raffle.QuantityCalc();

    public Raffle(ParticipantQueue kids, ToyStore currenToyStore) {

        this.currentToys = cc.assignWeight(currenToyStore);
        this.currentParticipants = kids;
    }
    
    public void runRaffle() {
        ParticipantQueue kids = this.currentParticipants;
        ToyStore currenToyStore = this.currentToys;
        PriorityQueue<Toy> prizes = new PriorityQueue<>(currenToyStore.toys.values());
        try {
            BufferedWriter log = FileIO.raffleLog();
            while(kids.iterator().hasNext()){
                double winRoll = cc.doRoll();
                Participant k = kids.iterator().next();
                try {
                    Toy win = cc.checkPrize(prizes, winRoll);
                    showRoll(k, win, winRoll);
                    prizes = qc.adjustQuantityLeft(win, currenToyStore, prizes);
                    log.write(showWin(k, win) + "\n");
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            log.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public ToyStore getCurrentToys() {
        return currentToys;
    }

    String showWin(Participant kid, Toy prize) {
        String winLine;
        if(prize.name.equals("ничего")){
            winLine = kid.toString() + " не выиграл ничего";
        } else {
            winLine = kid.toString() + " выиграл " + prize.name;
        }
        System.out.println(winLine);
        return winLine;
    }

    void showRoll(Participant kid, Toy prize, double roll) {
        System.out.printf("%s бросает на %.3f , это ниже шанса %.2f у %s%n", kid.name, roll, prize.getWeight(), prize.name);
    }
    
    class QuantityCalc {
        PriorityQueue<Toy> adjustQuantityLeft(Toy winToy, ToyStore currenToyStore,PriorityQueue<Toy> currentQueue) {
            if(winToy.quantity > 0){
                winToy.quantity -= 1;
            }
            if(winToy.quantity == 0){
                removeStock(winToy.id, currenToyStore);
                Raffle.this.cc.assignWeight(currenToyStore);
                currentQueue = new PriorityQueue<>(currenToyStore.toys.values());
            }
            return currentQueue;
        }

        void removeStock(int idNum, ToyStore toys) {
            toys.removeToy(idNum);
        }
    }
}

class WeightCalc {
    Random r = new Random();
    double maxWeight;
    double totalWeight;
    
    double doRoll(){
        return r.nextDouble()*maxWeight;
    }
    
    Toy checkPrize(PriorityQueue<Toy> prizes, double roll) throws Exception {
        PriorityQueue<Toy> onePoll = new PriorityQueue<>(prizes);

        while(!onePoll.isEmpty()){
            Toy p = onePoll.poll();
            if(roll <= p.getWeight()){
                return checkTies(onePoll,p);
            }
        }
        throw new Exception("Приз с такой вероятностью не найден");
    }
    
     ToyStore assignWeight(ToyStore currenToyStore){
        this.totalWeight = 0;
        this.maxWeight = 0;
        for (Toy toy:currenToyStore.toys.values()){
            this.totalWeight += toy.weight;
        }

        for (Toy toy:currenToyStore.toys.values()){
            double ch = toy.weight/totalWeight;
            toy.setWeight(ch);
            if(maxWeight < ch ){
                maxWeight = ch;
            }
        }
        return currenToyStore;
    }
    
    Toy checkTies(PriorityQueue<Toy> leftovers, Toy drawn  ){
        PriorityQueue<Toy> tiePoll = new PriorityQueue<>(leftovers);
        ArrayList<Toy> sameWeight = new ArrayList<>();
        while(!tiePoll.isEmpty()){
            if(drawn.getWeight() == tiePoll.peek().getWeight()){
                sameWeight.add(tiePoll.poll());
            }else {break;}
        }
        sameWeight.add(drawn);
        int pickRandom = r.nextInt(sameWeight.size());
        return sameWeight.get(pickRandom);
    }
}
