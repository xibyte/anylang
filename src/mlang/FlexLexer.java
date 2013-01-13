package mlang;


public interface FlexLexer  {
    
    void yybegin(int i);
    
    int yystate();
    
    int getTokenStart();
    
    int getTokenEnd();
    
    IElementType advance() throws java.io.IOException;
    
    void reset(CharSequence buf, int start, int end, int initialState);
}