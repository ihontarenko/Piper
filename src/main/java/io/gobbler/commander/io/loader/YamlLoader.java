package io.gobbler.commander.io.loader;

import io.gobbler.commander.io.Loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class YamlLoader implements Loader<String, InputStream> {

    @Override
    public InputStream load(String source) throws FileNotFoundException {
        return new FileInputStream(new File(source));
    }

}
