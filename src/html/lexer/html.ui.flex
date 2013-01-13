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
//WhiteSpace = {LineTerminator} | [ \t\f]

String = {StringSingle} | {StringDouble}
StringSingle = [\'] [^\']* [\"\']
StringDouble = [\"] [^\"]* [\"]
Name = [-A-Za-z0-9_:]+

Comment = "<!--" ~ "-->"

%x IN_TAG
%x IN_SCRIPT_TAG

%%

<YYINITIAL> {
    {Comment}             { return COMMENT; } 
    "</script>"           { return CLOSING_SCRIPT_TAG; } 
    "</"{Name}">"         { return CLOSING_TAG; } 
    "<script"             { yybegin(IN_TAG); return START_SCRIPT_TAG; }
    "<"{Name}             { yybegin(IN_TAG); return START_TAG; }
    [^]                   { return OTHER; }
}

<IN_TAG> {
  "/>"                    { yybegin(YYINITIAL); return END_TAG; } 
  ">"                     { yybegin(YYINITIAL); return END_TAG; } 
  {Name} "="?               { return PARAM; }
  {String}                { return STRING; }
  [^]                     { return OTHER; } 
}


