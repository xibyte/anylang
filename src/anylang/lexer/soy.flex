package soy.lexer;

import mlang.IElementType;
import static soy.lexer.SoyElementTypes.*;

%%

%{

public int offset() {
    return yychar;
}

public int yyline() {
    return yyline;
}

public int yycolumn() {
    return yycolumn;
}

%}

%char
%line
%column
%class _SoyLexer
%final
%unicode
%function advance
%type IElementType
%eof{ return;
%eof}

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]
InputCharacter = [^\r\n]

Comment = {TraditionalComment} | {EndOfLineComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?

FQNSet = [\._a-zA-Z]
Variable = "$" {FQNSet}*
StringSingle = [\'] [^\']* [\"\']
StringDouble = [\"] [^\"]* [\"]
String = {StringSingle} | {StringDouble}

TagName = [:jletter:][jletterdigit]*

%x SCRIPTING
%x DOCLET
%x IN_PARAM
%x LOOKING_NAME

%%

<YYINITIAL> {
    "/*"                     { yybegin(DOCLET); return DOCLET_START; }
    "{"                 { yybegin(SCRIPTING); return SCRIPT_START; }
    "{/"                     { yybegin(SCRIPTING); return SCRIPT_START; }
    //{WhiteSpace}+            { return WHITE_SPACE; }
    [^]                      { return TEMPLATE_DATA; }
}

<DOCLET> {

    "/*"                      { yybegin(YYINITIAL); yypushback(2); return DOCLET_END; }
    "*/"                      { yybegin(YYINITIAL); return DOCLET_END; }

    "@param" "?"? [ ]+         { yybegin(IN_PARAM); return COMMENT; }
    [^]                        { return COMMENT; }
}

<IN_PARAM> {FQNSet}+ {
    yybegin(DOCLET);
    return PARAM;
}

<LOOKING_NAME> {
    {FQNSet}+                 { return NAME; }
}

<SCRIPTING> {
    "namespace"               { yybegin(LOOKING_NAME); return SCRIPT_NAME; }
    "template"                { yybegin(LOOKING_NAME); return SCRIPT_NAME; }
    [a-zA-z_]+                 { return SCRIPT_NAME; }
}

<SCRIPTING, LOOKING_NAME> {
    "}"                       { yybegin(YYINITIAL); return SCRIPT_END; }
    "{"                       { yybegin(YYINITIAL); yypushback(1); return SCRIPT_END; }
    {Comment}                 { return COMMENT; }
    {Variable}                { return VARIABLE; }
    {WhiteSpace}+             { return WHITE_SPACE; }
    [^]                       { return BAD_CHARACTER; }
}


