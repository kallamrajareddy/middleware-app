package com.kallam.middleware;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

import com.kallam.middleware.helper.MongoLocalDateTime;

public class MongoDateUtil {

	public static MongoLocalDateTime toLocal(Date dateToConvert) {
		return MongoLocalDateTime.of(Instant.ofEpochMilli(dateToConvert.getTime())
		.atZone(ZoneId.systemDefault())
		.toLocalDateTime());
		}
}
