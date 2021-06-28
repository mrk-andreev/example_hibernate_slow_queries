package name.mrkandreev.example_hibernate_slow_queries.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MainService {
  private final EntityManagerFactory entityManagerFactory;

  @Transactional
  public String handle(Double sleepTimeout) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();
    try {
      entityTransaction.begin();
      Query
          query = entityManager.createNativeQuery(String.format("SELECT 1 as ret FROM (SELECT pg_sleep(%s)) foo", sleepTimeout));
      query.getResultList();
      return "OK";
    } finally {
      entityTransaction.commit();
      entityManager.close();
    }
  }
}
