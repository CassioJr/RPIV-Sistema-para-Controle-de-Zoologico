--Script para criar a tabela de tratamento
CREATE TABLE tratamento(
    id serial,
    dataentrada varchar(100),
    horarioentrada varchar(100),
    nome varchar(100),
    idade int,
    especie varchar(200),
    sexo varchar(50),
    numeroabrigo int,
    motivointernacao varchar(500),
    situacao varchar(500),
    procedimento varchar(500),
    evolucaoquadro varchar(500),
    resultados varchar(500),
    motivoconsulta varchar(500),
    tratamento varchar(500),
    resultadosatendimento varchar(500),
    vacinacao varchar(500)
);

--Script para criar a tabela de funcionario 
CREATE TABLE funcionario(
    id serial,
    nome varchar(100),
    dtAdmissao varchar(100),
    dtSaida varchar(100),
    mtvSaida varchar(100),
    funcao varchar(100),
    endereco varchar(100),
    telefone float,
    salario float,
); 

--Script para criar a tabela de animal
CREATE TABLE animal(
id serial, 
nomeAnimal varchar(50),
sexoAnimal varchar(50),
idadeAnimal int,
tipoTransferencia varchar(200),
instituicaoOrigem varchar(200),
instituicaoDestino varchar(200),
estadoSaude varchar(200),
nomeDoenca varchar(200),
nomeEspecie varchar(200),
habitatEspecie varchar(200),
numeroAbrigo int,
localizacaoAbrigo varchar(200),
tamanhoAbrigo float,
nomeAlimento varchar(200),
quantidadeDiariaAlimento float,
medidaQuantidadeAlimento varchar(200),
dataTransferencia varchar(200),
consultando boolean);

--Criar tabela login com id com prefixo
CREATE SEQUENCE seq_prefixo;

CREATE TABLE login(
    id TEXT NOT NULL DEFAULT '2021'||to_char(nextval('seq_prefixo'::regclass), 'FM0000'),
    nome varchar(200),
    senha varchar(200),
    funcao varchar(200)
);

--Comando para inserir um login de permissao de gerente
INSERT INTO login (nome,senha,funcao) VALUES ('Gerente',1234,'Gerente');

--comando para inserir a tabela fornecedor
CREATE TABLE fornecedor(
    id serial,
    nomefor varchar(100),
    endfor varchar(100),
    telfor float
);