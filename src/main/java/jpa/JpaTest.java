package jpa;

import dao.*;
import enumeration.Tag;
import jpa.business.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class JpaTest {

    private final EntityManager manager;

    public JpaTest(EntityManager manager) {
        this.manager = manager;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
        EntityManager manager = factory.createEntityManager();
        JpaTest test = new JpaTest(manager);

//        EntityTransaction tx = manager.getTransaction();
//        tx.begin();
        try {
            test.fillDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
       // tx.commit();

        test.listTickets();

        manager.close();
        System.out.println(".. done");
    }

    private void listTickets() {
        List<Ticket> resultList = manager.createQuery("Select t From Ticket t", Ticket.class).getResultList();
        System.out.println("num of tickets:" + resultList.size());
        for (Ticket next : resultList) {
            System.out.println("next ticket: " + next);
        }
    }

    private void fillDatabase() {
        int numOfTickets = manager.createQuery("Select t From Ticket t", Ticket.class).getResultList().size();
        if (numOfTickets == 0) {
            User reporterTristan = new User("Tristan");
            User assigneeJulien = new User("Julien");
            User assigneeJohan = new User("Johan");

            UserDao userDao = new UserDao();
            userDao.save(reporterTristan);
            userDao.save(assigneeJulien);
            userDao.save(assigneeJohan);

            /*manager.persist(reporterTristan);
            manager.persist(assigneeJulien);
            manager.persist(assigneeJohan);*/

            Ticket ticket1 = new Ticket("Ajout dashboard", "Ajout d'un dashboard sur l'écran d'accueil", reporterTristan);
            ticket1.setAssignee(assigneeJulien);
            ticket1.addTag(Tag.FEATURE);
            Ticket ticket2 = new Ticket("Bug", "Bug lors du chargement des utilisateurs", reporterTristan);
            ticket2.setAssignee(assigneeJohan);
            ticket2.addTag(Tag.BUG);
            Ticket ticket3 = new Ticket("Migration", "Migration vers Angular15", reporterTristan);
            ticket1.setAssignee(assigneeJulien);
            ticket1.addTag(Tag.TECH);

            TicketDao ticketDao = new TicketDao();
            ticketDao.save(ticket1);
            ticketDao.save(ticket2);
            ticketDao.save(ticket3);

         /*   manager.persist(ticket1);
            manager.persist(ticket2);
            manager.persist(ticket3);*/

            Message message1 = new Message(assigneeJohan, ticket2, "L'erreur vient-elle du back ou du front ?");
            Message message2 = new Message(reporterTristan, ticket2, "Ca peut-être les deux, a vous de trouver");
            Message message3 = new Message(assigneeJohan, ticket2, "D'accord !");
            Message message4 = new Message(assigneeJulien, ticket3, "Peut-on passer directement de Angular12 à 15 ?");
            Message message5 = new Message(reporterTristan, ticket3, "Non, il faut passer les versions une par une");
            Message message6 = new Message(assigneeJulien, ticket3, "Ca marche !");

            MessageDao messageDao = new MessageDao();
            messageDao.save(message1);
            messageDao.save(message2);
            messageDao.save(message3);
            messageDao.save(message4);
            messageDao.save(message5);
            messageDao.save(message6);

           /* manager.persist(message1);
            manager.persist(message2);
            manager.persist(message3);
            manager.persist(message4);
            manager.persist(message5);
            manager.persist(message6);*/


          /*  UserDao userDao = new UserDao();
            userDao.save(reporterTristan);
            userDao.save(assigneeJulien);
            userDao.save(assigneeJohan);

            TicketDao ticketDao = new TicketDao();
            ticketDao.save(ticket1);
            ticketDao.save(ticket2);
            ticketDao.save(ticket3);

            MessageDao messageDao = new MessageDao();
            messageDao.save(message1);
            messageDao.save(message2);
            messageDao.save(message3);
            messageDao.save(message4);
            messageDao.save(message5);
            messageDao.save(message6);*/
        }

    }


}
