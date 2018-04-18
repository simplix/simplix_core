package org.simplix.core.data;

import java.util.List;
import java.util.function.Consumer;

public class TreeUtils {

    @SuppressWarnings ( "unchecked" )
	public static boolean isEqual ( List<Object> tree_1, List<Object> tree_2 ) {
	    
	    if ( tree_1.size() != tree_2.size() ) return false;
        
        for ( int i = 0; i < tree_1.size(); i++ ) {
	        Object element_1 = tree_1.get ( i );
	        Object element_2 = tree_2.get ( i );
	        if ( element_1 instanceof String && element_2 instanceof String ) {
                if ( ! ( (String) element_1 ).equals ( (String) element_2 ) )
                    return false;
            } else if ( element_1 instanceof List && element_2 instanceof List ) {
	            if ( ! isEqual ( (List<Object>) element_1, (List<Object>) element_2 ) )
                    return false;
            }
        }
        
        return true;
	}

    public static void forEachStringNodeInTree ( List<Object> tree, Consumer<String> consumer ) {
		
		for ( Object element : tree ) {
			if ( element instanceof String ) {
				consumer.accept ( (String) element );
			} else if ( element instanceof List ) {
				@SuppressWarnings("unchecked")
				List<Object> subList = (List<Object>) element;
				forEachStringNodeInTree ( subList, consumer );
			} else {
				if ( element == null ) {
					throw new RuntimeException ( "Unexpected element null" );
				} else {
					throw new RuntimeException ( "Unexpected type" + element.getClass ().getName () );
				}
			}
		}
	}
	
	public static void visitTreeNodes (
		List<Object> tree,
		Consumer<List<Object>> treeStartHandler,
		Consumer<List<Object>> treeEndHandler,
		Consumer_4<List<Object>, Integer, Boolean, Integer> branchStartHandler, // branch, index, is_last, level
		Consumer_4<List<Object>, Integer, Boolean, Integer> branchEndHandler,   // branch, index, is_last, level
		Consumer_4<String, Integer, Boolean, Integer> stringNodeHandler ) {     // value, index, is_last, level
    	
    	treeStartHandler.accept ( tree );
    	visitTreeNodes_2 ( tree,0,
			stringNodeHandler,
			branchStartHandler, branchEndHandler );
    	treeEndHandler.accept ( tree );
	}

	private static void visitTreeNodes_2 (
		List<Object> tree,
		int level,
		Consumer_4<String, Integer, Boolean, Integer> stringNodeHandler,
		Consumer_4<List<Object>, Integer, Boolean, Integer> branchStartHandler,
		Consumer_4<List<Object>, Integer, Boolean, Integer> branchEndHandler ) {
    	
    	final int size = tree.size();
    	int index = 1;
		for ( Object element : tree ) {
			boolean is_last = index == size;
			if ( element instanceof String ) {
				stringNodeHandler.accept ( (String) element, index, is_last, level );
			} else if ( element instanceof List ) {
				@SuppressWarnings("unchecked")
				List<Object> subList = (List<Object>) element;
				branchStartHandler.accept ( subList, index, is_last, level );
				visitTreeNodes_2 ( subList, level + 1,
					stringNodeHandler,
					branchStartHandler, branchEndHandler );
				branchEndHandler.accept ( subList, index, is_last, level );
			} else {
				if ( element == null ) {
					throw new RuntimeException ( "Unexpected element null" );
				} else {
					throw new RuntimeException ( "Unexpected type" + element.getClass ().getName () );
				}
			}
			index = index + 1;
		}
	}
	
}
