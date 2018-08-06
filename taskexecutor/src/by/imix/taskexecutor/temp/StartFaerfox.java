package by.imix.taskexecutor.temp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by miha on 16.04.2016.
 */
public class StartFaerfox {
    private static final Logger _log= LoggerFactory.getLogger(StartFaerfox.class);
    public static void main(String[] src) throws UnsupportedEncodingException {
//        firefox -P "Another User" -no-remote.
//         -no-remote -ProfileManager
        List<String> cmd = new ArrayList<String>();
        cmd.add("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        cmd.add("-no-remote");
        cmd.add("-P");
        cmd.add("aesha2");
//        cmd.add("\"fileName\"");
        ProcessBuilder builder = new ProcessBuilder().command(cmd);
        Process process = null;
        int exitCode=-1;

        try {
            process = builder.start();
            final Process finalProcess = process;
            Thread t=new Thread(new Runnable() {

                public void run() {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finalProcess.destroy();
                }
            });
            t.start();
            exitCode = process.waitFor();
        } catch (InterruptedException e) {
            String ed=new String(e.getMessage());
            _log.debug(ed);
        } catch (IOException e) {
            String ed=new String(e.getMessage());
            _log.debug(ed);
        }
        process.destroy();
        _log.debug(Integer.toString(exitCode));
    }
}
