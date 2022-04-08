package com.github.majidshoorabi.saftonfun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author majid.shoorabi
 * @created 2022-07-April
 * @project saftonfun
 */

public class WordsLoader {


    public List<String> readFile() throws IOException {
        try (
                InputStream inputStream = this.getClass().getResourceAsStream("/words.txt");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                Stream<String>  lines = bufferedReader.lines();
        ) {
            List<String> tmpList = new LinkedList<>();
            List<String> words = new LinkedList<>();
            lines.forEach(tmpList::add);

            tmpList.forEach(str -> words.add(str.replaceAll(" ", "")));

            return words;
        }
    }
}
