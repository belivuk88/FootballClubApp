INSERT INTO `user` (id, username, password, role)
              VALUES (1,'miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','ADMIN');
INSERT INTO `user` (id, username, password, role)
              VALUES (2,'tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','KORISNIK');
INSERT INTO `user` (id, username, password, role)
              VALUES (3,'petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','KORISNIK');
              
INSERT INTO klub (id, naziv, budzet) VALUES (1, 'Juventus', 10000000);
INSERT INTO klub (id, naziv, budzet) VALUES (2, 'Fk Crvena Zvezda', 5000000);
INSERT INTO klub (id, naziv, budzet) VALUES (3, 'Partizan', 3000000);

INSERT INTO igrac (id, ime_prezime, pozicija, broj_dresa, datum_rodjenja, na_prodaju, klub_id) VALUES (1, 'Cristiano Ronaldo', 'Napadac', 7, '1985/02/05', false , 1);
INSERT INTO igrac (id, ime_prezime, pozicija, broj_dresa, datum_rodjenja, na_prodaju, klub_id) VALUES (2, 'Zoran Popović', 'Golman', 1, '1988/05/28', true , 2);
INSERT INTO igrac (id, ime_prezime, pozicija, broj_dresa, datum_rodjenja, na_prodaju, klub_id) VALUES (3, 'Filip Kljajić', 'Golman', 12, '1990/05/28', false , 3);
INSERT INTO igrac (id, ime_prezime, pozicija, broj_dresa, datum_rodjenja, na_prodaju, klub_id) VALUES (4, 'Zoran Tošić', 'Vezni', 8, '1987/04/28', false , 3);
INSERT INTO igrac (id, ime_prezime, pozicija, broj_dresa, datum_rodjenja, na_prodaju, klub_id) VALUES (5, 'Giorgio Chiellini', 'Odbrambeni', 10, '1985/02/05', true , 1);

INSERT INTO transfer (id, igrac_id, klub_id, cena) VALUES (1, 1, 2, 7000000);
INSERT INTO transfer (id, igrac_id, klub_id, cena) VALUES (2, 2, 1, 2000000);
INSERT INTO transfer (id, igrac_id, klub_id, cena) VALUES (3, 3, 2, 3000000);
INSERT INTO transfer (id, igrac_id, klub_id, cena) VALUES (4, 4, 1, 2000000);
INSERT INTO transfer (id, igrac_id, klub_id, cena) VALUES (5, 5, 3, 1000000);
