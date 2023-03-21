package jpa;

import dao.*;
import jpa.business.*;

import java.util.List;



public class JpaTest {

	/**
	 * Peuple la base avec 20 Users (Ne font pas partie du support, n'ont pas de tickets et donc pas de messages)
	 */
	public static void fillDatabaseWithNormalUsers(){
		String[] randomNames = {"Gabriel", "Léo", "Raphael", "Louis", "Arthur", "Jules", "Mael", "Noah", "Adam", "Lucas",
				                "Jade", "Louise", "Emma", "Ambre", "Alice", "Rose", "Anna", "Alba", "Romy", "Mia" };

		for(int i = 0 ; i < randomNames.length ; i++){
			UserDao userDao = new UserDao();
			User user = new SimpleUser(randomNames[i]);
			userDao.save(user);
		}
	}



	/**
	 * Peuple la base avec 5 Users qui font partie du support (n'ont pas de tickets créés ni affectés)
	 */
	public static void fillDatabaseWithSupportMembers(){
		String[] randomAdmins = {"Admin A" , "Admin B", "Admin C", "Admin D", "Admin E"};
		for(int i = 0 ; i < randomAdmins.length ; i++){
			SupportMemberDao suppDao = new SupportMemberDao();
			SupportMember supp = new SupportMember(randomAdmins[i]);
			suppDao.save(supp);
		}
	}


	/**
	 *
	 */
	public static void fillDatabaseWithTicketsMessagesTags(){

		User user1 = new SimpleUser("Jean");
		User user2 = new SimpleUser("Jeanne");
		User user3 = new SimpleUser("Pierre");

		SupportMember supp1 = new SupportMember("Noel Plouzeau");
		SupportMember supp2 = new SupportMember("Olivier Barais");

		Tag tag1 = new Tag("Help");
		Tag tag2 = new Tag("Bug");
		Tag tag3 = new Tag("Feature");

		Ticket ticket1 = new Ticket("Recherche Option","Où trouver...", user1);
		Ticket ticket2 = new Ticket("Incompréhension","Je ne comprends pas...", user1);
		Ticket ticket3 = new Ticket("Besoin d'aide", "J'ai besoin d'aide pour...", user2);
		Ticket ticket4 = new Ticket("Erreur", "Erreur quand...", user2);
		Ticket ticket5 = new Ticket("Disfonctionnement", "Ne fonctionne pas...", user3);
		Ticket ticket6 = new Ticket("Idée Feature", "Nouveau bouton pour...", user3);

		Message messageA1 = new Message(supp1, ticket1, "Veuillez vous rendre dans la section ...");
		Message messageA2 = new Message(user1, ticket1, "Merci beaucoup.0");

		Message messageB1 = new Message(supp1, ticket4, "L'erreur rencontrée se répète-t-elle à chaque tentative ?");
		Message messageB2 = new Message(user2, ticket4, "Non elle apparaît seulement quand je fais un double-click");
		Message messageB3 = new Message(supp1, ticket4, "Très bien, merci pour votre retour. L'erreur sera corrigée");

		UserDao userDao = new UserDao();
		userDao.save(user1);
		userDao.save(user2);
		userDao.save(user3);

		TagDao tagDao = new TagDao();
		tagDao.save(tag1);
		tagDao.save(tag2);
		tagDao.save(tag3);

		ticket1.addTag(tag1);
		ticket2.addTag(tag1);
		ticket2.addTag(tag2);
		ticket3.addTag(tag1);
		ticket4.addTag(tag2);
		ticket5.addTag(tag2);
		ticket6.addTag(tag3);

		TicketDao ticketDao = new TicketDao();
		ticketDao.save(ticket1);
		ticketDao.save(ticket2);
		ticketDao.save(ticket3);
		ticketDao.save(ticket4);
		ticketDao.save(ticket5);
		ticketDao.save(ticket6);

		supp1.affectTicket(ticket1);
		supp1.affectTicket(ticket2);
		supp1.affectTicket(ticket3);
		supp1.affectTicket(ticket4);
		supp2.affectTicket(ticket4);
		supp2.affectTicket(ticket5);
		supp2.affectTicket(ticket6);

		SupportMemberDao suppDao = new SupportMemberDao();
		suppDao.save(supp1);
		suppDao.save(supp2);

		MessageDao messageDao = new MessageDao();
		messageDao.save(messageA1);
		messageDao.save(messageA2);
		messageDao.save(messageB1);
		messageDao.save(messageB2);
		messageDao.save(messageB3);

	}

	public static void requestTest(){

		TicketDao ticketDao = new TicketDao();

		List<Ticket> list1 = ticketDao.getAllTicketsByUserId((long) 26);
		 System.out.println(list1.get(0));

		 List<Ticket> list2 = ticketDao.getAffectedTicketsBySupportMemberId((long) 35);
		 System.out.println(list2.get(0));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		fillDatabaseWithNormalUsers();
		fillDatabaseWithSupportMembers();
		fillDatabaseWithTicketsMessagesTags();
		requestTest();



		/* EntityManager manager = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		manager.close();
		EntityManagerHelper.closeEntityManagerFactory(); */
		//		factory.close();
	}


}
