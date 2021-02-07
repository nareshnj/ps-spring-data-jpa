package com.pluralsight.conferencedemo.models;

import com.pluralsight.conferencedemo.repositories.PricingCategoryJpaRepository;
import com.pluralsight.conferencedemo.repositories.TicketPriceJpaRepository;
import com.pluralsight.conferencedemo.repositories.TicketTypeJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TicketPriceTest {
    @Autowired
    private TicketPriceJpaRepository repository;

    @Autowired
    private PricingCategoryJpaRepository pcRepository;

    @Autowired
    private TicketTypeJpaRepository ttRepository;


    @Test
    public void testFind() throws Exception {
        TicketPrice ticket = repository.getOne(1L);
        assertNotNull(ticket);
    }

    @Test
    @Transactional
    public void testSaveAndGetAndDelete() throws Exception {
        TicketPrice tp = new TicketPrice();
        tp.setBasePrice(BigDecimal.valueOf(200, 2));

        tp.setPricingCategory(pcRepository.getOne("E"));

        tp.setTicketType(ttRepository.getOne("C"));

        tp = repository.saveAndFlush(tp);

        TicketPrice otherTp = repository.getOne(tp.getTicketPriceId());
        assertEquals(BigDecimal.valueOf(200, 2), otherTp.getBasePrice());

        repository.deleteById(otherTp.getTicketPriceId());
    }

}
