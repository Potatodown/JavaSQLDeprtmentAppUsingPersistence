import bean.employees;
import jakarta.persistence.*;

import java.util.List;

public class Main {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    //Main Console
    public static void main(String[] args) {
        findMaxSalary();
        //getNot1015kEmp();
        entityManagerFactory.close();
    }

    public static void findMaxSalary() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String query = "SELECT max(a.salary) AS maxsal FROM employees a where job_id = 9";
        Query q = entityManager.createQuery(query);
        employees maxsal = new employees();
        try {
            maxsal.setSalary((Double) q.getSingleResult());
            System.out.printf("%-2s %n", "maximum salary");
            System.out.printf("%-2s %n", maxsal.getSalary());
        } catch (NoResultException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            entityManager.close();
        }
    }

    public static void getNot1015kEmp() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<employees> entityTypedQuery = entityManager.createNamedQuery("findspecificEmployees", employees.class);
        try {
            List<employees> incomeEntityList = entityTypedQuery.getResultList();
            System.out.printf("%-10s %-15s %-50s %n", "firstname", "lastname", "salary");
            incomeEntityList.forEach(field -> System.out.printf("%-10s %-15s %-50s %n", field.getFirst_name(),field.getLast_name(), field.getSalary()));
        } catch (NoResultException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            entityManager.close();
        }
    }
}
