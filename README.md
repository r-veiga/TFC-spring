TFC-spring
==========

Nuevo enfoque: Maven + JSF + Spring + JPA (implementación Hibernate)


(1) Directorios de la aplicación
(1.1) rob.proyecto.tfc.core
(1.1.1) rob.proyecto.tfc.core.dao
(1.1.2) rob.proyecto.tfc.core.exception
(1.1.3) rob.proyecto.tfc.core.service
(1.2) rob.proyecto.tfc.data
(1.2.1) rob.proyecto.tfc.data.entity
(1.2.2) rob.proyecto.tfc.data.vo
(1.3) rob.proyecto.tfc.web
(1.3.1) rob.proyecto.tfc.web.jsf.mbean
(1.3.2) rob.proyecto.tfc.web.listener
(2) rob.proyecto.tfc.TFCConstantes.java
(3) Pruebas unitarias



(1) Directorios de la aplicación
--------------------------------
La aplicación está estructurada en tres directorios .core, .data y .web.

(1.1) rob.proyecto.tfc.core
(1.1.1) rob.proyecto.tfc.core.dao
Contiene los interfaces de las clases DAO. 
Las clases DAO sólo serán consumidas por las clases de servicio de Spring. 
En el proyecto, los servicios Spring tirarán siempre contra los interfaces de las clases, nunca contra sus implementaciones.

Los DAO de esta aplicación heredan de la clase AbstractDAOImpl donde se define el EntityManager de forma común a todos ellos. Se inyecta desde Spring con el EntityManagerFactory con la anotación @PersistenceContext de JPA. 

Asimismo, esta carpeta contiene una subcarpeta .impl con implementaciones de las clases. 
Son clases de tipo Spring: contienen la anotación de Spring @Repository a nivel de clase. 

La anotación de Spring @Repository define estas clases como clases “Repositorio” (cuya finalidad es cumplir con el patrón DAO). 
Con esta anotación se clarifica su rol dentro de la arquitectura de la aplicación. 
La anotación permite que la clase sea autodetectada por Spring. No es necesario definirla explícitamente en el xml.
Una clase de este tipo puede lanzar la excepción Spring DataAccessException. 

El lenguaje de consultas que se emplea es un SQL sobre entidades, JQL. No es Criteria ni SQL nativa. Se aprovecha en lo posible las NamedQuery.

(1.1.2) rob.proyecto.tfc.core.exception
Las tres excepciones: 

TFCException.java
Excepción padre. 
Es el tipo de excepción que se captura en la capa de Vista (Managed Beans JSF). 

TFCCoreException.java
Excepción hija de TFCException. 
Se emplea cuando se produce una excepción en la capa de servicios (lógica de negocios). 
Las excepciones se propagan hasta la capa de Vista. 
 
TFCSQLException.java
Excepción hija de TFCException. 
Se emplea cuando se produce una excepción en la capa DAO. 
Las excepciones que se controlan en bloques try/catch en esta capa son errores al acceder a la BBDD.
Las excepciones se propagan hasta la capa de Vista.

Podría comprobarse en la Vista cuál es la excepción generada, pero en este momento no se está realizando. Es suficiente con controlar la excepción padre.

(1.1.3) rob.proyecto.tfc.core.service
Contiene las clases Spring, que conforman la capa de lógica de negocio propiamente dicha. 

Contiene los interfaces de las clases Spring que serán llamadas por los Managed Beans JSF. 
En el proyecto, los Managed Beans JSF tirarán siempre contra los interfaces de las clases de servicio, nunca contra sus implementaciones.

Asimismo, esta carpeta contiene una subcarpeta .impl con implementaciones de las clases. 
Son clases de tipo Spring: contienen la anotación de Spring @Service a nivel de clase. 
La anotación permite que la clase sea autodetectada por Spring. No es necesario definirla explícitamente en el xml.

Los métodos de lógica de negocio que modifican datos en BBDD se marcan con la anotación de Spring @Transaction. Se usa la anotación de Spring y no la de JPA porque el framework usado para la transaccionabilidad es de Spring. 
El manejo y la gestión de JPA se delega en el framework de Spring. Esto se define en el ApplicationContext.xml. 

Las anotaciones empleadas de Spring en los métodos servicios son: 

@Transactional 
Se aplica la anotación Spring porque el motor transaccional se define en el ApplicationContext.xml de Spring

@Autowired y @Qualifier
Son dos anotaciones Spring que en este proyecto se usan siempre juntas. Indican concisamente el Bean de Spring (servicio o repository) que debe inyectarse. 
Autowired indica que inyecte algún componente de Spring que implemente el interfaz indicado. 
Qualifier especifica cuál componente Spring queremos emplear. Este valor se define en el caso de los repositorios cuando se emplea la anotación @Repository(“qualif”). 


(1.2) rob.proyecto.tfc.data

Esto es la gestión de datos. 
Son POJOs de entidades. 

(1.2.1) rob.proyecto.tfc.data.entity
Aquí se guardan las entidades JPA. Hay dos tipos de entidades:
la descripción de las tablas de la BBDD
la descripción de las claves primarias en aquellas tablas que contienen una clave primaria “compleja” de más de un campo

Se ha decidido trabajar sin una clase abstract que unifique aspectos de todas las entidades JPA. Cada una de las entidades JPA se codifica por separado. 
Las entidades JPA se han creado mediante los asistentes del IDE, por eso se han arrastrado todas las foreign keys presentes en el modelo de BBDD a su implementación. Cuando ha sido necesario se han borrado manualmente las foreign key. 

(1.2.2) rob.proyecto.tfc.data.vo
Aquí se tienen los “value objects” o “business objects”. 
Estos son objetos que componen los servicios a partir de la información extraída de diferentes DAOs. 
Básicamente, son beans con los datos que necesita consumir la capa de vista. En vez de presentar varias entidades JPA, se encapsula todo a la medida necesaria para el consumo por el usuario.
El único VO se emplea en UsuariosMB y en UsuariosServiceImpl. 

(1.3) rob.proyecto.tfc.web

(1.3.1) rob.proyecto.tfc.web.jsf.mbean
Los MB están estructurados por funcionalidad. 
Es decir, no se  emplea la idea de un MB por Facelet (.xhtml). La finalidad es evitar la duplicidad de código. 

Casi todos los MB tienen un ámbito @ViewScoped
Algunas de las funciones de un mismo MB son empleadas por distintas pantallas/vistas.

Hay un MB @ApplicationScoped. 


Hay un MB @SessionScoped. 


(1.3.2) rob.proyecto.tfc.web.listener
Sólo un listener, que se emplea para leer del archivo web.xml la ruta del repositorio donde son almacenados los ficheros uploaded con los resultados de las pruebas diagnósticas a los pacientes. 
El listener es del paquete Servlet. 
Se ejecuta una sola vez durante el arranque de la aplicación. 

El contexto de Spring se carga también en el arranque de la aplicación mediante otro listener incluido en las librerías Spring. 
Está definido en el web.xml para que se ejecute. 

(2) rob.proyecto.tfc.TFCConstantes.java
---------------------------------------
public final class TFCConstantes

public static enum TIPO_SANITARIO {
		MEDICO, ENFERMERIA, AUXILIAR, REHABILITACION, TRABAJO_SOCIAL
}
public static final String WEBCFG_KEY_TFC_FILE_REPOSITORY = "tfc.upload.file.repository";
public static String       TFC_FILE_REPOSITORY = "";

¿Para qué WEBCFG_KEY_TFC_FILE_REPOSITORY?
¿Para qué TFC_FILE_REPOSITORY?

Define y centraliza los valores constantes a emplear en cualquier punto de la aplicación (Vista-Modelo-Controlador).
Son valores que no se pueden definir en el Managed Bean JSF @ApplicationScoped porque éste sólo afecta a la capa de Vista. Sus propiedades no son accesibles fácilmente desde los servicios y repositorios Spring. 


(3) Pruebas unitarias

Las pruebas unitarias prueban: 

MB + Core  + Dao + DDBB
Core + Dao + DDBB
Dao + DDBB 

Spring tiene un motor de ejecución de pruebas unitarias @RunWith(Spring….). 
Sin este motor no se puede usar el contexto de Spring para las pruebas unitarias (nada de inyecciones). 
Se indica también @ContextConfiguration(locations = “classpath:applicationContext-TEST.xml”) 

En las pruebas de este proyecto sólo empleo el contexto de Spring. 
Existe un motor de JUnit para JSF, pero no lo voy a emplear. Tampoco se empleará EasyMock, PowerMock, Mockito… (frameworks de Mock). 
Sin embargo estoy probando los MBean como si fuesen POJOs. 





