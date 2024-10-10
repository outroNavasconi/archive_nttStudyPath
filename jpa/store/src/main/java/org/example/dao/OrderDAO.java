package org.example.dao;

import org.example.model.ClientOrder;
import org.example.vo.SalesReportVo;

import java.util.List;

public interface OrderDAO {
    void register(ClientOrder clientOrder);

    List<Object[]> salesReport();

    List<SalesReportVo> salesReportV2();

    ClientOrder getOrderWithCliente(Long id);
}
