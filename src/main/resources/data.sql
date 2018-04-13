
INSERT IGNORE INTO utilisateur (`id_utilisateur`, `nom`, `prenom`, `idRH`, `fonction`, `mdp`) VALUES ('1', 'Montgomery', 'Leroy', 'ABC123', '0', 'AZER');
INSERT IGNORE INTO utilisateur (`id_utilisateur`, `nom`, `prenom`, `idRH`, `fonction`, `mdp`) VALUES ('2', 'Mosley', 'Nola', 'CDE456', '1', 'QSDF');
INSERT IGNORE INTO utilisateur (`id_utilisateur`, `nom`, `prenom`, `idRH`, `fonction`, `mdp`) VALUES ('3', 'Gonzales', 'Wang', 'EZA789', '2', 'WXCV');
INSERT IGNORE INTO utilisateur (`id_utilisateur`, `nom`, `prenom`, `idRH`, `fonction`, `mdp`) VALUES ('4', 'Bullock', 'Ivana', 'DSQ321', '3', '1234');

INSERT IGNORE INTO bureau (`id_bureau`, `nom_bureau`, `code_regate`, `adresse`, `code_postal`,  `ville`, `telephone`) VALUES ('1', 'Bordeaux Victoire', '331230', '12 place de la Victoire', '33123', 'Bordeaux', '0556567890');
INSERT IGNORE INTO bureau (`id_bureau`, `nom_bureau`, `code_regate`, `adresse`, `code_postal`,  `ville`, `telephone`) VALUES ('2', 'Libourne Principal', '332340', '25 boulevard de la maire', '33543', 'Libourne', '0557678905');
INSERT IGNORE INTO bureau (`id_bureau`, `nom_bureau`, `code_regate`, `adresse`, `code_postal`,  `ville`, `telephone`) VALUES ('3', 'Estaque', '133450' , '86 traverse de la colline', '13015', 'Marseille', '0491567890');
INSERT IGNORE INTO bureau (`id_bureau`, `nom_bureau`, `code_regate`, `adresse`, `code_postal`,  `ville`, `telephone`) VALUES ('4', 'Simplon', '757890', '55 rue de Vincenne', '93100', 'Montreuil', '0123456789');

INSERT IGNORE INTO materiel (`id_materiel`, `domaine`, `type`, `marque`, `modele`, `numero_serie`, `code_parc`, `code_article`, `date_fin_garantie`) VALUES ('1', '0', 'imprimante', 'Lexmark', '360dn', '8K345876M13', 'A12345', '123456', '2016-08-30 19:05:00');
INSERT IGNORE INTO materiel (`id_materiel`, `domaine`, `type`, `marque`, `modele`, `numero_serie`, `code_parc`, `code_article`, `date_fin_garantie`) VALUES ('2', '1', 'routeur', '3COM', 'ER25', '65433HY8', 'B32154', '234321','2015-04-12 19:05:00');
INSERT IGNORE INTO materiel (`id_materiel`, `domaine`, `type`, `marque`, `modele`, `numero_serie`, `code_parc`, `code_article`, `date_fin_garantie`) VALUES ('3', '4', 'caméra' , 'Bosch', 'GAC15', '1235TYU678', 'C43212', '432123','2017-09-21 19:05:00');
INSERT IGNORE INTO materiel (`id_materiel`, `domaine`, `type`, `marque`, `modele`, `numero_serie`, `code_parc`, `code_article`, `date_fin_garantie`) VALUES ('4', '3', 'tourelle', 'Wincord', 'gauche', 'TG436','D54321', '346789','2019-09-21 19:05:00');