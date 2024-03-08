package org.example.domain;

import org.example.service.UtilizatorService;
import org.example.service.PrietenieService;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class UserInterface {
    private UtilizatorService utilizatorService;
    private PrietenieService prietenieService;

    public UserInterface(UtilizatorService utilizatorService, PrietenieService prietenieService) {
        this.utilizatorService = utilizatorService;
        this.prietenieService = prietenieService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("--------------Meniu-------------------");
            System.out.println("1. Adaugă utilizator.");
            System.out.println("2. Șterge utilizator.");
            System.out.println("3. Adaugă prieten.");
            System.out.println("4. Șterge prieten.");
            System.out.println("5. Afișare număr de comunități.");
            System.out.println("6. Afișare cea mai sociabilă comunitate.");
            System.out.println("7. Afisare lista de utilizatori.");
            System.out.println("8. Cautare utilizator dupa id.");
            System.out.println("9. Afisare lista de prieteni.");
            System.out.println("0. Ieșire.");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consumă newline

            switch (option) {
                case 1:
                    // Implementați adăugarea unui utilizator
                    System.out.println("Introduceți numele utilizatorului: ");
                    String firstName = scanner.nextLine();

                    System.out.println("Introduceți prenumele utilizatorului: ");
                    String lastName = scanner.nextLine();

                    // Creați un obiect Utilizator cu datele introduse
                    Utilizator utilizator = new Utilizator(firstName, lastName);

                    // Apelați metoda de adăugare a utilizatorului
                    Optional<Utilizator> rezultatAdaugare = utilizatorService.addUser(utilizator);
                    if (rezultatAdaugare.isPresent()) {
                        System.out.println("Utilizator adăugat cu succes.");
                    } else {
                        System.out.println("Nu s-a putut adăuga utilizatorul.");
                    }
                    break;
                case 2:
                    // Implementați ștergerea unui utilizator
                    //daca un utilizator este sters se vor sterge si prieteniile acestuia

                    System.out.println("Introduceți id-ul utilizatorului: ");
                    Long idUtilizator = scanner.nextLong();
                    scanner.nextLine(); // Consumă newline
                    Optional<Utilizator> utilizatorDeSters = utilizatorService.findEntityByID(idUtilizator);
                    if (utilizatorDeSters.isPresent()) {
                        Iterable<Prietenie> prietenii = prietenieService.findAllEntities();
                        for (Prietenie prietenie : prietenii) {
                            if (prietenie.getId().getLeft().equals(idUtilizator) || prietenie.getId().getRight().equals(idUtilizator)) {
                                prietenieService.deleteEntity(prietenie.getId());
                            }
                        }
                        Optional<Utilizator> rezultatStergere = utilizatorService.deleteEntity(utilizatorDeSters.get().getId());
                        if (rezultatStergere.isPresent()) {
                            System.out.println("Utilizator șters cu succes.");
                        } else {
                            System.out.println("Nu s-a putut șterge utilizatorul.");
                        }
                    } else {
                        System.out.println("Nu există utilizatorul cu id-ul " + idUtilizator);
                    }
                    break;
                case 3:
                    // Implementați adăugarea unui prieten
                    System.out.println("Introduceți id-ul primului utilizator: ");
                    Long id1 = scanner.nextLong();
                    scanner.nextLine(); // Consumă newline
                    System.out.println("Introduceți id-ul celui de-al doilea utilizator: ");
                    Long id2 = scanner.nextLong();
                    scanner.nextLine(); // Consumă newline
                    Tuple<Long, Long> idPrietenie = new Tuple<>(id1, id2);
                    Prietenie prietenie = new Prietenie();
                    prietenie.setId(idPrietenie);
                    Optional<Prietenie> rezultatAdaugarePrietenie = prietenieService.addUser(prietenie);
                    if (rezultatAdaugarePrietenie.isPresent()) {
                        System.out.println("Prietenie adăugată cu succes.");
                    } else {
                        System.out.println("Nu s-a putut adăuga prietenia.");
                    }
                    break;
                case 4:
                    // Implementați ștergerea unui prieten
                    System.out.println("Introduceți id-ul primului utilizator: ");
                    Long idPrimulUtilizator = scanner.nextLong();
                    scanner.nextLine(); // Consumă newline
                    System.out.println("Introduceți id-ul celui de-al doilea utilizator: ");
                    Long idAlDoileaUtilizator = scanner.nextLong();
                    scanner.nextLine(); // Consumă newline
                    Tuple<Long, Long> idPrietenieDeSters = new Tuple<>(idPrimulUtilizator, idAlDoileaUtilizator);
                    Prietenie prietenieDeSters = new Prietenie();
                    prietenieDeSters.setId(idPrietenieDeSters);
                    Optional<Prietenie> rezultatStergerePrietenie = prietenieService.deleteEntity(prietenieDeSters.getId());
                    if (rezultatStergerePrietenie.isPresent()) {
                        System.out.println("Prietenie ștearsă cu succes.");
                    } else {
                        System.out.println("Nu s-a putut șterge prietenia.");
                    }
                    break;
                case 5:
                    // Implementați afișarea numărului de comunități
                    //numarul de comunitati este egal cu numarul de componente conexe din graful retelei
                    //pentru aceasta se va construi un graf bidirectional si se va face un dfs pe acesta
                    //numarul de componente conexe este egal cu numarul de comunitati
                    int nrComunitati = 0;
                    Iterable<Utilizator> utilizatori = utilizatorService.findAllEntities();
                    for(Utilizator ignored : utilizatori)
                        nrComunitati++;
                    System.out.println("Numarul de utilizatori este: " + nrComunitati);
                    GrafRetele grafRetele = new GrafRetele(nrComunitati+1);
                    /*utilizatori.forEach(utilizator->);
                    for(Utilizator utilizator1:utilizatori)
                    {
                        for (Utilizator utilizator2:utilizatori)
                        {
                            if(!Objects.equals(utilizator1.getId(), utilizator2.getId()))
                            {
                                Iterable<Prietenie> prietenii = prietenieService.findAllEntities();
                                for (Prietenie prietenie5 : prietenii) {
                                    if (prietenie5.getId().getLeft().equals(utilizator1.getId()) && prietenie5.getId().getRight().equals(utilizator2.getId())||prietenie5.getId().getLeft().equals(utilizator2.getId()) && prietenie5.getId().getRight().equals(utilizator1.getId())) {
                                    grafRetele.adaugaMuchie(utilizator1.getId().intValue(), utilizator2.getId().intValue());
                                    grafRetele.adaugaMuchie(utilizator2.getId().intValue(), utilizator1.getId().intValue());
                                    }
                                }
                            }
                        }
                    }*/
                    utilizatori.forEach(utilizator1 -> {
                        utilizatori.forEach(utilizator2 -> {
                            if (!Objects.equals(utilizator1.getId(), utilizator2.getId())) {
                                Iterable<Prietenie> prietenii = prietenieService.findAllEntities();
                                prietenii.forEach(prietenie5 -> {
                                    if ((prietenie5.getId().getLeft().equals(utilizator1.getId()) && prietenie5.getId().getRight().equals(utilizator2.getId())) ||
                                            (prietenie5.getId().getLeft().equals(utilizator2.getId()) && prietenie5.getId().getRight().equals(utilizator1.getId()))) {
                                        grafRetele.adaugaMuchie(utilizator1.getId().intValue(), utilizator2.getId().intValue());
                                        grafRetele.adaugaMuchie(utilizator2.getId().intValue(), utilizator1.getId().intValue());
                                    }
                                });
                            }
                        });
                    });

                    int nrComponenteConexe = grafRetele.numarComponenteConexe();


                    System.out.println("Numarul de comunitati este: " + nrComponenteConexe);

                    break;
                case 6:
                    // Implementați afișarea celei mai sociabile comunități
                    //cea mai sociabila comunitate este cea cu numarul maxim de prietenii
                    // daca sunt mai multe comunitati cu acelasi numar maxim de prietenii se afiseaza toate
                    // rezultatul va fi componenta conexa cu cele mai multe muchii
                   int nrComunitati1 = 0;
                    Iterable<Utilizator> utilizatori1 = utilizatorService.findAllEntities();
                    for(Utilizator ignored : utilizatori1)
                        nrComunitati1++;
                    System.out.println("Numarul de utilizatori este: " + nrComunitati1);
                    /*GrafRetele grafRetele1 = new GrafRetele(nrComunitati1+1);
                    for(Utilizator utilizator1:utilizatori1)
                    {
                        for (Utilizator utilizator2:utilizatori1)
                        {
                            if(!Objects.equals(utilizator1.getId(), utilizator2.getId()))
                            {
                                Iterable<Prietenie> prietenii = prietenieService.findAllEntities();
                                for (Prietenie prietenie5 : prietenii) {
                                    if (prietenie5.getId().getLeft().equals(utilizator1.getId()) && prietenie5.getId().getRight().equals(utilizator2.getId())||prietenie5.getId().getLeft().equals(utilizator2.getId()) && prietenie5.getId().getRight().equals(utilizator1.getId())) {
                                        grafRetele1.adaugaMuchie(utilizator1.getId().intValue(), utilizator2.getId().intValue());
                                        grafRetele1.adaugaMuchie(utilizator2.getId().intValue(), utilizator1.getId().intValue());
                                    }
                                }
                            }
                        }
                    }
                    grafRetele1.exploreazaComponente();
                    int componentaMaxima = grafRetele1.gasesteComponentaCuCeleMaiMulteNoduri();
                    List<Integer> noduriInComponenta=grafRetele1.noduriInComponenta(componentaMaxima);
                    System.out.println("Cea mai sociabila comunitate este: ");
                    System.out.println(noduriInComponenta);*/

                    Graph graph = new Graph(nrComunitati1);
                    /*for (Utilizator utilizator1:utilizatori1) {
                        for (Utilizator utilizator2 : utilizatori1) {
                            if (!Objects.equals(utilizator1.getId(), utilizator2.getId())) {
                                Iterable<Prietenie> prietenii = prietenieService.findAllEntities();
                                for (Prietenie prietenie5 : prietenii) {
                                    if (prietenie5.getId().getLeft().equals(utilizator1.getId()) && prietenie5.getId().getRight().equals(utilizator2.getId()) || prietenie5.getId().getLeft().equals(utilizator2.getId()) && prietenie5.getId().getRight().equals(utilizator1.getId())) {
                                        graph.addEdge(utilizator1.getId().intValue(), utilizator2.getId().intValue());
                                        graph.addEdge(utilizator2.getId().intValue(), utilizator1.getId().intValue());
                                    }
                                }
                            }
                        }
                    }*/
                    utilizatori1.forEach(utilizator1 -> {
                        utilizatori1.forEach(utilizator2 -> {
                            if (!Objects.equals(utilizator1.getId(), utilizator2.getId())) {
                                Iterable<Prietenie> prietenii = prietenieService.findAllEntities();
                                prietenii.forEach(prietenie5 -> {
                                    if ((prietenie5.getId().getLeft().equals(utilizator1.getId()) && prietenie5.getId().getRight().equals(utilizator2.getId())) ||
                                            (prietenie5.getId().getLeft().equals(utilizator2.getId()) && prietenie5.getId().getRight().equals(utilizator1.getId()))) {
                                        graph.addEdge(utilizator1.getId().intValue(), utilizator2.getId().intValue());
                                        graph.addEdge(utilizator2.getId().intValue(), utilizator1.getId().intValue());
                                    }
                                });
                            }
                        });
                    });

                    // int lungime=graph.findConnectedComponentWithLongestPath();
                    List<Integer>longestPath=graph.findComponentWithLongestPath();
                    System.out.println("Cea mai sociabila comunitate este: ");
                    System.out.println(longestPath);

                    //System.out.println("Lungimea maxima a componentei conexe este: "+lungime);

                    break;

                    case 7:
                       //afisati lista de utilizatori
                        Iterable<Utilizator> users = utilizatorService.findAllEntities();
                        /*for (Utilizator user : users) {
                            System.out.println(user.toString());
                        }*/
                        users.forEach(user -> {
                            System.out.println(user.toString());
                        });

                        break;
                case 8:
                    //cautare utilizator dupa id
                    System.out.println("Introduceti id-ul utilizatorului cautat: ");
                    Long id = scanner.nextLong();
                    Optional<Utilizator> utilizatorCautat = utilizatorService.findEntityByID(id);
                    if (utilizatorCautat.isPresent()) {
                        System.out.println("Utilizatorul cautat este: " + utilizatorCautat);
                    } else {
                        System.out.println("Nu exista utilizatorul cu id-ul " + id);
                    }
                    break;
                case 9:
                    //afisare lista de prieteni
                    Iterable<Prietenie> prieteniis = prietenieService.findAllEntities();
                    /*for (Prietenie prietenies : prieteniis) {
                        System.out.println(prietenies.toString());
                    }*/
                    prieteniis.forEach(prieteniee -> {
                        System.out.println(prieteniee.toString());
                    });

                    break;
                case 0:
                    running = false;
                    System.out.println("La revedere!");
                    break;
                default:
                    System.out.println("Opțiune invalidă. Vă rugăm să alegeți o opțiune validă.");
                    break;
            }
        }
    }
}
