package OOP;

public class Toy implements Comparable<Toy> {

    int id;
    String name;
    int quantity;
    double weight;

    public Toy(int id, String name, int quantity, double weight) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Игрушка: id %d, название: %s, количество: %d, частота выпадения: %f", 
                             this.id, this.name, this.quantity, this.weight);
    }

    @Override
    public int compareTo(Toy o) {
        if(o.weight == this.weight){
            return 0;
        }
        return this.weight < o.weight ? -1 : 1;
    }
}