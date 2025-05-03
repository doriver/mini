package com.ex.mini.shop.application.leaf;

import com.ex.mini.common.exception.ErrorCode;
import com.ex.mini.common.exception.ExpectedException;
import com.ex.mini.shop.domain.entity.Item;
import com.ex.mini.shop.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceLeaf {

    private final ItemRepository itemRepository;

    // 단순 저장
    public Item insertItem(Item item, ErrorCode errorCode) {
        Item savedItem = null;
        try {
            savedItem = itemRepository.save(item);
        } catch (Exception e) {
            throw new ExpectedException(errorCode);
        }
        return savedItem;
    }
}
