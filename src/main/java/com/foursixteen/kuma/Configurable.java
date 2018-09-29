package com.foursixteen.kuma;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Configurable {

    protected Properties properties;
    protected FileInputStream fis;
    protected FileOutputStream fos;

    public Configurable(String path) {
        try {
            properties = new Properties();

            fis = new FileInputStream(path);
            fos = new FileOutputStream(path);

            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
