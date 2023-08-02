package com.mangkyu.stream.Quiz1;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Quiz1 {

    // 1.1 각 취미를 선호하는 인원이 몇 명인지 계산하여라.
    public Map<String, Integer> quiz1() throws IOException {
        /*
        List<String[]> csvLines = readCsvLines();

        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();

        ArrayList<String> strings = new ArrayList<>();

        for (String[] csvLine : csvLines) {
            String[] split = csvLine[1].trim().split(":");
            Arrays.stream(split).forEach(
                e -> strings.add(e)
            );
        }

        List<String> collect = strings.stream().distinct().collect(Collectors.toList());

        collect.stream().forEach(
            string -> stringIntegerHashMap.put(string, (int)strings.stream().filter(e -> e.equals(string)).count())
        );
        */

        List<String[]> csvLines = readCsvLines();

        return csvLines.stream()
                .map(users -> users[1].trim())
                .flatMap(strings1 -> Arrays.stream(strings1.split(":")))
                .collect(Collectors.toMap(string -> string, string -> 1, (oldValue, newValue) -> newValue += oldValue));
    }

    // 1.2 각 취미를 선호하는 정씨 성을 갖는 인원이 몇 명인지 계산하여라.
    public Map<String, Integer> quiz2() throws IOException {

        List<String[]> csvLines = readCsvLines();

        Map<String, Integer> answerMap = csvLines.stream()
                .filter(line -> line[0].startsWith("정"))
                .map(line -> line[1].replaceAll("\\s", ""))
                .flatMap(hobbies -> Arrays.stream(hobbies.split(":")))
                .collect(Collectors.toMap(hobby -> hobby, hobby -> 1, (oldValue, newValue) -> ++newValue));

        return answerMap;
    }

    // 1.3 소개 내용에 '좋아'가 몇번 등장하는지 계산하여라.
    public int quiz3() throws IOException {
        List<String[]> csvLines = readCsvLines();
        csvLines.stream().map(line -> fncountContain(line[2]))
                .reduce(0, Integer::sum);
        return 0;
    }

    private int fncountContain(String string) {
        int index = string.indexOf("좋아", 0);

        return 0;
    }

    private List<String[]> readCsvLines() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(getClass().getResource("/user.csv").getFile()));
        csvReader.readNext();
        return csvReader.readAll();
    }

}
