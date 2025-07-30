# Verde Gestão - MVP

Este é o repositório do MVP (Minimum Viable Product) do sistema **Verde Gestão**. O projeto foi desenvolvido utilizando **Java 17** e **PostgreSQL 17**, e pode ser executado no **Tomcat 9.0**.

## Requisitos

- **PostgreSQL 17**
- **Java 17**
- **Tomcat 9.0**
- Uma cópia do **.war** do projeto compilado (opcional, caso possua o **IntelliJ IDEA**).

## Como configurar o ambiente

### 1. Instalando o Java 17
Faça o download diretamente do [site oficial da Oracle](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) e instale o **Java 17** em sua máquina. A instalação pode ser feita normalmente, sem necessidade de configurações adicionais.

### 2. Instalando o PostgreSQL 17
Baixe o **PostgreSQL 17** diretamente do [site oficial](https://www.postgresql.org/download/).

Durante a instalação, configure o **usuário** e a **senha** como:
   - **Usuário:** postgres
   - **Senha:** admin

Após a instalação, o banco de dados estará pronto para ser configurado no projeto.

### 3. Executando o MVP com IntelliJ IDEA (Ambiente de Desenvolvimento)

Caso possua o **IntelliJ IDEA**: 
- 1. Clone o repositório do GitHub em sua máquina.

- 2. Abra o IntelliJ IDEA e importe o repositório como um projeto.

- 3. Espere a IDE indexar todos os arquivos e dependências do projeto.

Execute a seguinte ação: "Ctrl + Shift + A" e selecione **Refresh Gradle Dependencies** (ou equivalente, dependendo da versão do IntelliJ).

### Configure as informações do banco de dados no arquivo: `SGD_verde_gestao\verde_gestao_ws\src\main\resources\application.properties`

- 1. Crie uma nova database no **PGAdmin** (aplicativo administrativo do PostgreSQL):
   - **Nome da database:** VERDE_GESTAO
   - **Usuário:** postgres
   - **Senha:** admin

- 2. Preencha com as credenciais do PostgreSQL:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/VERDE_GESTAO
   spring.datasource.username=postgres
   spring.datasource.password=admin
   ```
- 3. Clique no botão **play** na IDE para iniciar o servidor.

- 4. Acesse o sistema no navegador de sua preferência utilizando o seguinte endereço: 
   ```
   http://localhost:8080
   ```
(O número da porta pode variar conforme a configuração do Tomcat embutido no IntelliJ IDEA).

### Executando o MVP sem IntelliJ IDEA (Ambiente de Produção)

Caso não possua o **IntelliJ IDEA**:

- 1. Clone o repositório do GitHub em sua máquina.

- 2. Acesse a pasta **release**, onde estará o arquivo **.war** do projeto.

- 3. Copie o arquivo **.war** para dentro da pasta do **Tomcat 9.0** (geralmente dentro da pasta `webapps`).

- 4. Crie uma nova database no **PGAdmin** (aplicativo administrativo do PostgreSQL):
   - **Nome da database:** VERDE_GESTAO
   - **Usuário:** postgres
   - **Senha:** admin
- 5. Inicie o **Tomcat 9.0** e, após a conclusão, acesse o sistema no navegador utilizando o endereço:
   ```
   http://localhost:8080
   ```
(O número da porta pode variar conforme a configuração do Tomcat).

---