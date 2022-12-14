package com.line;

import com.line.domain.Hospital;
import com.line.parser.HospitalParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        FileController<Hospital> hospitalFileController = new FileController<>(new HospitalParser());
        String filename = "/Users/jeonghyeonkim/Downloads/hospitalUTF.csv";
        List<Hospital> hospitals = hospitalFileController.readLines(filename);

        System.out.println(hospitals.size());
        List<String> lines = new ArrayList<>();

        for(Hospital hospital : hospitals){
            /*
            System.out.printf("%s,%s,%s,%s,%d,%s,%s\n",
                    hospital.getId(), hospital.getAddress(), hospital.getDistrict(),
                    hospital.getCategory(), hospital.getEmergencyRoom(), hospital.getName(),
                    hospital.getSubdivision());
             */

            //System.out.println(hospital.getSqlInsertQuery());

            lines.add(hospital.getSqlInsertQuery()); //파일에 쿼리문 넣기
        }
        String sqlFilename = "hospital_insert.sql";
        hospitalFileController.createNewFile(sqlFilename);
        hospitalFileController.writeLines(lines, sqlFilename);

    }
}
