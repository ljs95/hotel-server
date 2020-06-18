package cn.hotel.hotelserver.mapper.housing;


import cn.hotel.hotelserver.model.housing.HousingOperation;
import org.springframework.stereotype.Repository;

@Repository
public interface HousingOperationMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(HousingOperation record);

    HousingOperation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HousingOperation record);
}