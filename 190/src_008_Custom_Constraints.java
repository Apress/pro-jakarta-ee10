import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class Pzn8Validator implements 
      ConstraintValidator<PZN8, String> {
    protected boolean delimitersIncluded;

    @Override
    public void initialize(PZN8 pzn8) {
        this.delimitersIncluded = pzn8.includeDelimiters();
    }

    @Override
    public boolean isValid(String str, 
          ConstraintValidatorContext ctx) {
        // null values are valid
        if ( str == null )
            return true;
        String str2 = str;
        
        if(delimitersIncluded) {
            if(str.length() < 2) return false;
            if(str.charAt(0) != '*' || 
               str.charAt(str.length()-1) != '*') 
                  return false;
            str2 = str.substring(1, str.length() - 1);
        }
        
        if(str2.length() < 8 || str2.length() > 9) 
            return false;
        
        if(str2.charAt(0) != '-') return false;
        str2 = str2.substring(1);
        
        char checkDigit = str2.charAt(str2.length() - 1);
        str2 = str2.substring(0, str2.length() - 1);
        
        if(!str2.matches("\\d*")) return false;
        
        // the check digit algorithm for PZNs
        int p = 2;
        int s = 0;
        for(int i=0;i<str2.length();i++) {
            s += p * (str2.charAt(i) - '0');
            p++;
        }
        int x = s % 11;
        
        return (checkDigit - '0') == x;
   }
}
