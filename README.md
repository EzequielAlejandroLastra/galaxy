# galaxy 

@Autor Lastra Ezequiel

Premisas:
- Todos los planetas tienen su punto inicial en cero(0) grados.
- Los años tienen 365 dias.
- No hay años bisiestos.


Tecnologias utilizadas:
- Java 8
- SpringBoot (Primera vez que lo utilizo)
- Liquibase
- H2
- Gradle


Urls disponibles:

- https://hidden-stream-51833.herokuapp.com/prediction/generate-predictions/10
    
    Llama el servicio de calculo de predicciones.
    Recibe un entero que indica la cantidad de años a proyectar.
    Devuelve un mensaje indicando como finalizo el proceso.
    
    
    
    
- https://hidden-stream-51833.herokuapp.com/prediction/get-prediction/22   
    
    Llama el servicio de busqueda de predicciones climaticas.
    Recibe un entero que indica el dia que se qiere consultar.
    Devuelve un mensaje indicando el clima para ese dia. 