package com.kallam.middleware;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.kallam.middleware.helper.MongoLocalDateTime;

public class MongoDateUtil {

	public static MongoLocalDateTime toLocal(Date dateToConvert) {
		LocalDate time = LocalDate.of(dateToConvert.getYear(), dateToConvert.getMonth(), dateToConvert.getDay());
		return MongoLocalDateTime.of(time.atStartOfDay());
		/*return MongoLocalDateTime.of(Instant.ofEpochMilli(dateToConvert.getTime())
		.atZone(ZoneId.systemDefault())
		.toLocalDateTime());*/
		}
}
