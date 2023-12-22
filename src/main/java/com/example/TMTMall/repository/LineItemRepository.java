package com.example.TMTMall.repository;

import com.example.TMTMall.model.LineItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineItemRepository extends CrudRepository<LineItem, Long> {

  List<LineItem> findLineItemByOrder_Id(Long id);
}
