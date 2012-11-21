Tutorial Hibernate <http://www.davidmarco.es/tutoriales/hibernate-reference/>

Parte 1: La primera aplicación de hibernate
	1. Configuración: crea el pom.xml
	2. La primera clase: crea el Event.java
	3. El archivo de mapeo: crea el Event.hbm.xml
	4. Configuración de Hibernate: hibernate.cfg.xml
	5. Construyendo Maven: mvn compile
	6. Puesta en marcha y clases de ayuda: HibernateUtil.java y log4j.properties
	7. Leyendo y almacenando objetos

Parte 2: Mapeo de asociaciones
	1. Mapeando la clase persona
	2. Una asociación unidimensional basada en set
	3. Trabajando con asociaciones
	4. Colecciones de valores
	5. Asociaciones bidireccionales
	6. Trabajando con enlaces bidireccionales

Parte 3: La aplicación web eventmanager
	1. Escribiendo un servlet básico
	2. Procesando la solicitud y formando la pagina de respuesta.
	3. Desplegando y probando.


Resumen

- Arranca la bbdd:
mvn exec:java -Dexec.mainClass="org.hsqldb.Server" -Dexec.args="-database.0 file:target/data/tutorial"
- Ejecutar:
mvn exec:java -Dexec.mainClass="org.hibernate.tutorial.EventManager" -Dexec.args="store"