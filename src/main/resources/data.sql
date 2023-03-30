INSERT INTO utilisateur (`mot_de_passe`, `nb_parties_gagnees`, `nb_parties_jouees`, `pseudo`, `km_parcourus`, `peut_avancer`) VALUES ('blabla', 0, 0, 'test', 0, 0);
INSERT INTO utilisateur (`mot_de_passe`, `nb_parties_gagnees`, `nb_parties_jouees`, `pseudo`, `km_parcourus`, `peut_avancer`) VALUES ('KatzBG', 7, 6, 'Moumi', 0, 0);
INSERT INTO utilisateur (`mot_de_passe`, `nb_parties_gagnees`, `nb_parties_jouees`, `pseudo`, `km_parcourus`, `peut_avancer`) VALUES ('superMDP', 0, 0, 'John Rambo', 0, 0);
INSERT INTO utilisateur (`mot_de_passe`, `nb_parties_gagnees`, `nb_parties_jouees`, `pseudo`, `km_parcourus`, `peut_avancer`) VALUES ('DoggyBG', 5, 5, 'Midoune', 0, 0);

INSERT INTO `type_carte` (`id_type_carte`, `nom_type_carte`) VALUES (1, 'botte');
INSERT INTO `type_carte` (`id_type_carte`, `nom_type_carte`) VALUES (2, 'attaque');
INSERT INTO `type_carte` (`id_type_carte`, `nom_type_carte`) VALUES (3, 'parade');
INSERT INTO `type_carte` (`id_type_carte`, `nom_type_carte`) VALUES (4, 'kilometres');

INSERT INTO `carte` (`effet`, `km`, `nb_dispo`, `nom`, `type_carte_id_type_carte`) VALUES ('prioritaire', 0, 1, 'Véhicule prioritaire', 1);
INSERT INTO `carte` (`effet`, `km`, `nb_dispo`, `nom`, `type_carte_id_type_carte`) VALUES ('citerne', 0, 1, 'Citerne essence', 1);
INSERT INTO `carte` (`effet`, `km`, `nb_dispo`, `nom`, `type_carte_id_type_carte`) VALUES ('increvable', 0, 1, 'Increvable', 1);
INSERT INTO `carte` (`effet`, `km`, `nb_dispo`, `nom`, `type_carte_id_type_carte`) VALUES ('as', 0, 1, 'As du volant', 1);

INSERT INTO `carte` (`effet`, `km`, `nb_dispo`, `nom`, `type_carte_id_type_carte`) VALUES ('limite', 0, 4, 'Limitation de vitesse', 2);
INSERT INTO `carte` (`effet`, `km`, `nb_dispo`, `nom`, `type_carte_id_type_carte`) VALUES ('panne', 0, 3, 'Panne d essence', 2);
INSERT INTO `carte` (`effet`, `km`, `nb_dispo`, `nom`, `type_carte_id_type_carte`) VALUES ('crevaison', 0, 3, 'Crevaison', 2);
INSERT INTO `carte` (`effet`, `km`, `nb_dispo`, `nom`, `type_carte_id_type_carte`) VALUES ('accident', 0, 3, 'Accident', 2);
INSERT INTO `carte` (`effet`, `km`, `nb_dispo`, `nom`, `type_carte_id_type_carte`) VALUES ('rouge', 0, 5, 'Feu rouge', 2);

INSERT INTO `carte` (`effet`, `km`, `nb_dispo`, `nom`, `type_carte_id_type_carte`) VALUES ('finlimite', 0, 6, 'Fin de limitation de vitesse', 3);
INSERT INTO `carte` (`effet`, `km`, `nb_dispo`, `nom`, `type_carte_id_type_carte`) VALUES ('essence', 0, 6, 'Station essence', 3);
INSERT INTO `carte` (`effet`, `km`, `nb_dispo`, `nom`, `type_carte_id_type_carte`) VALUES ('roue', 0, 6, 'Roue de secours', 3);
INSERT INTO `carte` (`effet`, `km`, `nb_dispo`, `nom`, `type_carte_id_type_carte`) VALUES ('reparations', 0, 6, 'Réparations', 3);
INSERT INTO `carte` (`effet`, `km`, `nb_dispo`, `nom`, `type_carte_id_type_carte`) VALUES ('vert', 0, 14, 'Feu vert', 3);

INSERT INTO `carte` (`effet`, `km`, `nb_dispo`, `nom`, `type_carte_id_type_carte`) VALUES ('km', 25, 10, '25 Km', 4);
INSERT INTO `carte` (`effet`, `km`, `nb_dispo`, `nom`, `type_carte_id_type_carte`) VALUES ('km', 50, 10, '50 Km', 4);
INSERT INTO `carte` (`effet`, `km`, `nb_dispo`, `nom`, `type_carte_id_type_carte`) VALUES ('km', 75, 10, '75 Km', 4);
INSERT INTO `carte` (`effet`, `km`, `nb_dispo`, `nom`, `type_carte_id_type_carte`) VALUES ('km', 100, 12, '100 Km', 4);
INSERT INTO `carte` (`effet`, `km`, `nb_dispo`, `nom`, `type_carte_id_type_carte`) VALUES ('km', 200, 4, '200 Km', 4);

INSERT INTO `partie` (`id_partie`, `code_partie`, `duree_tour`, `nombre_joueurs`) VALUES ( 1, 'azerty', 30, 3 )