<h2>Setup</h2>
<li>Utilizar java 15</li>
<li>Tenha o docker previamente instalado na sua máquina</li>
<li>Você deve ter o mavem previamento instalado na sua máquina</li>

<li> <a href="docker/docker-compose.yml">  Senhas dos bancos e rabbit estão no docker compose</a></li>
<li>Windows sem o hyperv não irão funcionar em localhost, utilizar o IP da VM no arquivo <a href="assembleia-api/src/main/resources/application.yml">application para banco e mensageria</a></li>
<li>Para rodar o banco e rabbit execute o seguinte comando na raiz do projeto</li>
<code>
cd docker && docker-compose up
</code>

<li>abrir no draw.io 
    <a href="https://drive.google.com/file/d/1he_W6Iim2sO9VlEE2qgSrvAjVIAE1-lj/view?usp=sharing">
        <label>       
            Modelo ER    
        </label>
    </a>
</li>
<li>Na Raiz do projeto <code>mvn clean install</code></li>

<h2>Aplicação</h2>
<li>Rodar o spring na sua IDE, apenas a mensageria e o banco estão no docker</li>
<li>Utilizar branch master</li>
<li>
    <a>http://localhost:8080/api/assembleia/swagger-ui/index.html</a>
</li>
<li>Rodar primeiro a api para depois rodar o batch</li>
<li>Ambos com profile default</li>


<h3 align="left">Languages and Tools:</h3>
<p align="left"> 
    <a href="https://www.java.com" target="_blank"> 
        <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a> <a href="https://www.linux.org/" target="_blank"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/linux/linux-original.svg" alt="linux" width="40" height="40"/> </a> <a href="https://www.mysql.com/" target="_blank"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mysql/mysql-original-wordmark.svg" alt="mysql" width="40" height="40"/> </a> <a href="https://www.rabbitmq.com" target="_blank"> <img src="https://www.vectorlogo.zone/logos/rabbitmq/rabbitmq-icon.svg" alt="rabbitMQ" width="40" height="40"/> 
    </a> 
</p>



