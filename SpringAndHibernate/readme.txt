Ejemplo de integración de uso de Hibernate integrado con Spring.
Tomando como base el tutorial: http://appcode.blogspot.com.es/p/hibernate-3-integracion-con-spring.html

Ejemplo de HQL

Para arrancar la BBDD:
- mvn exec:java -Dexec.mainClass="org.hsqldb.Server" -Dexec.args="-database.0 file:target/data/tutorial"