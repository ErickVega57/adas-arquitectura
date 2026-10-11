Compilar programa 

Linux/MacOS
javac -d out/ $(find src -name "*.java")

Windows
javac -d out (Get-ChildItem -Path src -Filter *.java -Recurse).FullName

Ejecutar

Linux/MacOS
java -cp out/ Principal

Windows
java -cp .\out\ Principal