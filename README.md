# Sistema-Turnos
PROYECTO #1 - PRIMER PARCIAL

Integrantes:

-Briones Chonillo Ana

-Venegas Bodero William

-Intriago Ortiz Sandy

Grupo # 5

 Descripcion del proyecto:
 
Usted creará un sistema de turnos para la atención de los pacientes que tienen una emergencia médica en un hospital y necesitan ser atendidos conforme a la prioridad de su enfermedad. La apariencia del sistema de turnos debe ser similar a la siguiente imagen mostrada. 
Los síntomas estarán almacenados en un archivo sintomas.txt con su respectiva prioridad. Las prioridades han sido definidas del 0 al 5, donde 0 es la prioridad más alta de atención. Ejemplo:
sintomas.txt
vómito|1
fiebre|0
resfrio|3
congestión nasal|3
……..
Se implementa un sistema de turnos para la atención de los pacientes que tienen una emergencia médica en un hospital y necesitan ser atendidos conforme a la prioridad de su enfermedad. Se realiza formulario donde se permita crear puestos o eliminarlos. A cada puesto se le puede asignar un médico responsable. El sistema permite crear médicos con sus datos y especialidad. El puesto puede ser eliminado siempre y cuando primero se elimine la asignación del médico a ese puesto. La información de los médicos y puestos puede almacenarla en archivos de texto plano.
Se crea un formulario para ingresar los datos del paciente como nombres, apellidos, edad, género y el síntoma que presenta. Esta información se almacena en un archivo de texto plano. En el momento en que se ingresa esta información el paciente es derivado a un puesto.
Para simular la atención de los pacientes, se crea una ventana que permite atender a los pacientes registrando el diagnóstico y receta. En el momento en que se registra el diagnóstico y la receta el puesto queda liberado para atender otro paciente.
Con la finalidad de generar entretenimiento mientras se espera la atención, la ventana de turnos muestra videos de entretenimiento de manera continua, los videos se encuentran guardados en un archivo llamado videos.txt, el cual tiene la ruta donde se encuentran los videos a proyectar en la ventana de turnos. Ejemplo:
videos.txt
Video001.wav
Video002.wav
Video003.wav
….

Requerimientos:

•	Diseño (Diagrama de Clases) de las relaciones de los TDAs que participan en la simulación.

    Anexado en el proyecto
   
•	Implementación del sistema de turno en interfaz gráfica.

•	Reporte de evaluación de correcto funcionamiento de las funcionalidades solicitadas.

    Anexado en el proyecto 
 •	Es necesario integrar este codigo al VM de properties en su Proyecto 
 
´´´
--add-modules=javafx.swing,javafx.graphics,javafx.fxml,javafx.media,javafx.web --add-reads javafx.graphics=ALL-UNNAMED --add-opens javafx.controls/com.sun.javafx.charts=ALL-UNNAMED --add-opens javafx.graphics/com.sun.javafx.iio=ALL-UNNAMED --add-opens javafx.graphics/com.sun.javafx.iio.common=ALL-UNNAMED --add-opens javafx.graphics/com.sun.javafx.css=ALL-UNNAMED --add-opens javafx.base/com.sun.javafx.runtime=ALL-UNNAMED

´´´
 

 

