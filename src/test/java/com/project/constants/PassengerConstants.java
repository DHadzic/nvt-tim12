package com.project.constants;

import java.util.GregorianCalendar;

import org.apache.tomcat.util.codec.binary.Base64;

import com.project.domain.PassengerType;

public class PassengerConstants {
	public static final String NEW_TAKEN_USERNAME = "taken_username";
	
    public static final String NEW_USERNAME1 = "Djole123";
    public static final String NEW_USERNAME2 = "Djole_123";
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
    
    public static final String VERIFY_PASSENGER = "pName";
    public static final String VERIFY_IMAGE = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADh"
    		+ "CAMAAAAJbSJIAAAAYFBMVEUlroj///9DuZm95tr3/Pu55NgZq4Ty+vg8tpVfxKjq9/Tk9PAnr4owso49tpbW8On"
    		+ "E6N7g8+6u4NJWvqBJu5x4zbSl3c2X18WO1MB+zrdvya+W18TR7uWh2spjxKgAqoFYBRDgAAALv0lEQVR4nOWd6Z"
    		+ "ajIBCFXUtcaJWoUTvL+7/laMwCxgUEDOm5P/tkEr4BKWqhtGzdijJ3UOa3DsIJRk7rP/8Waf99S9cXe56X+p1IU"
    		+ "zh3IbAGAXr8qWhI/6G0+7SugWgh9IIgJuTcop+fH4QfXFMCfPtMeyYkDgItlOoJgyhyz+fGQShZYmM4E4Sc5nx2"
    		+ "oyhQPh7FhEF0+q2bA8aLMzc9mxgfmvr3pBpSJaEXp8fmgnEiCPdSgvGlOaaxyuWqjjBOqzbE1na8O6SFw7ZKY2X"
    		+ "jUkQYn0hYYBBdmtMCwEVIToogVR1q7MpiNBmHV4uvlujB1k9gHC2A31rc6xINw+jRsJY79F+wH23kjrb2TcROil"
    		+ "jbMj3iCnSTdtqxsIvbwq8e6AloXLKt/AKE4YVSXaY4N5V4LKStxyCBNGR50GcFmdeTwKIwoSeulx1x3mjREdRZ9"
    		+ "GMcKgCj/xBNLCYSXmIgsRBp++SkL/YM4KfYoTkYuQGPQIvgQFUUXoGwnYI/LM4jqhZ+QSvQkOHNvNKqFn4CbzEg"
    		+ "fiGmFnJj5Nsah1o7FCaPYM9lqdxRVC4wHXjcYyoZlmgtWa0VgkNNVMsFoxGguEBpsJVstGY57wawBXEOcJM7PNB"
    		+ "KtwPuw/SxjUZrlLy8L1rEs8R+iR4tOjFlJB5tbpHGH2FdvoS1DMrdMZwu9ao71m1+k0oRFhQ0HNBRknCb3q+wAtQNXkozhJmO6Y/FQnCFNewuj4";
    
}
