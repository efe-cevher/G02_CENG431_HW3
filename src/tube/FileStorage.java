package tube;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileStorage implements IStorage{

    String filePath;

    public FileStorage(String filePath) { this.filePath = filePath; }

    public void save(String data){
        File file = new File(filePath);
        FileWriter fr = null;
        BufferedWriter br = null;
        String dataWithNewLine=data+System.getProperty("line.separator");
        try{
            fr = new FileWriter(file);
            br = new BufferedWriter(fr);
            br.write(dataWithNewLine);

        } catch (IOException e) {
            e.printStackTrace();

        }finally{
            try {
                if(br != null){
                    br.close();
                    fr.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void append(String data){
        File file = new File(filePath);
        FileWriter fr = null;
        BufferedWriter br = null;
        String dataWithNewLine=data+System.getProperty("line.separator");
        try{
            fr = new FileWriter(file, true);
            br = new BufferedWriter(fr);
            br.write(dataWithNewLine);

        } catch (IOException e) {
            e.printStackTrace();

        }finally{
            try {
                if(br != null){
                    br.close();
                    fr.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
