package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class CSVReader implements Iterable<String[]> {

    private final Scanner scan;
    private final String[] headers;

    public CSVReader(File file) throws FileNotFoundException {
        this.scan = new Scanner(file);
        this.headers = processLine(scan.nextLine());
    }

    private String[] processLine(String line){
        String[] results = line.split(",");
        for (int i = 0; i < results.length; i++) {
            results[i] = results[i].trim();
        }
        return results;
    }

    public String[] headers() throws FileNotFoundException {
        return headers;
    }

    public String[] nextLine(){
        return processLine(scan.nextLine());
    }

    @Override
    public Iterator<String[]> iterator() {
        return new FileIterator();
    }

    private class FileIterator implements Iterator<String[]> {

        @Override
        public boolean hasNext() {
            return scan.hasNext();
        }

        @Override
        public String[] next() {
            return nextLine();
        }
    }
}
