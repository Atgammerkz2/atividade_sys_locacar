package edu.syslocacar.persistence;

import edu.syslocacar.model.entity.Locacao;
import edu.syslocacar.model.entity.Veiculo;
import edu.syslocacar.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class LocacaoDAO {
    public void save(Locacao locacao) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(locacao);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Locacao> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Query<Locacao> query = session.createQuery("from Locacao", Locacao.class);
            return query.list();
        }
    }

    public Locacao findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Locacao.class, id);
        }
    }

    public void update(Locacao locacao) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(locacao);
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
            Locacao locacao = session.get(Locacao.class, id);
            if (locacao != null) {
                session.delete(locacao);
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
