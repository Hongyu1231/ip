@ECHO OFF

REM
if not exist ..\bin mkdir ..\bin

REM
del /Q ..\bin\batman\*.class
del /Q ..\bin\task\*.class
del /Q ..\bin\exception\*.class

REM
javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\batman\*.java ..\src\main\java\task\*.java ..\src\main\java\exception\*.java

IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM
java -classpath ..\bin batman.Batman < input.txt > ACTUAL.TXT

REM
FC ACTUAL.TXT EXPECTED.TXT