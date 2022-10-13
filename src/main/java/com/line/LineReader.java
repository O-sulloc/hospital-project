package com.line;

import com.line.parser.Parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LineReader<T> {
    Parser<T> parser;

    boolean isRemoveColumnName = true; //행제목 날릴지 안 날릴지

    public LineReader(Parser<T> parser) {
        this.parser = parser;
    }

    List<T> readLines(String filename) throws IOException {
        List<T> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        br = Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8); //한글 인코딩
        String str;

        if (isRemoveColumnName) {
            br.readLine();
        }

        while((str = br.readLine()) != null){
            System.out.println(str);
            result.add(parser.parse(str));
        }
        return result;
    }


}
