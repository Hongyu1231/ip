@ECHO OFF

REM Create a bin folder if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM Delete previous output file
if exist ACTUAL.TXT del ACTUAL.TXT

REM Compile the code
REM Note: We use *.java to compile Batman, Task, Todo, etc. all at once
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\*.java

IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM Run the program, feed input.txt, and capture output to ACTUAL.TXT
java -classpath ..\bin Batman < input.txt > ACTUAL.TXT

REM Compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT