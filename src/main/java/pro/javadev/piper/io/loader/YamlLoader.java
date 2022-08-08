package pro.javadev.piper.io.loader;

import pro.javadev.piper.common.Loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class YamlLoader implements Loader<String, InputStream> {

    @Override
    public InputStream load(String source) throws FileNotFoundException {
        return new FileInputStream(source);
    }

}
