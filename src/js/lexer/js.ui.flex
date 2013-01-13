package js.lexer;

import mlang.IElementType;
import mlang.Lexer;
import static js.lexer.JSElementTypes.*;

%%

%{

public int offset() {
    return yychar;
}

%}

%buffer 1000
%char
%class _JSPresentationLexer
%final
%unicode
%function advance
%type IElementType
%implements Lexer

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace = {LineTerminator} | [ \t\f]

String = {StringSingle} | {StringDouble}
StringSingle = [\'] [^\']* [\"\']
StringDouble = [\"] [^\"]* [\"]

Doclet   = "/**" ~ "*/"
Comment   = "/*" ~ "*/"
EOFComment = "//" {InputCharacter}* {LineTerminator}


%%

<YYINITIAL> {
    {String}              { return STRING; }
    {Doclet}              { return DOCLET; }
    {Comment}             { return COMMENT; }
    [$_a-zA-Z0-9]+         { return WORD; }
    {EOFComment}          { return EOF_COMMENT; }
    [^]                   { return OTHER; }
}