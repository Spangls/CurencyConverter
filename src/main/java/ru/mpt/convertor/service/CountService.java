package ru.mpt.convertor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mpt.convertor.model.Count;
import ru.mpt.convertor.model.Item;
import ru.mpt.convertor.repos.CountRepo;

import java.util.Set;

@Service
public class CountService {
    @Autowired
    private CountRepo countRepo;

    public Set<Count> findAll() {
        return countRepo.findAll();
    }

    public Integer findCountByItem(Item item) {
        Integer totalCount = 0;
        for (Count count : countRepo.findAllByItem(item))
            totalCount += count.getChange();
        return totalCount;
    }

    public Count save(Count count) {
        return countRepo.save(count);
    }

}
