package OOP;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

public class FileIO {

    List<String> readToys(String filepath) {
        try {
            return Files.readAllLines(Paths.get(filepath));

        } catch(IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public static BufferedWriter raffleLog() throws IOException{

        BufferedWriter bw = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream("./raffle_winners.txt",true), StandardCharsets.UTF_8)
        );
        bw.write(LocalDateTime.now().toString()+"\n");
        return bw;
    }

}