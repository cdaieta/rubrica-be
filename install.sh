# Build the app
clear
cd /c/Desarrollo/Workspaces/ws_java/rubrica-poc
mvn clean package
cp target/rubrica-poc-0.0.1-SNAPSHOT.jar /c/Desarrollo/Varios/rubricas/
# cp src/main/resources/imagenes.txt /c/Desarrollo/Varios/rubricas/
# cp -r src/main/resources/images /c/Desarrollo/Varios/rubricas/
# cp -r src/main/resources/jasper /c/Desarrollo/Varios/rubricas/

# Run the app
cd /c/Desarrollo/Varios/rubricas/
java -jar rubrica-poc-0.0.1-SNAPSHOT.jar

