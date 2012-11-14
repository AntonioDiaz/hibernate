package com.util;

import org.hibernate.Session;

import com.domain.Event;
import com.domain.Person;

import java.util.*;

public class EventManager {

	public static void main(String[] args) {
		EventManager mgr = new EventManager();
		if (args.length > 0) {
			if (args[0].equals("storeEvent")) {
				mgr.createAndStoreEvent("Eventor", new Date());
			} else if (args[0].equals("storePerson")) {
				mgr.createAndStorePerson("Name", "Apellido1");
			} else if (args[0].equals("storePersonEvent")) {
				Long person = mgr.createAndStorePerson("Name", "Apellido1");
				Long event = mgr.createAndStoreEvent("Eventor", new Date());
				mgr.addPersonToEvent(person, event);
			} else if (args[0].equals("list")) {
				List<Event> events = mgr.listEvents();
				System.out.println("Eventos.........................................");
				for (int i = 0; i < events.size(); i++) {
					Event theEvent = events.get(i);
					System.out.println("Event: " + theEvent);
				}
				List<Person> persons = mgr.listPersons();
				System.out.println("Person.........................................");
				for (int i = 0; i < persons.size(); i++) {
					Person thePerson = persons.get(i);
					System.out.println("Person: " + thePerson);
				}
			} else if (args[0].equals("addpersontoevent")) {
				Long eventId = mgr.createAndStoreEvent("My Event", new Date());
				Long personId = mgr.createAndStorePerson("Nombre", "Apellidos...");
				mgr.addPersonToEvent(personId, eventId);
				System.out.println("Added person " + personId + " to event " + eventId);
			}

		}
		HibernateUtil.getSessionFactory().close();
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Person> listPersons() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Person> list = session.createQuery("from Person").list();
		session.getTransaction().commit();
		return list;
	}

	private Long createAndStorePerson(String nombre, String apellidos) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Person thePerson = new Person();
		thePerson.setAge((int)new Date().getTime()%100);
		thePerson.setFirstname(nombre);
		thePerson.setLastname(apellidos);
		thePerson.getEmailAddresses().add(nombre + "@" + apellidos);
		session.save(thePerson);
		session.getTransaction().commit();
		return thePerson.getId();
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

	@SuppressWarnings("unchecked")
	private List<Event> listEvents() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Event> result = session.createQuery("from Event").list();
		session.getTransaction().commit();
		return result;
	}

	private void addPersonToEvent(Long personId, Long eventId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Person aPerson = (Person) session.load(Person.class, personId);
		Event anEvent = (Event) session.load(Event.class, eventId);
		aPerson.getEvents().add(anEvent);
		session.getTransaction().commit();
	}

}