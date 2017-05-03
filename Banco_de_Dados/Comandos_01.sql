-- Cria um Banco de Dados
create database dbinfox;
use dbinfox;
-- O bloco abaixo cria uma Tabela
create table tbusuarios (
	iduser int primary key,
    usuario varchar(50) not null,
    fone varchar(15),
    login varchar(15) not null unique,
    senha  varchar(15)not null,
    perfil varchar(15) not null
);
-- Descrevendo o nome
describe tbusuarios;
-- insere dados na tabela usuario
-- Create
insert into tbusuarios(iduser, usuario, fone, login, senha,perfil)
values (1, 'Uninove', '6598152-0890', 'uninove', '123','user');
-- Linha abaixo exibe os dados
select * from tbusuarios;

insert into tbusuarios(iduser, usuario, fone, login, senha,perfil)
values (2, 'Administrador', '6598152-0407', 'admin', 'admin','admin');

insert into tbusuarios(iduser, usuario, fone, login, senha,perfil)
values (3, 'Bill Gates', '6598152-0407', 'bill', '123','user');

-- A linha abaixo modifica dados da tabela (CRUD)
-- update

-- update tbusuarios set fone ='6599999-1111' where iduser=4;
update tbusuarios set senha ='123' where iduser=3;

-- A linha abaixo apaga um registro
-- Delete
 delete from tbusuarios where iduser=3;

select * from tbusuarios;
select * from tbclientes;
desc tbclientes;

create table tbclientes (
	idcli int primary key auto_increment,
    nomecli varchar(50)not null,
    endcli varchar(100),
    fonecli varchar(50) not null,
    emailcli varchar(50)
);

/*select * from tbclientesSITE;
create table tbclientesSITE (
	idcli int primary key auto_increment,
    nomesite varchar(50)not null,
    cepsite varchar(100),
    cidadesite varchar(50) not null,
    emailcli varchar(50),
    emailsite varchar(50)
);*/


select * from tbclientes;

insert into tbclientes(nomecli, endcli, fonecli,
email) 

values('Jose Torvalds', 'Rua Tux, 2017', '9999-9999',
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