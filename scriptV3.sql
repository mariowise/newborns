INSERT INTO accounttype(
            id, name)
    VALUES (1, 'admin'),
    (2, 'matrona'),
    (3, 'medico'),
    (4, 'fonoaudiologo');

INSERT INTO account(
            rut, email, password, phone, accounttype_id, name)
    VALUES ('104717888', 'admin@usach.cl', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', '21234567', 1, 'Maria'),
         ('164377377', 'matrona@usach.cl', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', '21321321', 2, 'Jorge'),
         ('62089237', 'medico@usach.cl', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', '23897231', 3, 'Juana'),
         ('126432631', 'fonoaudiologo@usach.cl', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', '24658920', 4, 'Esteban');

INSERT INTO schedulingstate(id, state)
    VALUES (1, 'Agendado'), (2, 'Asistido'), (3, 'Postergado');

INSERT INTO deliverytype(id, name)
    VALUES (1, 'Normal'), (2, 'Cesarea'), (3, 'Forceps'), (4, 'Podálico'), (5, 'Fuera del Servicio');

CREATE OR REPLACE VIEW accounts AS 
 SELECT account.rut,
    account.password,
    accounttype.name AS rolename
   FROM account
   JOIN accounttype ON account.accounttype_id = accounttype.id;

