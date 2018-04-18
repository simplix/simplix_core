package org.simplix.core.data;

import java.math.BigDecimal;
import java.util.List;

public class se_number {
    
    public static List<Object> equals ( List<Object> op1, List<Object> op2 ) {
        
        boolean result = Conversions.listToBigDecimal ( op1 ).equals ( Conversions.listToBigDecimal ( op2 ) );
        return Conversions.booleanToList ( result );
    }
    
    public static List<Object> add ( List<Object> op1, List<Object> op2 ) {
        
        BigDecimal result = Conversions.listToBigDecimal ( op1 ).add ( Conversions.listToBigDecimal ( op2 ) );
        return Conversions.stringToList ( result.toString() );
    }
    
    public static List<Object> subtract ( List<Object> op1, List<Object> op2 ) {
        
        BigDecimal result = Conversions.listToBigDecimal ( op1 ).subtract ( Conversions.listToBigDecimal ( op2 ) );
        return Conversions.stringToList ( result.toString() );
    }
    
    public static List<Object> multiply ( List<Object> op1, List<Object> op2 ) {
        
        BigDecimal result = Conversions.listToBigDecimal ( op1 ).multiply ( Conversions.listToBigDecimal ( op2 ) );
        return Conversions.stringToList ( result.toString() );
    }
    
    public static List<Object> divide ( List<Object> op1, List<Object> op2 ) {
        
        BigDecimal result = Conversions.listToBigDecimal ( op1 ).divide ( Conversions.listToBigDecimal ( op2 ) );
        return Conversions.stringToList ( result.toString() );
    }

}
