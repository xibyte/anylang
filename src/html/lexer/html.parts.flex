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

%buffer 1000
%char
%class _HTMLPresentationLexer
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

%x IN_TAG
%x IN_SCRIPT_TAG

%%

<YYINITIAL> {
    "</script>"           { return END_SCRIPT_TAG; } 
    "<"                   { yybegin(IN_TAG); return START_TAG; }
    "<script"             { yybegin(IN_TAG); return START_SCRIPT_TAG; }
    {Comment}             { return COMMENT; } 
    [^]                   { return OTHER; }
}

<IN_TAG> {
  ">"                     { yybegin(YYINITIAL); return END_TAG; } 
  {String}                { return STRING; }
  [^]                     { return OTHER; } 
}


