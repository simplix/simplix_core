package org.simplix.core.data;

import java.util.Collections;
import java.util.List;

public class se_list {
	
    /* TD write se_in SPL
	function is_empty ( se_list ) -> yes_no
		java
			return Conversions.booleanToList ( se_list.isEmpty() );
		end java
	.
	*/
    
    // size

	public static List<Object> is_empty ( List<Object> list ) {
		return Conversions.booleanToList ( list.isEmpty() );
	}
	
	public static List<Object> size ( List<Object> list ) {
		return Conversions.intToList ( list.size() );
	}
	
	// getters
	
	public static List<Object> get ( List<Object> list, List<Object> index ) {
        
        return getByIndex ( list, Conversions.listToInt ( index ) );
	}
	
	public static List<Object> first ( List<Object> list ) {
        
        return getByIndex ( list, 1 );
	}
	
	public static List<Object> last ( List<Object> list ) {
        
        return getByIndex ( list, list.size() );
	}
	
	private static List<Object> getByIndex ( List<Object> list, int index ) {
        
        return Conversions.listElementToList ( list.get ( index - 1 ) );
	}
	
	// add
	
    @SuppressWarnings ( "unchecked" )
	public static void append ( List<Object> list, List<Object> other ) {

		Collections.addAll ( other );
	}
	
    @SuppressWarnings ( "unchecked" )
	public static void insert_before ( List<Object> list, List<Object> index, List<Object> other ) {
        
        int list_index = Conversions.listToInt ( index );
		for ( int other_index = other.size() - 1; other_index > 0; other_index-- ) {
			list.add ( list_index - 1, other.get ( other_index ) );
		}
	}
	
	// remove
	
    @SuppressWarnings ( "unchecked" )
	public static void remove_at_index ( List<Object> list, List<Object> index ) {
        
        int i = Conversions.listToInt ( index );
        list.remove ( i - 1 );
	}
	
	public static void remove_first ( List<Object> list ) {
        
        list.remove(0);
	}
	
	public static void remove_last ( List<Object> list ) {
        
        list.remove(list.size() - 1 );
	}
	
	public static void remove_all ( List<Object> list ) {
        
        list.clear();
	}
	
	// replace
	
	/* TODO must iterate
	public static void replace_at_index ( List<Object> se_list, List<Object> index, List<Object> element ) {
        
        int i = Conversions.listToInt ( index );
        se_list.set ( i - 1, element );
	}
	
	public static void replace_first ( List<Object> se_list, List<Object> element ) {
        
        se_list.set ( 0, element );
	}
	
	public static void replace_last ( List<Object> se_list, List<Object> element ) {
        
        se_list.set ( se_list.size() - 1, element );
	}
	*/
	
	/* TODO
	public static List<Object> equals ( List<Object> se_list, List<Object> other )
	
	public static void append_all ( List<Object> se_list, List<Object> to_append )

	public static void replace_all ( List<Object> se_list, List<Object> element )

	public static List<Object> contains ( List<Object> se_list, List<Object> element )

	public static List<Object> sub_list_from_to ( List<Object> se_list, List<Object> from, List<Object> to )

	public static void sort ( List<Object> se_list )
	public static void sort_ignore_case ( List<Object> se_list )
	public static void sort_descending ( List<Object> se_list )
	public static void sort_descending_ignore_case ( List<Object> se_list )

	*/

}
