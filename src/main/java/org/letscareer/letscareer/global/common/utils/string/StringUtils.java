package org.letscareer.letscareer.global.common.utils.string;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StringUtils {

    public static String listToString(List<Long> longList) {
        return longList.stream().map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    public static List<Long> stringToList(String strList) {
        String[] convertedArray = strList.split(",");
        List<Long> convertedList = new ArrayList<>();

        for (String strNum : convertedArray) {
            convertedList.add(Long.parseLong(strNum));
        }

        return convertedList;
    }

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm");

    public static final DateTimeFormatter DATE_TIME_FORMATTER_MM_dd_E_a_HH_mm = DateTimeFormatter.ofPattern("MM월 dd일(E) a HH시 mm분");

    public static final DateTimeFormatter DATE_TIME_FORMATTER_MM_dd = DateTimeFormatter.ofPattern("MM월 dd일");

    public static String dateToString(LocalDateTime date) {
        return date.format(DATE_TIME_FORMATTER);
    }

    public static String dateToStringMMddEaHHmm(LocalDateTime date) {
        return date.format(DATE_TIME_FORMATTER_MM_dd_E_a_HH_mm);
    }

    public static String dateToStringMMdd(LocalDateTime date) {
        return date.format(DATE_TIME_FORMATTER_MM_dd);
    }

    public static String toStringWithThousandsSeparator(Integer num) {
        return String.format("%,d", num);
    }

    public static String withThousandsSeparator(String str) {
        return str.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
    }
}
