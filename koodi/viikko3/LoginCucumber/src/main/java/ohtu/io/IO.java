
package ohtu.io;

import org.springframework.stereotype.Component;

@Component
public interface IO {
    void print(String toPrint);
    int readInt(String prompt);
    String readLine(String prompt);
}
