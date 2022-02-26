Neste desafio estarei usando Java com o framework Spring boot + thymeleaf com o MySql com Xamp como meu banco de dados, o motivo em usar esse framework seria por eu estar mais confortável em trabalhar com ela e o mySql com o xamp por causa da facilidade de usar e ser bem leve para testes pequenos.


No começo do desenvolvimento, eu foquei mais na parte do usuário e suas funcionalidades, já que a maioria delas eu poderia reaproveitar os códigos na hora de criar a parte do SKU/local. Os usuários, gerentes e admin podem criar um SKU e editar eles, mas apenas os gerente e admin tem a permissão de aprova-los.

Primeiro devemos configurar nosso MySql, criando ele com o nome Orion na porta 3306, apenas configurando isso o hibernate ira criar as tabelas automaticamente ![image](https://user-images.githubusercontent.com/26257093/155835316-42320cf0-f587-43f8-bbcf-22daaa71174a.png)

Caso queira mudar a porta usada para o sql voce pode alterar o valor da porta no application.properties ![image](https://user-images.githubusercontent.com/26257093/155835433-87debc6f-d082-4926-a5ed-74cbb3f9f5f5.png)

Agora podemos dar inicio ao nosso aplicativo, quando começar rodar o OrionApplication.java ira inicializar, entre no localhost:8080 para começar a usar ele

Na página de registro, todos os campos são obrigatórios, no campo do Usuário e Email  não pode ter eles repetido no banco de dados,  no campo da senha tem um verificador onde é necessário ter 12 caracteres, 2 minúsculos, 2 maiúsculos, 2 especiais, e sem repetir o mesmo caracteres por 3 vezes sequencialmente e finalmente no RUT tem que ter um RUT valido. 

Aqui podemos pegar algums RUT validos para fazer os testes http://jqueryrut.sourceforge.net/generador-de-ruts-chilenos-validos.html

Ao registrar sua conta, o usuário sera apenas um usuário comum, caso queira aumentar o privilégio dele, tera que entrar na conta admin/admin para poder aumentar o cargo dele.

Aqui esta a lista de espera, onde voce pode registrar um novo local
![image](https://user-images.githubusercontent.com/26257093/155836492-88a7160a-584d-40cc-aa39-5e8c1dd60b8d.png)


Nesta página tem um validador onde o número de jogos tem que ter a mesma quantidade de jogos, e caso o contrato seja de único jogo, só poderá escolher um jogo e os valores não estiverem certos também  acionara o validador.
![image](https://user-images.githubusercontent.com/26257093/155836051-18ac67f8-3c53-40d4-918d-302816badc9e.png)
![image](https://user-images.githubusercontent.com/26257093/155836133-632849ea-1cde-4365-8269-72fb61f62b02.png)
![image](https://user-images.githubusercontent.com/26257093/155836166-eb893458-d431-41cf-af9a-35f36dcd5408.png)

Assim que o registro for completo, voltaremos para a pagina da lista de espera, como a conta utilizada é apenas um usuario comum ele nao pode aprovar a lista, para isso devemos 
aumentar o cargo dele
![image](https://user-images.githubusercontent.com/26257093/155836674-235caad4-b9bd-4163-8b08-f2c412795369.png)

![image](https://user-images.githubusercontent.com/26257093/155836214-0c5f5f5a-0a9d-484a-a31d-30da6646aa07.png)
![image](https://user-images.githubusercontent.com/26257093/155836216-18a2bce1-3362-471d-95a5-45217fb02e26.png)
![image](https://user-images.githubusercontent.com/26257093/155836223-477309d4-e9f4-4876-b8fa-aa0bf62c39dc.png)

Agora que ele tem o cargo de manager, ele tem acesso a pagina de aprovar o local
![image](https://user-images.githubusercontent.com/26257093/155836264-05b2ae7f-fb21-4fed-9684-80ea2553005c.png)

Onde podera dar uma ultima olhada nos dados antes de aprovar
![image](https://user-images.githubusercontent.com/26257093/155836278-2c0d4f88-d2f9-4897-b455-e176ed98e8ab.png)

Agora que ele esta aprovado, ele vai estar na pagina de locais aprovados
![image](https://user-images.githubusercontent.com/26257093/155836705-d2bcef9d-726e-4dc0-8c0c-6532c643a537.png)
![image](https://user-images.githubusercontent.com/26257093/155836708-696f7cd3-724a-4c9d-86cd-cfd9eafe650e.png)

Caso ainda precisa editar o valor do local aprovado, apos editar os valores dele, ele voltara para a lista de espera para ser aprovado novamente. Para evitar em apertar no deletar acidentalmente, aparecera um modal para confirmar.
![image](https://user-images.githubusercontent.com/26257093/155836753-bd20862f-adee-4ebe-9df2-c14128e26483.png)




O codigo SKU ser divido da seguinte forma:

US/MX/CH - Distribuidora

EN/ES - Lingua

JM/UJ - Contrato- jogo único ou jogos múltiplos, como que a porcentagem pode ser baseada no contrato ela não precisa aparecer no código SKU

01-10 - Numero de Placas

01-05 - Numero de Jogos

H/V/E/N/L/T/D - A quantidade pode variar dependendo do número de jogos com o limite de 5 jogos.

"Halloween", "Valentine's day", "Easter Sunday", "New Year", "Lunar New Year", "Thanksgiving", "Día de Muertos"

1xxxxxxxxx1- O número é baseado no Unix epoch do tempo cadastrado, ou seja, os números nunca vão se repetir.

O unix vai servir como um identificador para achar os dados do Local e Cidade no banco de dados


Exemplo Sku:

USENJM0902HL1645580326

Distribuidor- Estados Unidos

Lingua - Ingles

Contrato - Multiplo jogos

Porcentagem - 30%

Quantidade de placas - 9

Quantidade de jogos - 2

Jogos -  Halloween, Lunar new eve

Local - rua natal n20

Cidade - Natal



