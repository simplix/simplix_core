package org.simplix.core.data;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Conversions {
    
	// ...ToList

	public static List<Object> stringToList ( String s ) {
		return new ArrayList<Object>( Arrays.asList ( s ));
	}

	public static List<Object> intToList ( int i ) {
		return stringToList ( String.valueOf ( i ) );
	}
	
	public static List<Object> booleanToList ( boolean b ) {
		return stringToList ( b ? "yes" : "no" );
	}
	
	@SuppressWarnings ( "unchecked" )
	public static List<Object> listElementToList ( Object element ) {
		
		if ( element instanceof String ) {
			return stringToList ( (String) element );
		} else {
			
			return (List<Object>) element;
		}
	}

	// listTo...

    public static List<String> listToStringList ( List<Object> list ) {
		
        final List<String> result = new ArrayList<> ();
		TreeUtils.forEachStringNodeInTree ( list, string -> { result.add ( string ); } );
		return result;
	}

	public static String singleStringListToString ( List<Object> list ) {
		
	    assertSingleElement(list);
	    return (String) list.get ( 0 );
	}

	public static String listToString ( List<Object> list ) {
		
		StringBuffer buffer = new StringBuffer();
		TreeUtils.forEachStringNodeInTree ( list, element -> { buffer.append ( element ); } );
		return buffer.toString();
	}

	public static String listToLimitedString ( List<Object> list ) {
		return listToLimitedString ( list, 80 );
	}

	public static String listToLimitedString ( List<Object> list, int limit ) {
		
		String r = listToString ( list );
		if ( r.length() > limit ) {
			r = r.substring ( 0, limit ) + " ..."; 
		}
		return r;
	}

	public static int listToInt ( List<Object> list ) throws NumberFormatException {

	    assertSingleElement(list);
        return Integer.parseInt ( (String) list.get(0) );
        /*
		try {
		} catch ( Exception e ) {
			// TODO
		} */
	}

	public static boolean listToBoolean ( List<Object> list ) {
		
	    assertSingleElement(list);
	    String value = (String) list.get ( 0 );
	    if ( value.equalsIgnoreCase ( "yes" ) ) {
            return true;
        } else if ( value.equalsIgnoreCase ( "no" ) ) {
	        return false;
        } else {
	        throw new RuntimeException ( value + " is not a valid yes/no value." );
        }
	}

	public static File listToFile ( List<Object> list ) {
	    
		String path = listToString ( list );
		return new File ( path );
	}

	public static Path listToPath ( List<Object> list ) {
		
		return listToFile ( list ).toPath();
	}

	public static BigDecimal listToBigDecimal ( List<Object> list ) {

		String value = singleStringListToString ( list );
		return new BigDecimal ( value );
	}
	
	private static void assertSingleElement ( List<Object> list ) {
		
		if ( list.size() != 1 ) {
			throw new RuntimeException ( "List is supposed to contain one element, but contains " + list.size() + " elements." );
		}
	}

}
