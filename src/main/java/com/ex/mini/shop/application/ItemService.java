package com.ex.mini.shop.application;

import com.ex.mini.common.exception.ErrorCode;
import com.ex.mini.common.exception.ExpectedException;
import com.ex.mini.shop.domain.model.Item;
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
        아이템 저장
     */
    public Long saveItem(ItemCreateDTO itemCreateDTO) {
        Item item = Item.builder()
                .name(itemCreateDTO.getName()).price(itemCreateDTO.getPrice()).createdAt(LocalDateTime.now())
                .build();

        Long savedItemId = itemRepository.save(item).getId();

        if (savedItemId == null) {
            throw new ExpectedException(ErrorCode.FAIL_SAVE_ITEM);
        }
        return savedItemId;
    }
}
