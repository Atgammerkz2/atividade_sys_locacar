package edu.syslocacar.persistence;

import edu.syslocacar.model.entity.Veiculo;
import edu.syslocacar.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class VeiculoDAO {

    public void save(Veiculo veiculo) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(veiculo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Veiculo> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Query<Veiculo> query = session.createQuery("from Veiculo", Veiculo.class);
            return query.list();
        }
    }

    public Veiculo findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Veiculo.class, id);
        }
    }

    public void update(Veiculo veiculo) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(veiculo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Veiculo veiculo = session.get(Veiculo.class, id);
            if (veiculo != null) {
                session.delete(veiculo);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
