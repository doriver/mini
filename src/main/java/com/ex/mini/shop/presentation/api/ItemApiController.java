package com.ex.mini.shop.presentation.api;

import com.ex.mini.common.ApiResponse;
import com.ex.mini.shop.application.ItemService;
import com.ex.mini.shop.presentation.dto.request.ItemCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemService itemService;

    /*
        아이템 등록
     */
    @PostMapping
    public ApiResponse<Long> createItem(@RequestBody ItemCreateDTO itemCreateDTO) {
        Long savedItemId = itemService.saveItem(itemCreateDTO);

        return ApiResponse.success(savedItemId);
    }

}
