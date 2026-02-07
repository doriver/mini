package com.ex.mini.warehouse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    // 재고 감소 로직 구현
    @Transactional
    public void decrease(Long id, Long quantity) {
        // 락을 걸고 재고 데이터를 가져온다
        Optional<Stock> stock = stockRepository.findByIdWithPessimisticLock(id);

        // 재고 감소
        stock.orElseThrow(() -> new RuntimeException("해당하는 재고가 없습니다."))
                .decrease(quantity);

        // 갱신된 값을 저장
        stockRepository.save(stock.get());
    }
}
