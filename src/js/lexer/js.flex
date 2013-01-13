package js.lexer;

import mlang.IElementType;
import static js.lexer.JSElementTypes.*;
import mlang.Lexer;

%%

%{

public int offset() {
    return yychar;
}

%}

%char
%class _JSLexer
%implements Lexer
%final
%unicode
%function advance
%type IElementType

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]
InputCharacter = [^\r\n]

Comment = {Doclet} | {MultiComment} | {SingleComment}
Doclet = "/**" [^*] ~"*/" | "/*" "*"+ "/"
MultiComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
SingleComment = "//" {InputCharacter}* {LineTerminator}?

ID = [:jletter:] [:jletterdigit:]*
VAR = ID("."ID)*

String = {StringSingle} | {StringDouble}
StringSingle = [\'] [^\']* [\"\']
StringDouble = [\"] [^\"]* [\"]
Assign =  {VAR} {WhiteSpace}* "="
JsonAssign =  {VAR} {WhiteSpace}* ":"

%x IN_FUNC_DEF
%x IN_ARGS
%x IN_WAITING_FUNC_START

%%

<YYINITIAL> {
  {VAR}                 { return VAR;}
  {String}              { return STRING;}
  "function"            { yybegin(IN_FUNC_DEF); return FUNCTION;}

  "{"                   { return BRACE_OPEN; } 
  "}"                   { return BRACE_CLOSE; } 
}

<IN_FUNC_DEF> {
  {ID}                   { return FUNCTION_NAME;}
  "("                    { yybegin(IN_ARGS); return BRACKET_OPEN;} 
}

<IN_ARGS> {
  {ID}                   { return ARG;}
  ","                    { return COMMA;} 
  ")"                    { yybegin(IN_WAITING_FUNC_START); return BRACKET_CLOSE;} 
}

<IN_WAITING_FUNC_START> {
  "{"                    { yybegin(YYINITIAL); return BRACE_OPEN;}
}

<YYINITIAL, IN_FUNC_DEF, IN_ARGS, IN_WAITING_FUNC_START> {
  {SingleComment}        { return SINGLE_COMMENT;}
  {Doclet}               { return DOCLET;}
  {MultiComment}         { return MULTI_COMMENT;}
  {WhiteSpace}+          { return WHITESPACE;}
  [^]                    { return OTHER; }
}


