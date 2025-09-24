#fijamos la semilla para obtener los mismos resultados
set.seed(123)

#generamos los dat os: 47 tiempos simulados en minutos
tiempos<- rnorm(47, mean = 55, sd=15)

#reemplazamos valores negativos por 0
tiempos<- ifelse(tiempos<0, 0, tiempos)

#mostrar datos simulados
tiempos

#cantidad total de observaciones
n<- length(tiempos)

#numero de clases segun la regla de sturges
k<-ceiling(1+3.322 *log10(n))
k
rango<- range(tiempos)
amplitud<- ceiling((rango[2]-rango[1])/k)
rango
amplitud

breaks<- seq(floor(rango[1]), ceiling(rango[2])+amplitud, by = amplitud)
clases<- cut(tiempos, breaks = breaks, right = FALSE)
head(clases)

tabla_tiempos<-table(clases)
f_acum<- cumsum(tabla_tiempos)
f_rel<- prop.table(tabla_tiempos)
f_rel_acum<- cumsum(f_rel)

tabla_final<- data.frame(
  Intervalo = levels(clases),
  Frecuencia = as.vector(tabla_tiempos),
  Frec_acumulada = as.vector(f_acum),
  Frec_relativa = round(as.vector(f_rel),3),
  Frec_rel_acum = round(as.vector(f_rel_acum),3)
)
tabla_final