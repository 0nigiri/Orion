Neste desafio estarei usando Java com o framework Spring boot + thymeleaf com o MySql como meu banco de dados, o motivo em usar esse framework seria por eu estar mais confortável em trabalhar com ela. 

Primeiramente ao entrar no localhost:8080, você entrara na página home onde você tera a opção de logar ou registrar uma conta nova. Ao criar iniciar o aplicativo, o springboot ira criar um usuario admin com a senha admin, usuarios que tem a autorizacao como admin tem o poder de ver a lista de todos os usuarios e editar as autorizacoes deles, no caso como um usuario comum e gerente, e deletar eles.

Na página de registro, todos os campos são obrigatórios, no campo do Usuário e Email  não pode ter eles repetido no banco de dados,  no campo da senha tem um verificador onde é necessário ter 12 caracteres, 2 minúsculos, 2 maiúsculos, 2 especiais, e sem repetir o mesmo caracteres por 3 vezes sequencialmente e finalmente no RUT tem que ter um RUT valido.

No começo do desenvolvimento, eu foquei mais na parte do usuário e suas funcionalidades, já que a maioria delas eu poderia reaproveitar os códigos na hora de criar a parte do SKU. Os usuários, gerentes e admin podem criar um SKU e editar eles, mas apenas os gerente e admin tem a permissão de aprova-los.

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
