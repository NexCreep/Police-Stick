package me.nexcreep.policestick;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    public void info(String text, Boolean save){
        String flog = String.format("[Police Stick/INFO] %s", text);
        System.out.println(flog);
        if (save){
            WriteInLog(flog);
        }
    }
    public void error(String text, Boolean save){
        String flog = String.format("[Police Stick/ERROR] %s", text);
        System.out.println(flog);
        if (save){
            WriteInLog(flog);
        }
    }
    public void debug(String text, Boolean save){
        String flog = String.format("[Police Stick/DEBUG] %s", text);
        System.out.println(flog);
        if (save){
            WriteInLog(flog);
        }
    }
    private void WriteInLog(String log){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        File f = new File("plugins/PoliceStick");
        System.out.println(f.mkdir());

        String TXTname = String.format("plugins/PoliceStick/%s.txt", dtf.format(now));

        try{
            String flog = String.format("%s", log);
            FileWriter cursor = new FileWriter(TXTname);
            BufferedWriter writer = new BufferedWriter(cursor);
            writer.write(flog);
            writer.newLine();
            cursor.close();
            debug("Successfully wrote in log file", false);

        }catch (IOException e){
            System.out.printf("[Police Stick/CRITICAL ERROR] %s");
            e.printStackTrace();
        }
    }


}
