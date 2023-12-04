package everything;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static List<String> loadFile(String path) {
        List<String> content = new ArrayList<>();
        try
        {
            File file=new File(path);    //creates a new file instance
            FileReader fr=new FileReader(file);   //reads the file
            BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
            String line;
            while((line=br.readLine())!=null)
            {
                content.add(line);      //appends line to string buffer
            }
            fr.close();    //closes the stream and release the resources
            System.out.println("Successfully loaded File!");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return content;
    }

    public static List<List<String>> asPackets(List<String> input) {
        List<List<String>> output =  new ArrayList<>();

        List<String> current = new ArrayList<>();
        for(String entry : input) {
            if(entry.length() == 0) {
                output.add(current);
                current = new ArrayList<>();
            }
            else {
                current.add(entry);
            }
        }
        output.add(current);

        return output;
    }

    public static List<Integer> parseInt(List<String> strings) {
        return strings.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    public static List<Integer> parseInt(String[] strings) {
        return parseInt(Arrays.asList(strings));
    }

    public static Integer[] parseArrayInt(String[] strings) {
        return Arrays.stream(strings).map(Integer::parseInt).toArray(Integer[]::new);
    }
}
