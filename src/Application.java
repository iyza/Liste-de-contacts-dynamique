import java.util.*;

import Contact.*;

public class Application {

    private static Scanner sc = new Scanner(System.in);
    private static Map<String, Contact> contacts = new HashMap<>();
    private static Contact contact = new Contact();
    private static Adresse adresse = new Adresse();
    private static Occupation occupation = new Occupation();
    private static Entreprise entreprise = new Entreprise();
    private static Telephone telephone;
    private static LinkedList<Contact> rappels = new LinkedList<>();

    public static void main(String[] args) {

        boolean o = true;
        int choix;
        String a;

        while (o) {
            System.out.println("Que voulez vous faire?");
            System.out.println(" 1 - Nouveau contact");
            System.out.println(" 2 - Modifier un contact");
            System.out.println(" 3 - Supprimer un contact");
            System.out.println(" 4 - Afficher les contacts");
            System.out.println(" 5 - Liste de rappels");
            System.out.println(" 6 - Fermer l'application");
            choix = sc.nextInt();
            switch (choix) {
                case 1:
                    creerContact();
                    break;

                case 2:
                    modifierContact();
                    break;

                case 3:
                    supprimerContact();
                    break;

                case 4:
                    for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
                        System.out.println("Informations personelles de " + entry.getKey().toUpperCase() + " " + entry.getValue().getNom().toUpperCase());
                        System.out.println("Adresse : " + entry.getValue().getAdresse().getRue() + " " + entry.getValue().getAdresse().getNumPorte() + ", " + entry.getValue().getAdresse().getVille() + ", " + entry.getValue().getAdresse().getProvince() + ", " + entry.getValue().getAdresse().getPays());
                        System.out.println("Occupation : " + entry.getValue().getOccupation().getPoste());
                        System.out.println("Nom de compagnie : " + entry.getValue().getOccupation().getEntreprise().getNom());
                        System.out.println("Adresse de travail : " + entry.getValue().getOccupation().getEntreprise().getAdresse());
                        for (int i=0;i<entry.getValue().getTelephones().size();i++) {
                            System.out.println("Numero #" + (i+1) + " : " + entry.getValue().getTelephones().get(i).getNum() + ", Telephone de " + entry.getValue().getTelephones().get(i).getInfo());
                        }
                        System.out.println("");
                    }
                    break;

                case 5:
                    listeDeRappels();
                    break;

                case 6:
                    System.out.println("byebye XDXD");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    public static Contact creerContact() {
        String a;
        boolean o = true;
        int choix;
        List<Telephone> telephones = new ArrayList<>();
        adresse = new Adresse();
        entreprise = new Entreprise();
        occupation = new Occupation();
        contact = new Contact();

        System.out.println("Quel est le prenom de votre contact?");
        a = sc.next();
        contact.setPrenom(a);
        System.out.println("Et son nom de famille?");
        a = sc.next();
        contact.setNom(a);
        System.out.println("Quel est le nom de la rue ou habite " + contact.getPrenom() + " " + contact.getNom());
        a = sc.next();
        adresse.setRue(a);
        System.out.println("Le numero de porte?");
        a = sc.next();
        adresse.setNumPorte(a);
        System.out.println("Dans quelle ville se trouve votre contact?");
        a = sc.next();
        adresse.setVille(a);
        System.out.println("Province?");
        a = sc.next();
        adresse.setProvince(a);
        System.out.println("Pays?");
        a = sc.next();
        adresse.setPays(a);
        System.out.println("Que fait votre contact comme travail?");
        a = sc.next();
        occupation.setPoste(a);
        System.out.println("Quel est le nom de l'entreprise de son travail?");
        a = sc.next();
        entreprise.setNom(a);
        System.out.println("Dans quelle rue se trouve son travail?");
        a = sc.next();
        entreprise.setAdresse(a);
        while (o) {
            System.out.println("Voulez-vous ajoutez un numero de telephone a votre contact");
            System.out.println(" 1 - Oui");
            System.out.println(" 2 - Non");
            choix = sc.nextInt();
            if (choix == 1) {
                telephone = new Telephone();
                System.out.println("Quel type de telephone voulez-vous ajoutez (maison, cellulaire, travail, etc.)?");
                a = sc.next();
                telephone.setInfo(a);
                System.out.println("Quel est le numero?");
                a = sc.next();
                telephone.setNum(a);
                telephones.add(telephone);
            }
            else if (choix == 2) {
                o = false;
            }
            else {
                System.out.println("Choix invalide.");
            }
        }
        contact.setAdresse(adresse);
        occupation.setEntreprise(entreprise);
        contact.setOccupation(occupation);
        contact.setTelephones(telephones);
        contacts.put(contact.getPrenom(), contact);

        return contact;
    }

    public static void modifierContact() {
        int i=1;
        boolean modifie = true;
        String a = "";
        System.out.println("Quel contact voulez-vous modifiez? (Entrez son nom)");
        for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
            System.out.println(" " + i + " - " + entry.getKey());
            i++;
        }
        String q = sc.next();
        if (contacts.containsKey(q)) {
            contact = contacts.get(q);
            contacts.remove(q);
            while (modifie) {
                System.out.println("Que voulez vous modifiez?");
                System.out.println(" 1 - Prenom");
                System.out.println(" 2 - Nom");
                System.out.println(" 3 - Rue");
                System.out.println(" 4 - Numero de porte");
                System.out.println(" 5 - Ville");
                System.out.println(" 6 - Province");
                System.out.println(" 7 - Pays");
                System.out.println(" 8 - Poste de travail");
                System.out.println(" 9 - Nom de compagnie");
                System.out.println(" 10 - Adresse de travail");
                System.out.println(" 11 - Liste de telephones");
                System.out.println(" 12 - Laisse faire");
                int choix = sc.nextInt();
                if (choix < 11 && choix > 0) {
                    a = sc.next();
                }

                switch (choix) {
                    case 1:
                        contact.setPrenom(a);
                        break;
                    case 2:
                        contact.setNom(a);
                        break;
                    case 3:
                        adresse.setRue(a);
                        break;
                    case 4:
                        adresse.setNumPorte(a);
                        break;
                    case 5:
                        adresse.setVille(a);
                        break;
                    case 6:
                        adresse.setProvince(a);
                        break;
                    case 7:
                        adresse.setPays(a);
                        break;
                    case 8:
                        occupation.setPoste(a);
                        break;
                    case 9:
                        entreprise.setNom(a);
                        break;
                    case 10:
                        entreprise.setAdresse(a);
                        break;
                    case 11:
                        System.out.println("Quel numero voulez-vous modifiez? (1-" + contact.getTelephones().size() + ")");
                        for (i=0;i<contact.getTelephones().size();i++) {
                            System.out.println(" " + (i+1) + " - " + contact.getTelephones().get(i).getNum() + ", type de telephone: " + contact.getTelephones().get(i).getInfo());
                        }
                        i = sc.nextInt();
                        if (i > 0 && i <= contact.getTelephones().size()) {
                            i--;
                            boolean modifie2 = true;
                            while (modifie2) {
                                System.out.println("Que voulez-vous modifiez");
                                System.out.println(" 1 - Numero");
                                System.out.println(" 2 - Type de telephone");
                                System.out.println(" 3 - Laisse faire");
                                choix = sc.nextInt();
                                switch (choix) {
                                    case 1:
                                        a = sc.next();
                                        contact.getTelephones().get(i).setNum(a);
                                        break;
                                    case 2:
                                        a = sc.next();
                                        contact.getTelephones().get(i).setInfo(a);
                                        break;
                                    case 3:
                                        modifie2 = false;
                                        break;
                                    default:
                                        System.out.println("Choix invalide.");
                                        break;
                                }
                            }
                        }
                        System.out.println("Ce telephone n'existe pas.");
                        break;
                    case 12:
                        modifie = false;
                        break;
                    default:
                        System.out.println("Choix invalide.");
                        break;
                }
                occupation.setEntreprise(entreprise);
                contact.setAdresse(adresse);
                contact.setOccupation(occupation);
                a = contact.getPrenom();
                contacts.put(a, contact);
            }
        }
        else System.out.println("Choix invalide.");
    }

    public static void supprimerContact() {
        int i=1;
        System.out.println("Entrez le nom du contact que vous voulez supprimer.");
        for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
            System.out.println(" " + i + " - " + entry.getKey());
            i++;
        }
        String q = sc.next();
        if (contacts.containsKey(q)) {
            contacts.remove(q);
        }
        else System.out.println("Choix invalide.");
    }

    public static void listeDeRappels() {
        boolean o = true;
        int choix;
        String a;
        System.out.println("Que voulez vous faire?");
        while (o) {
            System.out.println(" 1 - Ajouter un contact a votre liste de rappels");
            System.out.println(" 2 - Rappeler un contact");
            System.out.println(" 3 - Rien faire");
            choix = sc.nextInt();
            System.out.println("");
            switch (choix) {
                case 1:
                    int i=1;
                    System.out.println("Quel contact voulez vous ajoutez? (1-" + contacts.size() + ")");
                    for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
                        System.out.println(" " + i + " - " + entry.getKey());
                        i++;
                    }
                    a = sc.next();
                    contact = contacts.get(a);
                    rappels.add(contact);
                    break;

                case 2:
                    System.out.println(rappels.poll().getPrenom() + " a ete retire de la liste de rappels.");
                    break;

                case 3:
                    o = false;
            }
        }
    }

}
