package org.simplix.core.data;

import java.math.BigDecimal;
import java.util.List;

public class CheckUtils {

    public static boolean isYesNo ( List<Object> data ) {
        
        String singleString = getSingleStringOrNull ( data );
        if  ( singleString == null ) return false;

	    if ( singleString.equalsIgnoreCase ( "yes" ) ) {
            return true;
        } else if ( singleString.equalsIgnoreCase ( "no" ) ) {
	        return true;
        } else {
	        return false;
        }
    }

    public static boolean isNumber ( List<Object> data ) {
        
        String singleString = getSingleStringOrNull ( data );
        if  ( singleString == null ) return false;
        
        try {
            new BigDecimal ( singleString );
            return true;
        } catch ( NumberFormatException e ) {
            return false;
        }
    }

    private static String getSingleStringOrNull ( List<Object> data ) {
        
        if ( ! isSingleton ( data ) ) return null;
        
        Object element = data.get ( 0 );
        if ( element instanceof String ) {
            return (String) element;
        } else {
            return null;
        }
    }

    private static boolean isSingleton ( List<Object> data ) {
        
        return data.size() == 1;
    }
}
