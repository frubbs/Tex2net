cls
@echo off 
setlocal enableDelayedExpansion 

set MYDIR=C:\Users\rafa\Documents\Artigos\DatasetDOU\txt



for /F %%D in ('dir /a:d /b %MYDIR%') do (

	
	
	echo coisa %MYDIR%\%%D
	
	for /F %%x in ('dir /B/A:-D %MYDIR%\%%D') do (

	 pause		 
	 echo Gerar fragmentos : %MYDIR%\%%D\%%x
	 java -Xmx1100M -jar C:\Users\rafa\Documents\Projects\Text2Net\console-processor\out\artifacts\console_processor_jar\console-processor.jar %MYDIR%\%%D\%%x
	 
	)
	
	for /F %%y in ('dir /B/A:-D %MYDIR%\%%D\Fragmentos') do (

	 pause		 
	 echo Gerar conexoes : %MYDIR%\%%D\Fragmentos\%%y
	 java -Xmx1100M -jar C:\Users\rafa\Documents\Projects\Text2Net\console-processor\out\artifacts\console_processor_jar2\console-processor.jar %MYDIR%\%%D\Fragmentos\%%y
	)	
)
echo terminou!