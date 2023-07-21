package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

	private final EntityManager em;

	public void save(Item item){
		if (item.getId() == null) { //새로 생성한 객체(신규 등록)
			em.persist(item);
		} else {
			em.merge(item); //업데이트랑 비슷
		}
	}

	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}

	public List<Item> findALl() {
		return em.createQuery("select i from Item i", Item.class)
				.getResultList();
	}
}
