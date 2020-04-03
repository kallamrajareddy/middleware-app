package com.kallam.middleware.helper;

import java.time.LocalDateTime;
import java.time.Month;

public class MongoLocalDateTime  implements Comparable<MongoLocalDateTime> {

    private LocalDateTime localDateTime;

    private MongoLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public static MongoLocalDateTime of(LocalDateTime localDateTime) {
        return new MongoLocalDateTime(localDateTime);
    }

    public static MongoLocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        return new MongoLocalDateTime(LocalDateTime.of(year, month, dayOfMonth, hour, minute, second));
    }

    public int getYear() {
        return this.localDateTime.getYear();
    }

    public Month getMonth() {
        return this.localDateTime.getMonth();
    }

    public int getDayOfMonth() {
        return this.localDateTime.getDayOfMonth();
    }

    public int getHour() {
        return this.localDateTime.getHour();
    }

    public int getMinute() {
        return this.localDateTime.getMinute();
    }

    public int getSecond() {
        return this.localDateTime.getSecond();
    }

    public LocalDateTime toLocalDateTime() {
        return this.localDateTime;
    }

    public MongoLocalDateTime plusDays(long days) {
        return new MongoLocalDateTime(this.localDateTime.plusDays(days));
    }

    @Override
    public int compareTo(MongoLocalDateTime other) {
        return this.localDateTime.compareTo(other.localDateTime);
    }

    public String toString() {
        return  this.localDateTime.toString();
    }


}
