# Sistema de gestão de documentos - Verde Gestão

Este é o repositório do MVP (Minimum Viable Product) do sistema **Verde Gestão**. O projeto foi desenvolvido utilizando **Java 17** e **PostgreSQL 17**.

## Requisitos

- **PostgreSQL 17**
- **Java 17**
- Uma cópia do **.jar** do projeto compilado.

## Como configurar o ambiente

### 1. Instalando o Java 17
Faça o download diretamente do [site oficial da Oracle](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) e instale o **Java 17** em sua máquina. A instalação pode ser feita normalmente, sem necessidade de configurações adicionais.

### 2. Instalando o PostgreSQL 17
Baixe o **PostgreSQL 17** diretamente do [site oficial](https://www.postgresql.org/download/).

Durante a instalação, configure o **usuário** e a **senha** como:
   - **Usuário:** postgres
   - **Senha:** admin

Após a instalação, o banco de dados estará pronto para ser configurado no projeto.

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
### Executando o sistema (Ambiente de Produção)

- 1. Clone o repositório do GitHub em sua máquina.
- 2. Acesse a pasta **release**, onde estará o arquivo **.jar** do projeto.
- 3. Crie uma nova database no **PGAdmin** (aplicativo administrativo do PostgreSQL):
   - **Nome da database:** VERDE_GESTAO
   - **Usuário:** postgres
   - **Senha:** admin
- 4. De o seguinte comando:
   ```
   java -jar verde_gestao.jar
   ```
- 5. Após a conclusão do comando, acesse o sistema no navegador utilizando o endereço:
   ```
   http://localhost:8080
   ```
   ou
   ```
   http://seuip:8080
   ```
