package murach.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;

import murach.business.User;

public class UserDB {
    
    public static int insert(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(user);
            trans.commit();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
            return 0;
        } finally {
            em.close();
        }
    }
    
    public static int update(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            TypedQuery<User> q = em.createQuery(
                    "SELECT u FROM User u WHERE u.email = :email", User.class);
            q.setParameter("email", user.getEmail());
            User existingUser = q.getSingleResult();
            
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            
            trans.commit();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
            return 0;
        } finally {
            em.close();
        }
    }
    
    public static int delete(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            TypedQuery<User> q = em.createQuery(
                    "SELECT u FROM User u WHERE u.email = :email", User.class);
            q.setParameter("email", user.getEmail());
            User existingUser = q.getSingleResult();
            
            em.remove(existingUser);
            trans.commit();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
            return 0;
        } finally {
            em.close();
        }
    }
    
    public static boolean emailExists(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM User u " +
                         "WHERE u.email = :email";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setParameter("email", email);
        try {
            User user = q.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        } finally {
            em.close();
        }
    }
    
    public static User selectUser(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM User u " +
                         "WHERE u.email = :email";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setParameter("email", email);
        try {
            User user = q.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static List<User> selectUsers() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM User u ORDER BY u.userId";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        List<User> users;
        try {
            users = q.getResultList();
            return users;
        } finally {
            em.close();
        }
    }
}
