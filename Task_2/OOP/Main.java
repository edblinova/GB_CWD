package OOP;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        ToyStore toys1 = new ToyStore();
        System.out.println(toys1);
        
        ParticipantQueue pq = new ParticipantQueue(List.of(
                new Participant("Женя"),
                new Participant("Петя"),
                new Participant("Света"),
                new Participant("Галя"),
                new Participant("Вася"),
                new Participant("Данила"),
                new Participant("Денис"),
                new Participant("Катя"),
                new Participant("Оля")
        ));
        
        Raffle raf = new Raffle(pq,toys1);
        System.out.println(raf.getCurrentToys().toString());
        raf.runRaffle();

        // System.out.println(toys1.toString());
        toys1.saveToFile();
    }

}