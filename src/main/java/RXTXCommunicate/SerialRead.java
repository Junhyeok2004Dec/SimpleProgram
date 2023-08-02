package RXTXCommunicate;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SerialRead implements Runnable{

    InputStream inputStream;

    public SerialRead(InputStream instream) {
        this.inputStream = instream;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        int len = -1;
        try{
            while ((len = this.inputStream.read(buffer)) > -1) {
                System.out.println(new String(buffer, 0, len));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
