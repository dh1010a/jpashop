package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;

	@Transactional
	public void saveItem(Item item) {
		itemRepository.save(item);
	}

	/**
	 * 영속성 컨텍스트가 자동 변경
	 */
	@Transactional
	public void updateItem(Long id, String name, int price, int stockQuantity) {
		Item item = itemRepository.findOne(id);
		item.setName(name);// 가급적 setter 말고 change()메소드 같은거 만들어서 깔끔하게 관리하는게 좋음
		item.setPrice(price); // 어디서 바뀌는지 추적이 어려운 단점이 있음
		item.setStockQuantity(stockQuantity);
	}

	public List<Item> findItems() {
		return itemRepository.findALl();
	}

	public Item findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}
}
