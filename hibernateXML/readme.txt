Ejemplo de Hibernate siguiendo el tutorial: http://www.davidmarco.es/tutoriales/hibernate-reference/

Arrancar la BBDD, hay varias opciones:
- mvn exec:java -Dexec.mainClass="org.hsqldb.Server" -Dexec.args="-database.0 file:target/data/tutorial"
- java -classpath ..\war\WEB-INF\lib\hsqldb.jar org.hsqldb.Server -database test

Ejecutar el ejemplo:
 - mvn exec:java -Dexec.mainClass="com.util.EventManager" -Dexec.args="store"

