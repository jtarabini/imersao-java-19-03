# JPQL (*Java Persistence Query Language*)
## Case (In)sensitive
Com exceção de nomes de classes e propriedades, queries são case-insensitive, ou seja SELECT produz o mesmo efeito de Select, select ou sElEcT, mas br.com.targettrutst.trac.entidades.Usuario a br.com.targettrutst.trac.entidades.usuario são diferentes.

**Observação** - Por questão de padronização todas as queries documentadas nos exemplos abaixo usam *lowercase*.

## Statement types
Amos SQL e JPQL permitem os comandos SLECT, UPDATE e DELETE, sendo o INSERT o único comando não suportado pelo JPQL. 

### Select
Selects seguem o seguinte padrão:

```
        [select_clause]
        from_clause
        [where_clause]
        [groupby_clause]
        [having_clause]
        [orderby_clause]
```

Conforme podemos verificar o único componente obrigatório é o *from_clause*, sendo assim o select mais simples possível seria:

```
from Usuario
```
### Update
```
update_statement ::= update_clause [where_clause]

update_clause ::= UPDATE entity_name [[AS] identification_variable]
        SET update_item {, update_item}*

update_item ::= [identification_variable.]{state_field | single_valued_object_field}
        = new_value

new_value ::= scalar_expression |
                simple_entity_expression |
                NULL
```
### Delete
```
delete_statement ::= delete_clause [where_clause]

delete_clause ::= DELETE FROM entity_name [[AS] identification_variable]
```

## A claúsula FROM
A claúsula **FROM** é responsável por definir o escopo de objetos disponíveis para o resto da query e também deve definir todas as "variáveis de identificação" disponíveis a todos os elementos da query.

```
select ususario from Usuario usuario
```

## Joins explícitos
A claúsula **FROM** ainda pode conter joins explícitos de relacionamentos utilizando a palavra chave **join**. Esses joins podem ser *inner* ou *left outer*. Ex.:

```
select carro 
  from Carro carro
  join carro.equipamentos equipamento  
```
** Retorna todos os carros que possuem algum equipamento**

```
select carro 
  from Carro carro
  left outer join carro.equipamentos equipamento  
```
** Retorna todos os carros mesmo que **sem** equipamento

## Joins implícitos
Todos os relacionamentos das classes estão disponíveis para serem utilizados na query de forma implícita, conforme exemplo abaixo:
```
select carro 
  from Carro carro
 where carro.marca.nome = 'FORD'  
```
** Se no nosso exemplo tivessemos uma entidade **Marca** (modelo normalizado) poderíamos acessar seus atributos em uma query dessa forma.**

## Polimorfismo
Todas as sub-classes estão disponíveis a consulta, logo que se desejamos uma lista de todos os Carros podemos fazer:
```
select carro from Carro carro
```
que seria executado o seguinte sql:
```SQL
select * from tb_veiculo where tipo = 'CARRO'
```

## Funções agregadoras
Funções agregadoras geralmente disponíveis em SQL (ansi) também estão disponíveis no JPQL, exemplos:
* **COUNT** - (inclundo distinct) - O tipo do resultado é sempre um Long.
* **AVG** - O resultado é sempre Double.
* **MIN** - O resultado é sempre o mesmo do tipo de argumento.
* **MAX** - O resultado é sempre o mesmo do tipo de argumento.
* **SUM** - O resultado é sempre o mesmo tipo do argumento.

** O conteúdo aqui apresentado é apenas um resumo. Para uma visão mais global de todas a JPQL acesse a  
[documentação](http://docs.jboss.org/hibernate/orm/4.3/devguide/en-US/html/)  



