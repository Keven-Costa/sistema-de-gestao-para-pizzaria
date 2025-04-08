# Usa uma imagem base com OpenJDK (versão 17 neste exemplo)
FROM openjdk:21-jdk 

# Diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR para o contêiner (assumindo que o Maven/Gradle já gerou o JAR)
COPY target/pizzaria-0.0.1-SNAPSHOT.jar app.jar


# Comando para executar o aplicativo quando o contêiner iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]