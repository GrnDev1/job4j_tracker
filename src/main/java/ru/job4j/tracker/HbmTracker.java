package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                session.save(item);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
            return item;
        }
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean result = false;
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                result = session.createQuery("UPDATE Item "
                                + "SET name=:name, created=:created "
                                + "WHERE id=:id")
                        .setParameter("name", item.getName())
                        .setParameter("created", item.getCreated())
                        .setParameter("id", id).executeUpdate() != 0;
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                result = session.createQuery(
                                "DELETE Item WHERE id = :fId")
                        .setParameter("fId", id)
                        .executeUpdate() != 0;
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = List.of();
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                result = session.createQuery("FROM Item ORDER BY id", Item.class).list();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = List.of();
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                result = session.createQuery("FROM Item WHERE name=:key", Item.class)
                        .setParameter("key", key).list();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
        return result;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                item = session.get(Item.class, id);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
        return item;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}