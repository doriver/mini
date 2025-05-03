package com.ex.mini.shop.application;

import com.ex.mini.common.argumentResolver.UserInfo;
import com.ex.mini.common.exception.ErrorCode;
import com.ex.mini.common.exception.ExpectedException;
import com.ex.mini.common.utils.UserUtils;
import com.ex.mini.shop.application.leaf.ItemServiceLeaf;
import com.ex.mini.shop.domain.entity.Item;
import com.ex.mini.shop.domain.repository.ItemRepository;
import com.ex.mini.shop.presentation.dto.request.ItemCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    /*
        todo : 재고(Stock)에 있는 물건을 item으로 등록할수 있도록할꺼, 아직 재고쪽은 미개발
        아이템 등록
        MANAGER, ADMIN 권한이 등록 가능
     */
    public Long insertItem(ItemCreateDTO itemCreateDTO, UserInfo userInfo) {
        UserUtils.checkLogin(userInfo.getUserId());
        UserUtils.checkManagerAdmin(userInfo.getRole());

        // Item저장
        Item item = new Item(itemCreateDTO.getName(), itemCreateDTO.getPrice(), itemCreateDTO.getCount(), LocalDateTime.now());
        Item savedItem = itemRepository.save(item);
        return savedItem.getId();
    }
}
