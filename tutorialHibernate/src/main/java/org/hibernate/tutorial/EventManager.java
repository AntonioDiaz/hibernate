package org.hibernate.tutorial;

/**
 * mvn exec:java -Dexec.mainClass="org.hibernate.tutorial.EventManager" -Dexec.args="store"
 * arranca la bbdd:
 * mvn exec:java -Dexec.mainClass="org.hsqldb.Server" -Dexec.args="-database.0 file:target/data/tutorial"
 */

import org.hibernate.Session;

import java.util.*;

import org.hibernate.tutorial.domain.Event;
import org.hibernate.tutorial.domain.Person;
import org.hibernate.tutorial.util.HibernateUtil;

public class EventManager {

	public static void main(String[] args) {
		EventManager mgr = new EventManager();
		if (args[0].equals("storeE")) {
			mgr.createAndStoreEvent("My Event", new Date());
		} else if (args[0].equals("storeP")) {
			Long eventId = mgr.createAndStoreEvent("My Event Added", new Date());
			Long personId = mgr.createAndStorePerson("nombre", "apellido");
			mgr.addPersonToEvent(personId, eventId);
			System.out.println("Added person " + personId + " to event " + eventId);
		} else if (args[0].equals("listP")) {
			List<Person> listPersons = mgr.listPersons();
			for (Person person : listPersons) {
				System.out.println("["+ person +"]");
			}
		} else if (args[0].equals("listE")) {
			List<Event> events = mgr.listEvents();
			for (Event event : events) {
				System.out.println("******Event: " + event);
			}
		}
		HibernateUtil.getSessionFactory().close();
	}

	private Long createAndStorePerson(String name, String apellido) {
		org.hibernate.classic.Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Person person = new Person();
		person.setFirstname(name);
		person.setLastname(apellido);
		person.setAge((int)new Date().getTime()%100);
		session.save(person);
		session.getTransaction().commit();
		return person.getId();
	}

	private Long createAndStoreEvent(String title, Date theDate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Event theEvent = new Event();
		theEvent.setTitle(title);
		theEvent.setDate(theDate);
		session.save(theEvent);
		session.getTransaction().commit();
		return theEvent.getId();
	}

	private List<Event> listEvents() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Event> result = session.createQuery("from Event").list();
		session.getTransaction().commit();
		return result;
	}
	
	private List<Person> listPersons() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Person> result = session.createQuery("from Person").list();
		session.getTransaction().commit();
		return result;
	}

	private void addPersonToEvent(Long personId, Long eventId) {
		System.out.println("----------->" + personId);
		System.out.println("----------->" + eventId);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Person aPerson = (Person) session.load(Person.class, personId);
		Event anEvent = (Event) session.load(Event.class, eventId);
		aPerson.getEvents().add(anEvent);
		session.getTransaction().commit();
	}
}