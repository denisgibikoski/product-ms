#Product-MS

Para rodar o projeto devera ter um banco Postgres com uma database com o nome de product_ms, o serviço esta configurado para rodar na porta 4562. Segue abaixo exemplo para realizar as requisições.

Endpoints
Verbo           HTTP	            Resource path	Descrição
POST	        /products	          Criação de um produto
PUT	          /products/{id}	        Atualização de um produto
GET	          /products/{id}        Busca de um produto por ID
GET	          /products	          Lista de produtos
GET	          /products/search	  Lista de produtos filtrados
DELETE	      /products/	        Deleção de um produto


Para realizar a requisição para cadastrar Produto devera emitir um evento do método Post nesta URI http://localhost:9999/products contendo no body um Objeto JSON contendo a sugestão de envio:  
{
    "name": "Tenis",
    "description": "Esport",
    "price": 200.00
  }

Para realizar a requisição para Atualizar Produto deverá emitir um evento do método Put nesta URI http://localhost:9999/products/{id} contendo no body um Objeto JSON contendo a sugestão de envio:

  {
    "name": "Sapato",
    "description": "Casual",
    "price": 159.52
  }

Para realizar consulta Produto pelo Id deverá emitir um evento do método Get nesta URI http://localhost:9999/products/{id} deverá ser passado o nome por parâmetro da uri como o exemplo e sem conteúdo no body.


Para realizar a consulta para todos os cadastros de Produtos pelo estado deverá emitir um evento do método Get nesta URI http://localhost:9999/products/ não deverá ser passado parâmetro na uri como o exemplo e sem conteúdo no body.


Para realizar a consulta Produtos com parametros pelo name ,description ou price deverá emitir um evento do método Get nesta URI http://localhost:9999/products/search deverá ser passado os seguintes parâmetro abaixo na uri como o exemplo e sem conteúdo no body.

 Query param	            Ação de filtro
      q	                        deverá bater o valor contra os campos name e description
    min_price	                  deverá bater o valor ">=" contra o campo price
    max_price	                  deverá bater o valor "<=" contra o campo price
    
    Exemplos 
    
  GET  http://localhost:9999/products/search?min_price=150.50&max_price=50&q=tenis
  
   GET  http://localhost:9999/products/search?q=tenis
   
    GET  http://localhost:9999/products/search?min_price=150.50&max_price=50
    
     GET  http://localhost:9999/products/search?min_price=150.50&q=tenis
     
      GET  http://localhost:9999/products/search?max_price=50&q=tenis
      
      
Para realizar remoção Produto deverá emitir um evento do método Delete nesta URIhttp://localhost:9999/products/{id} deverá ser passado o id por parâmetro da uri como o exemplo e sem conteúdo no body.

