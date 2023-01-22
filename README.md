# Curso Softblue Real Meet

## Repositório do Curso

https://github.com/ctosin/realmeet

## Rodando os testes mutantes

`mvn clean install org.pitest:pitest-maven:mutationCoverage`

## Rodando o plugin do Prettier

Se por acaso a aplicação não estiver formatada, dando o seguinte erro:

Vá até o painel Maven >> realmeet-service >> plugins >> prettier
Dois cliques em prettier:check
Dois cliques em prettier:write

Depois é só rodar o comando `mvn clean install` novamente