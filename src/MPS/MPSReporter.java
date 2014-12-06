package MPS;

import com.sun.javafx.webkit.KeyCodeMap;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Wartung.Monitor.*;
import static Utilities.Constants.SEP;
/**
 * Created by Swaneet on 04.12.2014.
 */
public class MPSReporter {
    // reports the current status to the monitor.
    DataOutputStream writer;
    Map<String, Integer> usages = new HashMap();
    public MPSReporter(int num, int port) throws IOException, InterruptedException {
        Socket s = new Socket("localhost", port);
        writer = new DataOutputStream(s.getOutputStream());
        while(true) {
            send(ALIVE + SEP + usagesToString());   // sends string of the form "ALIVE;Usages;erstelleAuftrag,15;bla,16"
            Thread.sleep(ALIVE_INTERVAL_MILLIS);
        }
    }

    private String usagesToString() {
        String s = "Usages";
        for (Map.Entry<String, Integer> e:usages.entrySet()) {
            s += SEP + e.getKey() + "," + e.getValue();
        }
        return s;
    }

    private void send(String s) throws IOException {
        writer.writeBytes(s + "\n");
    }

    synchronized public void countUsage(String s) {
        if (usages.containsKey(s)) {
            usages.put(s, usages.get(s) + 1);
        }
        else {
            usages.put(s, 1);
        }
    }
}
