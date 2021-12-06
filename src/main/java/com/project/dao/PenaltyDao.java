package com.project.dao;

import com.project.entities.Penalty;

import java.util.List;

public interface PenaltyDao extends EntityDao<Penalty> {

    Penalty getPenaltyByOrder(int orderId);

}
