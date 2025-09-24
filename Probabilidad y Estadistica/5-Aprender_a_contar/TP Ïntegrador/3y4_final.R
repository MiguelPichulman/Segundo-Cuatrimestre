# Carga de libreria
if(!require(readxl)) install.packages("readxl") 
library(readxl)                                  
#CARGA DE ARCHIVO
archivo<-file.choose()
datos<-read_excel(archivo)

#Calculo para Intervalos
rango <- range(datos$`TIEMPO SEMANAL en HS. DEDIC. EST.`)# range busca el min y el maximo de la variable
k <- ceiling(1 + 3.322 * log10(nrow(datos)))# Regla de Sturges para determinar la cantidad de intervalos
amplitud <- ceiling((rango[2] - rango[1]) / k)  #
#---------Armado de intervalos 
cortes <- seq(floor(rango[1]), ceiling(rango[2]) + amplitud, by = amplitud) #almacena los valores que tomaran los intervalos
clases <- cut(datos$`TIEMPO SEMANAL en HS. DEDIC. EST.`, breaks = cortes, right = FALSE) #cut divide los datos en intervalos usando 
#los puntos de corte establecidos en la linea anterior

#CALCULO DE MEDIA
tabla_tiempo<-table(clases)
frecuencias<- as.vector(tabla_tiempo)
media_continua<- sum(marca_clase*frecuencias)/sum(frecuencias)

#CALCULO DE MODA
i_modal<-which.max(frecuencias)
L_m<-cortes[i_modal]
f_m<-frecuencias[i_modal]
f_1<-ifelse(i_modal==1, 0, frecuencias[i_modal-1])
f_2<-ifelse(i_modal== length(frecuencias),0 , frecuencias[i_modal+1])
moda_continua<- L_m+((f_m-f_1)/((f_m-f_1)+(f_m-f_2))) * amplitud

#CALCULO DE MEDIANA
n_total<- sum(frecuencias)
n_2<- n_total/2
clase_mediana_index<- which(f_acum >=n_2)[1]
L<- cortes[clase_mediana_index]
F_anterior<-ifelse(clase_mediana_index==1, 0, f_acum[clase_mediana_index-1])
f_mediana<-frecuencias[clase_mediana_index]
mediana_continua<- L +((n_2 - F_anterior)/ f_mediana) * amplitud

#MEDIDAS DE DISPERSION
varianza_continua<- sum(frecuencias* (marca_clase - media_continua)^2) / (n_total-1)
desvio_continua<-sqrt(varianza_continua)
coef_var_continua<-(desvio_continua / media_continua)*100

#MOSTRAR DATOS
stats<-data.frame(
  Media = round(media_continua,4),
  Moda = round(moda_continua,4),
  Mediana = round(mediana_continua,4),
  Varianza = round(varianza_continua,4),
  Desvio_estandar = round(desvio_continua,4),
  Coef_Variacion_pct = round(coef_var_continua,4)
)
print(stats, row.names=FALSE)