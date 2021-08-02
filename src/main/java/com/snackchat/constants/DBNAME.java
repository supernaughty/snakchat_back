package com.snackchat.constants;

import lombok.Getter;

@Getter
public enum DBNAME {
	USER("∞Ë¡§", "user");

	final private String krname;
	final private String dbname;

	DBNAME(String krname, String dbname) {
		this.krname = krname;
		this.dbname = dbname;
	}
}
