package com.line.parser;

import com.line.domain.Hospital;

public class HospitalParser implements Parser<Hospital> {
    private String getSubdivision(String name) {
        String[] subKinds = {"소아과", "피부", "성형", "정형외과", "산부인과", "관절", "안과", "가정의학과", "비뇨기과", "치과", "내과", "외과"};

        for (String subdivision : subKinds) {
            if (name.contains(subdivision)) {
                //If 병원 이름에 내과외과소와과... 하나라도 들어있으면 리턴해
                //ex. 기대찬내과의원에 '내과'가 있네. 그럼 '내과' 리턴해

                return subdivision;
            }
        }

        return ""; //subKinds에 해당하는 이름이 없으면 (ex.기대찬이빈후과) 그러면 그냥 ""리턴해
    }

    @Override
    public Hospital parse(String str) {
        str = str.replaceAll("\"", "");
        String[] splitted = str.split(",");

        String name = splitted[10]; //병원 이름

        String subdivision = getSubdivision(name); //위에 있는 메서드를 출력해서, 병원 이름(name)에서 파싱해서 가져옴.
        return new Hospital(splitted[0], splitted[1], splitted[2], Integer.parseInt(splitted[6]), name, subdivision);

        /*
        [0] -> address //~~가산동
        [1] -> district //서울특별시 금천구
        [2] -> category //C
        [6] -> emergency room //2
        [10] -> name //가산기대찬의원
        subdivision (name에서 파싱해온 후 넣는다.)
         */
    }
}
