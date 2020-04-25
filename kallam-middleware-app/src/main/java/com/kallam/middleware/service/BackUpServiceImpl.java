package com.kallam.middleware.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

@Service
public class BackUpServiceImpl implements BackUpService {

	@Override
	public Boolean resoreData() {
		try {
			//mongorestore --host KALLAMREDDY-shard-0/kallamreddy-shard-00-00-lmoxi.mongodb.net:27017,kallamreddy-shard-00-01-lmoxi.mongodb.net:27017,kallamreddy-shard-00-02-lmoxi.mongodb.net:27017 --ssl --username KallamRajaReddy --password Kallam02Lakshmi --authenticationDatabase admin  --drop  --noIndexRestore --gzip --archive=harshafinance.gz
			ProcessBuilder builderCloud = new ProcessBuilder(
		            "cmd.exe", "/c", "cd \"C:\\Rajendra-Personal\\DUMP\" && mongodump --host KALLAMREDDY-shard-0/kallamreddy-shard-00-00-lmoxi.mongodb.net:27017,kallamreddy-shard-00-01-lmoxi.mongodb.net:27017,kallamreddy-shard-00-02-lmoxi.mongodb.net:27017 --ssl --username KallamRajaReddy --password Kallam02Lakshmi --authenticationDatabase admin  --gzip --archive=harshafinance.gz");
	        builderCloud.redirectErrorStream(true);
		        Process pCloud = builderCloud.start();
		        BufferedReader rCloud = new BufferedReader(new InputStreamReader(pCloud.getInputStream()));
		        String lineCloud;
		        while (true) {
		        	lineCloud = rCloud.readLine();
		            if (lineCloud == null) { break; }
		            System.out.println(lineCloud);
		        }
			ProcessBuilder builder = new ProcessBuilder(
		            "cmd.exe", "/c", "cd \"C:\\Rajendra-Personal\\DUMP\" && mongorestore --drop  --noIndexRestore --gzip --archive=harshafinance.gz");
		        builder.redirectErrorStream(true);
		        Process p = builder.start();
		        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		        String line;
		        while (true) {
		            line = r.readLine();
		            if (line == null) { break; }
		            System.out.println(line);
		        }
		        
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
	}

	@Override
	public Boolean backUpData() {
		try {
			//mongodump --host KALLAMREDDY-shard-0/kallamreddy-shard-00-00-lmoxi.mongodb.net:27017,kallamreddy-shard-00-01-lmoxi.mongodb.net:27017,kallamreddy-shard-00-02-lmoxi.mongodb.net:27017 --ssl --username KallamRajaReddy --password Kallam02Lakshmi --authenticationDatabase admin  --gzip --archive=harshafinance.gz
		ProcessBuilder builder = new ProcessBuilder(
	            "cmd.exe", "/c", "cd \"C:\\Rajendra-Personal\\DUMP\" && mongodump --gzip --archive=harshafinance.gz");
	        builder.redirectErrorStream(true);
	        Process p = builder.start();
	        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line;
	        while (true) {
	            line = r.readLine();
	            if (line == null) { break; }
	            System.out.println(line);
	        }
	      //mongorestore --host KALLAMREDDY-shard-0/kallamreddy-shard-00-00-lmoxi.mongodb.net:27017,kallamreddy-shard-00-01-lmoxi.mongodb.net:27017,kallamreddy-shard-00-02-lmoxi.mongodb.net:27017 --ssl --username KallamRajaReddy --password Kallam02Lakshmi --authenticationDatabase admin  --drop  --noIndexRestore --gzip --archive=harshafinance.gz
			ProcessBuilder builderCloud = new ProcessBuilder(
		            "cmd.exe", "/c", "cd \"C:\\Rajendra-Personal\\DUMP\" && mongorestore --host KALLAMREDDY-shard-0/kallamreddy-shard-00-00-lmoxi.mongodb.net:27017,kallamreddy-shard-00-01-lmoxi.mongodb.net:27017,kallamreddy-shard-00-02-lmoxi.mongodb.net:27017 --ssl --username KallamRajaReddy --password Kallam02Lakshmi --authenticationDatabase admin  --drop  --noIndexRestore --gzip --archive=harshafinance.gz");
	        builderCloud.redirectErrorStream(true);
		        Process pCloud = builderCloud.start();
		        BufferedReader rCloud = new BufferedReader(new InputStreamReader(pCloud.getInputStream()));
		        String lineCloud;
		        while (true) {
		        	lineCloud = rCloud.readLine();
		            if (lineCloud == null) { break; }
		            System.out.println(lineCloud);
		        }
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
