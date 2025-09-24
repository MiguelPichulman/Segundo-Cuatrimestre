# 1.Carga de libreria
if(!require(readxl)) install.packages("readxl")
library(readxl)
# 2.Carga archivo
archivo <- file.choose()
datos <- read_excel(archivo)

#tabla de frecuencia para variable categorica
#cantidad de veces q aparece cada lenguaje
tabla_lenguajes <- table(datos$Lenguaje_Favorito)
#mostrar tabla
tabla_lenguajes

#tabla de frecuencia para variable cualitativa
tabla<- table(datos$Proyectos_Completados)
f_acum<- cumsum(tabla)
f_rel<- prop.table(tabla)
f_rel_acum<- cumsum(f_rel)

tabla_frecuencia<- data.frame(
  Proyectos = names(tabla),
  Frec = as.vector(tabla),
  Frec_acum = as.vector(f_acum),
  Frec_rel = round(as.vector(f_rel),3),
  Frec_rel_acum = round(as.vector(f_rel_acum),3)
)
tabla_frecuencia
print(tabla_frecuencia, row.names = FALSE)