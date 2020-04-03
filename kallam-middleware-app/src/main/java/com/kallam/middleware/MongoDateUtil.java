package com.kallam.middleware;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class MongoDateUtil {

	public static LocalDateTime toLocal(Date dateToConvert) {
		return Instant.ofEpochMilli(dateToConvert.getTime())
		.atZone(ZoneId.systemDefault())
		.toLocalDateTime();
		}
}
