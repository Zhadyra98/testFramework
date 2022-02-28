set projectLocation=C:\level_up\testAutomationFramework
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java org.testng.TestNG %projectLocation%\testng.xml
pause
java -cp %classpath% org.testng.TestNG %ProjectPath%\testng.xml