package org.simplix.core.data;

import org.simplix.core.files.se_file_reader;
import org.simplix.core.files.se_file_writer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class se_storage {
    
    private static class MyScanner {
        
        String a_s;
        int a_pos;
        
        MyScanner ( String s ) {
            this.a_s = s;
            a_pos = 0;
        }
        
        boolean hasMore() {
            return a_pos < a_s.length();
        }
        
        String remaining() {
            return a_s.substring ( a_pos );
        }

        String consumeRegex ( Pattern pattern ) {
            
            Matcher matcher = pattern.matcher ( a_s );
            matcher.region ( a_pos, a_s.length() );

            if ( matcher.lookingAt() ) {
                String result = matcher.toMatchResult().group();
                a_pos = a_pos + result.length ();
                return result;
            } else {
                return null;
            }
        }
        
        boolean consumeString ( String string ) {
            
            if ( isAtString ( string ) ) {
                a_pos = a_pos + string.length ();
                return true;
            } else {
                return false;
            }
        }
        
        boolean isAtString ( String string ) {
            return a_s.startsWith ( string, a_pos );
        }
    }
    
    public static void store_to_file ( List<Object> data, List<Object> file_path ) {
        se_file_writer.write ( file_path, se_tree.to_canonical_string ( data ) );
    }

    public static List<Object> restore_from_file ( List<Object> file_path ) {
        return parse ( se_file_reader.read ( file_path ) );
    }
    
    public static List<Object> parse ( List<Object> string ) {
        return parseString ( Conversions.listToString ( string ) );
    }
    
    private static List<Object> parseString ( String string ) {
        
        MyScanner scanner = new MyScanner ( string );
        return requireTree ( scanner );
    }
    
    private static List<Object> requireTree ( MyScanner scanner ) {
        
        List<Object> tree = new ArrayList<> ();
        requireBranch ( scanner, tree );
        return tree;
    }
        
    private static void requireBranch ( MyScanner scanner, List<Object> parentList ) {
        
        skipWhiteSpace ( scanner );
        requireString ( scanner, "[" );
        skipWhiteSpace ( scanner );
        
        while ( ! acceptString ( scanner, "]" ) ) {
            
            skipWhiteSpace ( scanner );

            if ( isAtString ( scanner, "[" ) ) {
                List<Object> branch = new ArrayList<> ();
                requireBranch ( scanner, branch );
                parentList.add ( branch );
            } else {
                String s = requireStringLiteral ( scanner );
                // System.out.println ( s );
                parentList.add ( s );
            }
            
            skipWhiteSpace ( scanner );
            acceptString ( scanner, "," );
            skipWhiteSpace ( scanner );
        }
    }
    
    private static void skipWhiteSpace ( MyScanner scanner ) {
        scanner.consumeRegex ( Pattern.compile ( "[ \\t\\r\\n]+" ) );
    }
    
    private static String requireStringLiteral ( MyScanner scanner ) {
        
        String result = scanner.consumeRegex ( Pattern.compile ( "\\\"(\\\\.|[^\"\\\\])*\\\"" ) );
        if ( result == null ) throw new RuntimeException ( "String literal expected." );
        // TODO replaced escaped characters
        return result.substring ( 1, result.length() - 1 );
    }
    
    private static void requireString ( MyScanner scanner, String string ) {
        
        if ( ! acceptString ( scanner, string ) )
            throw new RuntimeException ( string + "  expected." );
    } 

    private static boolean acceptString ( MyScanner scanner, String string ) {
        return scanner.consumeString ( string );
    } 

    private static boolean isAtString ( MyScanner scanner, String string ) {
        return scanner.isAtString ( string );
    } 

/*
    public static List<Object> restore_from_file ( List<Object> file_path ) {
        
        try {
            Scanner scanner = new Scanner ( Conversions.listToFile ( file_path ), FileWriterUtils.defaultCharSet.name() );
            return requireTree ( scanner );
        } catch ( FileNotFoundException e ) {
            throw new RuntimeException ( e );
        }
    }
    
    public static List<Object> parseString ( String string ) {
        
        Scanner scanner = new Scanner ( string );
        return requireTree ( scanner );
    }
    
    private static List<Object> requireTree ( Scanner scanner ) {
        
        List<Object> tree = new ArrayList<> ();
        requireBranch ( scanner, tree );
        return tree;
    }
        
    private static void requireBranch ( Scanner scanner, List<Object> parentList ) {
        
        requireSymbol ( scanner,"\\[" );
        
        while ( ! acceptSymbol ( scanner, "\\]" ) ) {
            
            if ( scanner.hasNext ( "\\[" ) ) {
                List<Object> branch = new ArrayList<> ();
                requireBranch ( scanner, branch );
                parentList.add ( branch );
            } else {
                String s = requireStringLiteral ( scanner );
                System.out.println ( s );
                parentList.add ( s );
            }
            
            acceptSymbol ( scanner, "," );
        }
    }

    private static String requireStringLiteral ( Scanner scanner ) {
        
        // \"(\\.|[^"\\])*\"
        String stringLiteral = acceptPattern ( scanner, "\\\"(\\\\.|[^\"\\\\])*\\\"" );
        if ( stringLiteral == null ) throw new RuntimeException ( "String literal expected." );
        // TODO replaced escaped characters
        return stringLiteral.substring ( 1, stringLiteral.length() - 1 );
    }

    private static String requirePattern ( Scanner scanner, String pattern ) {
        
        String result = acceptPattern ( scanner, pattern );
        if ( result == null ) throw new RuntimeException ( pattern + " required" );
        return result;
    }

    private static String acceptPattern ( Scanner scanner, String pattern ) {
        
        if ( scanner.hasNext ( pattern ) ) {
            return scanner.next();
        } else {
            return null;
        }
    }

    private static void requireSymbol ( Scanner scanner, String pattern ) {
        if ( ! acceptSymbol ( scanner, pattern ) )
            throw new RuntimeException ( pattern + " required" );
    }

    private static boolean acceptSymbol ( Scanner scanner, String pattern ) {
        
        if ( scanner.hasNext ( pattern ) ) {
            scanner.next();
            return true;
        } else {
            return false;
        }
    }

    private static boolean isOnSymbol ( Scanner scanner, String pattern ) {
        
        return scanner.hasNext ( pattern ) ) {
            scanner.next();
            return true;
        } else {
            return false;
        }
    }
*/
}
