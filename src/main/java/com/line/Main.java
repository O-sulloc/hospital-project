package com.line;

import com.line.domain.Hospital;
import com.line.parser.HospitalParser;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        LineReader<Hospital> lr = new LineReader<>(new HospitalParser());
        String filename = "/Users/jeonghyeonkim/Downloads/hospital.csv";
        List<Hospital> hospitals = lr.readLines(filename);

        System.out.println(hospitals.size());
        for(Hospital hospital : hospitals){
            System.out.println(hospital.getId());
        }
    }
}
