package com.project.constants;

import java.util.GregorianCalendar;

import com.project.domain.PassengerType;

public class PassengerConstants {
	public static final String NEW_TAKEN_USERNAME = "taken_username";
	
    public static final String NEW_USERNAME = "Djole123";
    public static final String NEW_USERNAME_FOR_INVALID = "Djole1234";
    public static final String NEW_PASSWORD = "sifra";
    public static final String NEW_NAME = "Djordje";
    public static final String NEW_SURNAME = "Djordjevic";
    public static final GregorianCalendar NEW_BIRTHDATE = new GregorianCalendar(1995,5,5);
    public static final PassengerType NEW_TYPE = PassengerType.REGULAR;

    public static final String NEW_USERNAME_SHORT = "Dj";
    public static final String NEW_USERNAME_LONG = "Djole1234567890123456";
    public static final String NEW_PASSWORD_SHORT = "si";
    public static final String NEW_PASSWORD_LONG = new String(new char[101]).replace("\0", "-");
    public static final String NEW_NAME_SHORT = "Dj";
    public static final String NEW_NAME_LONG = "Djordje123456789012345";
    public static final String NEW_SURNAME_SHORT = "Dj";
    public static final String NEW_SURNAME_LONG = "Djordjevic123456789012345";
    public static final GregorianCalendar NEW_BIRTHDATE_BEFORE = new GregorianCalendar(1700,5,5);
    public static final GregorianCalendar NEW_BIRTHDATE_AFTER = new GregorianCalendar(2015,5,5);
    public static final int DB_SIZE = 1;
    
    public static final String SUCC_LOGIN_USERNAME = "taken_username";
    public static final String SUCC_LOGIN_PASSWORD = "user";
    public static final String BAD_LOGIN_PASSWORD = "user2";
}
