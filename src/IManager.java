import java.util.List;

public interface IManager <E>{
    void addToCSV();
    List<E> readFromCSV();
}
