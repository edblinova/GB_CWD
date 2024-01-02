package OOP;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class ToyStore {
    HashMap<Integer,Toy> toys = new HashMap<>();
    FileIO f = new FileIO();
    protected int maxKey;

    public ToyStore(HashMap<Integer,Toy> toys) {
        this.toys = toys;
        this.maxKey = Collections.max(toys.keySet());
    }
    public ToyStore() {
        this.readFromFile("./toys_before.txt");
        this.maxKey = Collections.max(toys.keySet());
    }

    void addToyStore(Collection<Toy> newtoys){
        for (Toy newtoy: newtoys){
            this.addToy(newtoy);
        }
    }

    int addToy(Toy newtoy){
        int finalId = newtoy.id;
        if(toys.containsKey(finalId)){
            finalId = ++maxKey;
            newtoy.setId(finalId);
        }
        toys.put(finalId, newtoy);
        return finalId;
    }

    void removeToy(int id){
        toys.remove(id);
    }
    
    void readFromFile(String filepath){
        for (String line:f.readToys(filepath)){
            String[] toyParams = line.split(" ",4);
            int toyId = Integer.parseInt(toyParams[0]);
            toys.put(toyId, 
                     new Toy(toyId,
                             toyParams[1],
                             Integer.parseInt(toyParams[2]),
                             Double.parseDouble(toyParams[3]))
                    );
        }

    }

    public void saveToFile(){
        try(FileWriter writer = new FileWriter("./toys_after.txt", false))
        {
            writer.write(this.toString());
            writer.flush();
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Toy toy: toys.values()){
            sb.append(toy.id + " " + toy.name + " " + toy.quantity + " " + toy.weight);
            sb.append("\n");
        }
        return sb.toString();
    }
}