package com.foursixteen.kuma.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class IOUtil {

    public static OutputStream exec(File directory, String command, String... args) throws InterruptedException, IOException {
        Process p = null;
        Scanner scanner = null;
        try {
            p = Runtime.getRuntime().exec("curl", args, directory);
            scanner = new Scanner(p.getInputStream());
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
            p.waitFor();
        } finally {
            if (p != null) {
                scanner.close();
                p.getOutputStream().close();
                p.getInputStream().close();
                p.getErrorStream().close();
                p.destroy();
            }
        }
        return p.getOutputStream();
    }
}
