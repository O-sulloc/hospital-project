package com.line;

import com.line.parser.Parser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileController<T> {
    Parser<T> parser;

    boolean isRemoveColumnName = true; //행제목 날릴지 안 날릴지

    public FileController(Parser<T> parser) {
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

        while ((str = br.readLine()) != null) {
            System.out.println(str);
            result.add(parser.parse(str));
        }
        return result;
    }

    public void createNewFile(String filename) throws IOException {
        File file = new File(filename);
        file.createNewFile();

        System.out.println("파일 만들어 짐?" + file.exists());
    }

    public void writeLines(List<String> lines, String filename) throws IOException {
        File file = new File(filename);
        FileWriter fw = new FileWriter(file);
        for(int i=0;i<lines.size();i++){
            fw.write(lines.get(i) + "\n");
        }
        fw.flush();
        fw.close();
        System.out.println("success");
    }

}
