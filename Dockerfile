# Use a imagem base com o Tomcat e o Java instalados
FROM tomcat:9.0-jre11

# Diretório de trabalho dentro do contêiner
WORKDIR /usr/local/tomcat/webapps

# Não é necessário copiar o arquivo WAR no momento da construção

# Exponha a porta que o Tomcat estará ouvindo
EXPOSE 8080

# Comando para iniciar o Tomcat quando o contêiner for iniciado
CMD ["catalina.sh", "run"]