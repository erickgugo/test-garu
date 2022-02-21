INSERT INTO STUDENT VALUES ('111AAA', 'ERICK', 'GUTIERREZ');
INSERT INTO STUDENT VALUES ('222BBB', 'HILDE', 'PLATA');
INSERT INTO STUDENT VALUES ('333CCC', 'DIEGO', 'MORALES');
INSERT INTO STUDENT VALUES ('444DDD', 'FERNANDO', 'CASTILLO');

INSERT INTO COURSE VALUES ('INF-111', 'INTRODUCCION A LA PROGRAMACION', 'Describe la carrera de la informatica');
INSERT INTO COURSE VALUES ('MAT-112', 'MATEMATICA DISCRETA', 'Matematica basica');
INSERT INTO COURSE VALUES ('FIS-123', 'FISICA I', 'Introduccion a la Fisica');


INSERT INTO REGISTRATION(code, student_id) VALUES ('INF-111', '111AAA');
INSERT INTO REGISTRATION(code, student_id) VALUES ('INF-111', '222BBB');
INSERT INTO REGISTRATION(code, student_id) VALUES ('MAT-112', '222BBB');
INSERT INTO REGISTRATION(code, student_id) VALUES ('MAT-112', '333CCC');
