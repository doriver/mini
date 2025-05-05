package com.ex.mini.shop.application;

import com.ex.mini.common.argumentResolver.UserInfo;
import com.ex.mini.common.utils.UserUtils;
import com.ex.mini.shop.domain.entity.Item;
import com.ex.mini.shop.domain.repository.ItemRepository;
import com.ex.mini.shop.presentation.dto.request.ItemCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    /*
        todo : 재고(Stock)에 있는 물건을 item으로 등록할수 있도록할꺼, 아직 재고쪽은 미개발
        아이템 등록
        MANAGER, ADMIN 권한이 등록 가능
     */
    public Long registerItem(ItemCreateDTO itemCreateDTO, UserInfo userInfo) {
        UserUtils.checkLogin(userInfo.getUserId());
        UserUtils.checkManagerAdmin(userInfo.getRole());

        // Item저장
        Item item = new Item(itemCreateDTO.getName(), itemCreateDTO.getPrice(), itemCreateDTO.getCount(), LocalDateTime.now());
        Item savedItem = itemRepository.save(item);
        return savedItem.getId();
    }
    
    /*
        주문에따른, Item들 개수차감
        구매 수량만큼 -해줘야함
        id, count들 알아야함
     */
    @Transactional
    public void itemCountDownByOrder(Map<Long, Integer> orderedItemCountMap) {
        for (Long itemId : orderedItemCountMap.keySet()) {
            Item item = itemRepository.findById(itemId).orElse(null);
            item.minusCount(orderedItemCountMap.get(itemId));
            itemRepository.save(item);
        }
    }
}
