-- Cria um Banco de Dados
create database dbinfox;
use dbinfox;
-- O bloco abaixo cria uma Tabela
create table tbusuarios (
	iduser int primary key,
    usuario varchar(50) not null,
    fone varchar(15),
    login varchar(15) not null unique,
    senha  varchar(15)not null
);

-- Descrevendo o nome
describe tbusuarios;
-- insere dados na tabela usuario
-- Create
insert into tbusuarios(iduser, usuario, fone, login, senha)
values (1, 'Eduardo Camargo', '6598152-0407', 'dudu', '123');
-- Linha abaixo exibe os dados
select * from tbusuarios;

insert into tbusuarios(iduser, usuario, fone, login, senha)
values (2, 'Administrador', '6598152-0407', 'admin', 'admin');

insert into tbusuarios(iduser, usuario, fone, login, senha)
values (3, 'Bill Gates', '6598152-0407', 'bill', '123');

-- A linha abaixo modifica dados da tabela (CRUD)
-- update

update tbusuarios set fone ='6599999-9999' where iduser=2;

-- A linha abaixo apaga um registro
-- Delete
delete from tbusuarios where iduser=3;

select * from tbusuarios;

create table tbclientes (
	idcli int primary key auto_increment,
    nomecli varchar(50)not null,
    endcli varchar(100),
    fonecli varchar(50) not null,
    email varchar(50)
);


select * from tbclientes;

-- Quando id é auto_increment, não preciso declarar ID
insert into tbclientes(nomecli, endcli, fonecli,
email) 

values('Linus Torvalds', 'Rua Tux, 2017', '9999-9999',
'linus@linux.com.br');


select * from tbclientes;

create table tbos (
	os int primary key auto_increment,
    data_os timestamp default current_timestamp,
    equipamento varchar(150) not null,
    defeito varchar(150) not null,
    servico varchar(150),
    tecnico varchar(30),
    valor decimal(10,2),
    idcli int not null,
    foreign key (idcli) references tbclientes(idcli)
);

describe tbos;

insert tbos(equipamento, defeito, servico, tecnico, valor, idcli)
values ('Notebook', 'não liga', 'troca da fonte', 'Zé', 87.50, 1);

select * from tbos;

-- Traz informações de duas tabelas (Crio uma variavel O
select
O.os, equipamento, defeito, servico, valor,
C.nomecli, fonecli
from tbos as O
inner join tbclientes as C
on (O.idcli = C.idcli);