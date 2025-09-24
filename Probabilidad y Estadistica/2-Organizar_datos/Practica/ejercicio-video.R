#ejercicio video
#CARGA DE ARCHIVO
if(!require(readxl)) insatall.packages(readxl)
library(readxl)
archivo<-file.choose()
datos<-read_excel(archivo)

#VARIABLE plataforma_Trabajo
tabla_plataforma<-table(datos$Plataforma_Trabajo)
#tabla de frecuencias
tabla_plataforma

tabla<- table(datos$Plataforma_Trabajo)
f_rel<- prop.table(tabla)

tabla_plataforma<- data.frame(
  Proyectos = names(tabla),
  Frec_Abs = as.vector(tabla),
  Frec_Rel = round(as.vector(f_rel),3)
)
#Muestra tabla
tabla_plataforma

#VARIABLE Tickets_Soporte
tabla<- table(datos$Tickets_Soporte)
f_acum<- cumsum(tabla)
f_rel<- prop.table(tabla)
f_rel_acum<- cumsum(f_rel)
summary(tabla)

tabla_tickets<- data.frame(
  Proyectos = names(tabla),
  Frec_Abs = as.vector(tabla),
  Frec_Acum = as.vector(f_acum),
  Frec_Rel = round(as.vector(f_rel),3)
)

#Imprime tabla_tickets
print(tabla_tickets, row.names = FALSE)

#VARIABLE Tiempo_Conexion_Min
cant_filas<- length(datos$Tiempo_Conexion)#obtengo la cantidad de datos
rango <- range(datos$Tiempo_Conexion)# range busca el min y el maximo
print(rango)#muestro rango
k <- ceiling(1 + 3.322 * log10(cant_filas))  # Regla de Sturges
amplitud <- ceiling((rango[2] - rango[1]) / k)
breaks <- seq(floor(rango[1]), ceiling(rango[2]) + amplitud, by = amplitud)
clases <- cut(datos$Tiempo_Conexion, breaks = breaks, right = FALSE)

tabla_tiempo<-table(clases)
f_acum<-cumsum(tabla_tiempo)
f_rel<-prop.table(tabla_tiempo)
f_rel_acum<-cumsum(f_rel)

tabla_tiempo_conexion <- data.frame(
  Intervalo = levels(clases),
  Frecuencia = as.vector(tabla_tiempo),
  Frec_acumulada = as.vector(f_acum),
  Frec_relativa = round(as.vector(f_rel),3),
  Frec_rel_acum = round(as.vector(f_rel_acum),3)
)
#imprimo tabla_tiempo_conexion
print(tabla_tiempo_conexion, row.names = FALSE)