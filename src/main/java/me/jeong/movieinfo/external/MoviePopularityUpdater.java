package me.jeong.movieinfo.external;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.jeong.movieinfo.service.dto.MoviePopularityDTO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class MoviePopularityUpdater {
    // @PersistenceContext : JPA의 EntityManager를 트랜잭션에 맞게 스프링이 자동 주입해주는 어노테이션
    // 왜 @Autowired가 아닌가?
    // → EntityManager는 트랜잭션마다 달라져야 하므로,
    //    스프링이 트랜잭션 범위에 맞는 EntityManager를 제공해주는 @PersistenceContext를 사용해야 함
    @PersistenceContext
    // EntityManager는 JPA의 핵심 객체로, JPQL 쿼리 실행에 사용됨
    private EntityManager entityManager;

    @Transactional
    public void batchUpdatePopularity(List<MoviePopularityDTO> dtoList) {
        int batchSize = 50; // 한 번에 50건씩 처리

        for (int i = 0; i < dtoList.size(); i++) {
            MoviePopularityDTO dto = dtoList.get(i);

            entityManager.createQuery(
                            "UPDATE Movie m SET m.popularity = :popularity WHERE m.id = :id")
                    .setParameter("id", dto.getId())
                    .setParameter("popularity", dto.getPopularity())
                    .executeUpdate();

            // 일정 건수마다 flush + clear
            if (i % batchSize == 0) {
                entityManager.flush();  // SQL 실행
                entityManager.clear();  // 1차 캐시 비움
            }
        }

        // 마지막 남은 데이터 처리
        entityManager.flush();
        entityManager.clear();
    }
}
