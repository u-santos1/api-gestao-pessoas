# Nova base (Eclipse Temurin - Padrão de Mercado)
FROM eclipse-temurin:17-jdk-alpine
# 2. Pasta de trabalho: Criamos uma pasta dentro do container
WORKDIR /app

# 3. Cópia: Pegamos o arquivo .jar que o Maven gerou e jogamos dentro do container
# (O asterisco *.jar serve para pegar qualquer nome de arquivo gerado)
COPY target/*.jar app.jar

# 4. Porta: Avisamos que o app roda na porta 8080
EXPOSE 8080

# 5. Ação: O comando que faz o app iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]