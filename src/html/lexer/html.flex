package html.lexer;

import mlang.IElementType;
import mlang.Lexer;
import static html.lexer.HTMLElementTypes.*;

%%

%{

public int offset() {
    return yychar;
}


%}

%char
%class _HTMLLexer
%final
%unicode
%function advance
%type IElementType
%implements Lexer

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]

String = {StringSingle} | {StringDouble}
StringSingle = [\'] [^\']* [\"\']
StringDouble = [\"] [^\"]* [\"]

Comment = "<!--" ~ "-->"

ClosingTag = "</" ~ ">"

%x IN_LOOKUP_NAME
%x IN_TAG

%%

<YYINITIAL> {
    "<"                   { yybegin(IN_LOOKUP_NAME); return START_TAG; }
    {ClosingTag}          { return CLOSING_TAG; }
    {Comment}             { return COMMENT; } 
    [^]                   { return OTHER; }
}

<IN_LOOKUP_NAME> {
   [$-:_a-zA-Z0-9]+         { yybegin(IN_TAG); return TAG_NAME; }
}

<IN_TAG, IN_LOOKUP_NAME> {
  "/>"                     { yybegin(YYINITIAL); return END_SINGLE_TAG; } 
  ">"                     { yybegin(YYINITIAL); return END_TAG; } 
  {String}                { return STRING; }
  [^]                     { return OTHER; } 
}




