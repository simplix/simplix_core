package org.simplix.core.data;

import java.util.ArrayList;
import java.util.List;

public class se_tree {
	
	public static List<Object> flatten ( List<Object> tree ) {
		
		List<Object> result = new ArrayList<> ();
		TreeUtils.forEachStringNodeInTree ( tree, stringNode -> { result.add ( stringNode ); } );
		return result;
	}

	public static List<Object> is_equal ( List<Object> tree_1, List<Object> tree_2 ) {
	    return Conversions.booleanToList ( TreeUtils.isEqual ( tree_1, tree_2 ) );
	}

	public static List<Object> to_string ( List<Object> tree ) {

		StringBuffer buffer = new StringBuffer();
		TreeUtils.forEachStringNodeInTree ( tree, stringNode -> { buffer.append ( stringNode ); } );
		return Conversions.stringToList ( buffer.toString() );
	}


	public static List<Object> to_string_lines ( List<Object> tree ) {
		
		return to_string_with_separator ( tree, se_string.current_OS_new_line() );
	}

	public static List<Object> to_string_with_separator ( List<Object> tree, List<Object> separator ) {

		StringBuffer buffer = new StringBuffer();
		String sep = Conversions.listToString ( separator );
		final Boolean[] is_first = { true };
		TreeUtils.forEachStringNodeInTree ( tree, stringNode -> {
			if ( ! is_first[0] ) {
				buffer.append ( sep );
			}
			buffer.append ( stringNode );
			is_first[0] = false;
		} );
		return Conversions.stringToList ( buffer.toString() );
	}

	public static List<Object> to_string_tree ( List<Object> tree ) {

		StringBuffer buffer = new StringBuffer();

        TreeUtils.visitTreeNodes (
            tree,
            root -> {},
            root -> {},
            ( branch, index, is_last, level ) -> {},
            ( branch, index, is_last, level ) -> {},
            ( string, index, is_last, level ) -> {
				for ( int i = 1; i <= level; i++ ) {
					buffer.append ( "    " );
				}
            	buffer.append ( string );
            	buffer.append ( StringUtils.currentOSNewLine );
            } );
		return Conversions.stringToList ( buffer.toString() );
	}

	// TODO
	// public static List<Object> to_string_map ( List<Object> tree ) {
	
    public static List<Object> to_canonical_string ( List<Object> tree ) {

		StringBuffer buffer = new StringBuffer();

        TreeUtils.visitTreeNodes (
            tree,
            root -> { buffer.append ( "[ " ); },
            root -> { buffer.append ( " ]" ); },
            ( branch, index, is_last, level ) -> { buffer.append ( "[ " ); },
            ( branch, index, is_last, level ) -> {
            	buffer.append ( " ]" );
				if ( ! is_last ) {
					buffer.append ( ", " );
				}
			},
            ( string, index, is_last, level ) -> {
            	buffer.append ( '"' );
				// TODO escape "
            	buffer.append ( string );
            	buffer.append ( '"' );
				if ( ! is_last ) {
					buffer.append ( ", " );
				}
            } );
		return Conversions.stringToList ( buffer.toString() );
	}
	
    public static List<Object> to_HTML ( List<Object> tree ) {
        
		StringBuffer buffer = new StringBuffer();

        TreeUtils.visitTreeNodes (
            tree,
            list -> {
                buffer.append ( "<ul>" );
                appendNewLine ( buffer );
            },
            list -> {
                buffer.append ( "</ul>" );
            },
            ( list, index, is_last, level ) -> {
            	appendLevelIndent ( buffer, level );
            	buffer.append ( "<ul>" );
                appendNewLine ( buffer );
            },
            ( list, index, is_last, level ) -> {
            	appendLevelIndent ( buffer, level );
            	buffer.append ( "</ul>" );
                appendNewLine ( buffer );
            },
            ( string, index, is_last, level ) -> {
            	appendLevelIndent ( buffer, level );
            	buffer.append ( "<li>" );
                // TODO HTML escape
            	buffer.append ( string );
            	buffer.append ( "</li>" );
                appendNewLine ( buffer );
            } );
        return Conversions.stringToList ( buffer.toString() );
    }
 
    public static List<Object> to_XML ( List<Object> tree ) {
        
		StringBuffer buffer = new StringBuffer();

        TreeUtils.visitTreeNodes (
            tree,
            list -> {
                // buffer.append ( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" );
				// appendNewLine ( buffer );
                buffer.append ( "<data>" );
                appendNewLine ( buffer );
            },
            list -> {
                buffer.append ( "</data>" );
            },
            ( list, index, is_last, level ) -> {
                appendLevelIndent ( buffer, level );
                buffer.append ( "<list>" );
                appendNewLine ( buffer );
            },
            ( list, index, is_last, level ) -> {
                appendLevelIndent ( buffer, level );
                buffer.append ( "</list>" );
                appendNewLine ( buffer );
            },
            ( string, index, is_last, level ) -> {
                appendLevelIndent ( buffer, level );
                buffer.append ( "<el>" );
                // TODO XML escape
                buffer.append ( string );
                buffer.append ( "</el>" );
            } );
        return Conversions.stringToList ( buffer.toString() );
    }

    public static List<Object> to_JSON ( List<Object> tree ) {
        
		StringBuffer buffer = new StringBuffer();

        TreeUtils.visitTreeNodes (
            tree,
            list -> {
                buffer.append ( "{\"data\" : [" );
                appendNewLine ( buffer );
            },
            list -> {
                buffer.append ( "]}" );
            },
            ( list, index, is_last, level ) -> {
                appendLevelIndent ( buffer, level );
                buffer.append ( "[" );
                appendNewLine ( buffer );
            },
            ( list, index, is_last, level ) -> {
                appendLevelIndent ( buffer, level );
                buffer.append ( "]" );
                if ( ! is_last ) {
                    buffer.append ( ", " );
                }
                appendNewLine ( buffer );
            },
            ( string, index, is_last, level ) -> {
                appendLevelIndent ( buffer, level );
                buffer.append ( "\"" );
                // TODO JSON escape
                buffer.append ( string );
                buffer.append ( "\"" );
                if ( ! is_last ) {
                    buffer.append ( ", " );
                }
                appendNewLine ( buffer );
            } );
		return Conversions.stringToList ( buffer.toString() );
    }
    
    private static void appendLevelIndent ( StringBuffer buffer, int level ) {
        
        for ( int i = 0; i <= level; i++ ) {
            buffer.append ( "    " );
        }
    }
    
    private static void appendNewLine ( StringBuffer buffer ) {
		buffer.append ( StringUtils.currentOSNewLine );
	}

}
