package me.jeong.movieinfo.utils;

import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DateUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final String NOW = LocalDate.now().format(formatter);

    /**
     * 기준 날짜에서 특정 년, 월, 일을 더하거나 빼는 메서드
     * 연산 결과가 최소 또는 최대 범위를 벗어나면,
     * 가장 작은 날짜(1900-01-01) 또는 가장 큰 날짜(9999-12-31)를 반환합니다.
     *
     * @param dateStr 기준 날짜 (yyyy-MM-dd 형식의 문자열)
     * @param year    변경할 년 수 (양수면 더하기, 음수면 빼기)
     * @param month   변경할 개월 수 (양수면 더하기, 음수면 빼기)
     * @param day     변경할 일 수 (양수면 더하기, 음수면 빼기)
     * @return 계산된 날짜 (yyyy-MM-dd 형식의 문자열)
     */
    public static String calculateDate(String dateStr, long year, long month, long day) {
        LocalDate date = LocalDate.parse(dateStr, formatter);
        try {
            // 입력 날짜를 LocalDate로 파싱 후 연, 월, 일 연산 수행
            date = date.plusYears(year);
            date = date.plusMonths(month);
            date = date.plusDays(day);
            return date.format(formatter);
        } catch (DateTimeException e) {
            // 연산 중 유효 범위(-999,999,999 ~ 999,999,999)를 벗어난 경우 처리
            // 전체적으로 더하는 경우에는 최대 날짜, 빼는 경우에는 최소 날짜를 반환하도록 함
            if (date.getYear() > 0) {
                return "9999-12-31";
            } else {
                return "1970-01-01";
            }
        }
    }

    /**
     * 현재 날짜에서 특정 년, 월, 일을 더하거나 빼는 메서드
     * 연산 결과가 최소 또는 최대 범위를 벗어나면,
     * 가장 작은 날짜(1900-01-01) 또는 가장 큰 날짜(9999-12-31)를 반환합니다.
     *
     * @param year  변경할 년 수 (양수면 더하기, 음수면 빼기)
     * @param month 변경할 개월 수 (양수면 더하기, 음수면 빼기)
     * @param day   변경할 일 수 (양수면 더하기, 음수면 빼기)
     * @return 계산된 날짜 (yyyy-MM-dd 형식의 문자열)
     */
    public static String calculateDateFromToday(long year, long month, long day) {
        String currentDate = LocalDate.now().format(formatter);
        return calculateDate(currentDate, year, month, day);
    }

    public static String[][] splitMonthsIntoPeriods(int totalMonths, int parts) {
        int monthsPerPart = totalMonths / parts;
        String[][] periods = new String[monthsPerPart][parts];
        for (int n = 0; n < monthsPerPart; n++) {
            for (int m = 0; m < parts; m++) {
                periods[n][m] = calculateDateFromToday(0, (n + m) * 2, 0);
            }
        }
        return periods;
    }

    public static String convertToString(LocalDate date) {
        return date.format(formatter);
    }
}
