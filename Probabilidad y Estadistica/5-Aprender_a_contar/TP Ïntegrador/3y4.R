# Carga de libreria
if(!require(readxl)) install.packages("readxl") #verifica si esta insatalda la libreria 
library(readxl)                                 #carga la libreria 
#CARGA DE ARCHIVO
archivo<-file.choose()             #pide seleccionar el archivo excel y lo guarda como dataframe
datos<-read_excel(archivo)          #en la variable datos 
#Variable: Tiempo de estudio

#--------Calculo de datos

rango <- range(datos$`TIEMPO SEMANAL en HS. DEDIC. EST.`)# range busca el min y el maximo de la variable
k <- ceiling(1 + 3.322 * log10(nrow(datos)))# Regla de Sturges para determinar la cantidad de intervalos
amplitud <- ceiling((rango[2] - rango[1]) / k)  #
#---------Armado de intervalos 
cortes <- seq(floor(rango[1]), ceiling(rango[2]) + amplitud, by = amplitud) #almacena los valores que tomaran los intervalos
clases <- cut(datos$`TIEMPO SEMANAL en HS. DEDIC. EST.`, breaks = cortes, right = FALSE) #cut divide los datos en intervalos usando 
#los puntos de corte establecidos en la linea anterior

#Tabla de frecuencias
tabla_tiempo<-table(clases)
f_acum<-cumsum(tabla_tiempo)
f_rel<-prop.table(tabla_tiempo)
f_rel_acum<-cumsum(f_rel)
marca_clase<-(head(cortes,-1)+ tail(cortes,-1))/2#calcula marca de clase

#Armado de tabla
tabla_estudio_en_hs <- data.frame(
  Intervalo = levels(clases),
  Marca= as.vector(marca_clase),
  Frec_Abs= as.vector(tabla_tiempo),
  Frec_Abs_acum = as.vector(f_acum),
  Frec_rel = round(as.vector(f_rel),4),
  Frec_rel_acum = round(as.vector(f_rel_acum),4)
)
#imprimo tabla_estudio_en_hs
#print(tabla_estudio_en_hs, row.names = FALSE)
########################### EMPIEZA ##########################
#CALCULO DE MEDIA PARA LA VARIABLE TIEMPO SEMANAL EN HORAS DE ESTUDIO
frecuencias<- as.vector(tabla_tiempo)#vector de frec abs 
media_continua<- sum(marca_clase*frecuencias)/sum(frecuencias)#sumatoria de multiplicar cada marca de clase por su frec abs y luego se
#divide por el total de observaciones

#CALCULO DE MODA
i_modal<-which.max(frecuencias)# clase con mayor frec
L_m<-cortes[i_modal]#limite inferior del intervalo modal
f_m<-frecuencias[i_modal]#frecuencia del intervalo modal
f_1<-ifelse(i_modal==1, 0, frecuencias[i_modal-1])#frecuencia anterior
f_2<-ifelse(i_modal== length(frecuencias),0 , frecuencias[i_modal+1])#frecuencia posterior
moda_continua<- L_m+((f_m-f_1)/((f_m-f_1)+(f_m-f_2))) * amplitud #formula de interpolacionpara calcular moda

#CALCULO DE MEDIANA
n_total<- sum(frecuencias)#suma para saber el total de observaciones
n_2<- n_total/2#encontrar la posicion de la mediana
clase_mediana_index<- which(f_acum >=n_2)[1] #intervalo en el q aparece una frec igual o mayor a ese valor
L<- cortes[clase_mediana_index]#limite inferior
F_anterior<-ifelse(clase_mediana_index==1, 0, f_acum[clase_mediana_index-1])#frec acum anterior
f_mediana<-frecuencias[clase_mediana_index]#frec de la clase de la mediana
mediana_continua<- L +((n_2 - F_anterior)/ f_mediana) * amplitud# formula de interpolacion

#MEDIDAS DE DISPERSION
varianza_continua<- sum(frecuencias* (marca_clase - media_continua)^2) / (n_total-1)#usamos marca de clase y multiplicamos por frec de cada clase
desvio_continua<-sqrt(varianza_continua)#desvio estandar
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

##INICIO VARIABLE SATISFACCION CON CARRERA

print(summary(datos$`SATISFACCIÓN CON LA CARRERA`))
#calculo de moda
frecuencias<-table(datos$`SATISFACCIÓN CON LA CARRERA`)#armar tabla de frecuencias
moda<-as.numeric(names(frecuencias[frecuencias==max(frecuencias)]))#busca numero con mayor frecuencias
print(moda)


#------------Diagramas-------------
#HISTOGRAMA
hist(datos$`TIEMPO SEMANAL en HS. DEDIC. EST.`,
     breaks = seq(5, 23,2),
     col = "skyblue",
     main = "Histograma de Tiempo de Estudio en Semanas",
     xlab = "Horas Semanales",
     ylab = "Frecuencia",
     freq = TRUE,
     )

#CIRCULAR
tabla_satisfaccion<- table(datos$`SATISFACCIÓN CON LA CARRERA`)
f_rel<- prop.table(tabla_satisfaccion)
#----Armado de tabla
tabla_satisf_carrera<- data.frame(
  Satisfaccion = names(tabla_satisfaccion),
 Frecuencia = as.vector(tabla_satisfaccion),
  Frec_acum = cumsum(tabla_satisfaccion),
  Frec_Rel = round(as.vector(f_rel),4),
  Frec_rel_acum = round(cumsum(f_rel),4)
)
porcentajes<-c(tabla_satisf_carrera$Frec_Rel)
nombres<-c("Muy Satisfecho", "Satisfecho", "Insatisfecho", "Muy Insatisfecho")
pie(porcentajes,
    labels = nombres,
    col = rainbow(4),
    main = "Nivel de Satisfaccion"
    )