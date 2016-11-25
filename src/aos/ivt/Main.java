package aos.ivt;

import java.io.*;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {

            Date StartPro = new Date(System.currentTimeMillis());
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            System.err.println(StartPro);
            String pathToOutputFile = "outputdir" + File.separator + "header.bsq";
            File PartResultFilePath = new File("inputdir");
            File ResultFilePath = new File("outputdir");

            File[] inlistFile = PartResultFilePath.listFiles();
            File[] outlistFile = ResultFilePath.listFiles();

            for (int i = 0; i < outlistFile.length; i++) {
                new File("outputdir" + File.separator + outlistFile[i].getName()).delete();
            }

            List<String> listString;
            listString = Arrays.asList(PartResultFilePath.list());
            Collections.sort(listString);

            new File(pathToOutputFile).createNewFile();

            for (int i = 0; i < inlistFile.length; i++) {
                funcChanelCat(new FileOutputStream(pathToOutputFile, true).getChannel(),
                        new FileInputStream("inputdir" + File.separator + listString.get(i)).getChannel());

            }

            System.err.println(new Date(System.currentTimeMillis()));
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

        }

    }

    private static void funcChanelCat(FileChannel _headercanal, FileChannel _secondcanal)
    {
        try {
            _headercanal.transferFrom(_secondcanal, _headercanal.size(), _secondcanal.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
