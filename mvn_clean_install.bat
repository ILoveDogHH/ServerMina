@echo off
setlocal EnableDelayedExpansion
:: 设置时间
for /f %%i in ('powershell ^(Get-Date -f yyyy-MM-dd^)') do set currentdate=%%i
set year=%currentdate:~0,4%
set month=%currentdate:~5,2%
if "%month:~0,1%" == " " set month=0%month:~1,1%
set day=%currentdate:~8,2%
if "%day:~0,1%" == " " set day=0%day:~1,1%

set hour=%time:~0,2%
if "%hour:~0,1%" == " " set hour=0%hour:~1,1%
set min=%time:~3,2%
if "%min:~0,1%" == " " set min=0%min:~1,1%
set secs=%time:~6,2%
if "%secs:~0,1%" == " " set secs=0%secs:~1,1%

set currenttime=%year%%month%%day%%hour%%min%%secs%

:: 设置输出地址
set outputpath=%~dp0
set param1=%1
if param1=="" set outputpath=%1
set outputpath=%outputpath%/output-site

:: 执行mvn
mvn -Dbuild.current.path.output=%outputpath% -Dbuild.current.time=%currenttime% clean install antrun:run@copy-output-files> out.log
