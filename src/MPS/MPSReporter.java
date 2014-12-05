package MPS;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;

/**
 * Created by Swaneet on 04.12.2014.
 */
public class MPSReporter {
    DataOutputStream writer;
    public MPSReporter(int num, int port) throws IOException {
        Socket s = new Socket("localhost", port);
        writer = new DataOutputStream(s.getOutputStream());


    }
}
