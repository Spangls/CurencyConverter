package ru.mpt.convertor;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mpt.convertor.model.Item;
import ru.mpt.convertor.model.Price;
import ru.mpt.convertor.repos.ItemRepo;
import ru.mpt.convertor.repos.PriceRepo;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
class ConverterApplicationTests {
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private PriceRepo priceRepo;


    @Test
    public void priceTest() {
        Item itemOld = itemRepo.findFirstById(5);
        assertThat(itemOld.getPrice()).isEqualTo(500f);
//        Price newPrice = new Price(itemOld, 500f);
//        priceRepo.save(newPrice);
//        Item itemNew = itemRepo.findFirstById(5);
//        assertThat(itemOld.getPrice()).isNotEqualTo(itemNew.getPrice()).isEqualTo(newPrice.getPrice());
    }

}
